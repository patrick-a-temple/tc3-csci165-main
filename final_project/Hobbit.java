// Patrick Temple
// Prof. Whitener
// CSCI165
// 4 May 2020

// Final Project: Hobbit
// Purpose: to define the Hobbit class
// that extends the Creature class

// imported classes

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Hobbit extends Creature {

	// class features
	private int reach; // how far a Hobbit can reach out to
	                   // attack a Nazgul (if a Hobbit has a higher
	                   // reach than its radius, it still cannot attack
	                   // outside its radius)
	
	// constructors
	public Hobbit() { super(); } // blank constructor
	
	// overloaded 
	public Hobbit(int[] location, int health, int offense, int defense) {
		super(location, health, offense, defense);
		this.sustenance = 6;
		this.radiusSize = 1;
		this.reach = 1;
	}
	
	// copy constructor for replicate()
	private Hobbit(Hobbit parent, int[] location) {
		super(location, 10, parent.offense, parent.defense); // off and def copied from the parent
		this.sustenance = 7;
		this.radiusSize = parent.radiusSize; // copy the radius size and
		this.reach = parent.reach;           // reach from the parent
		this.turnsSinceReproduction = 0;
	}
	
	@Override
	public void chooseAction(ArrayList<Creature> neighborData) {
		
		// this is a section that I needed to reach out
		// to Stephen W for due to some buggy code,
		// and he helped me rework it
		
		// do not allow this Hobbit to move if it is dead
		if(this.isDead()) {
			System.out.println("This Hobbit is dead and cannot move");
			canCheckGround = false;
			return;
		}
		
		// in case this Hobbit was not able to pick up
		// items on the last turn, make it true again
		this.canCheckGround = true;
		
		
		// refresh this Hobbit's direct spaces
		// (ie. one space up/down/left/right)
		// so it knows what ways it can and cannot go
		refreshDirectSpaces(neighborData);
		
		// look for a Nazgul to attack
		Creature firstEnemyFound = seekEnemy(neighborData);
		
		// if enemy nearby call attack()
		if(firstEnemyFound != null) {
			attack(firstEnemyFound);
			this.canCheckGround = false; // rule: if any Creature attacks,
			                             // it cannot pick up an item
			// rule: Hobbits cannot move after attacking
		}
		
		// if the Hobbit can move at all
		else if(canMoveUp || canMoveRight || canMoveDown || canMoveLeft) {
			// make flags for if there is a Hobbit or Nazgul nearby
			boolean friendUp = false;
			boolean friendRight = false;
			boolean friendDown = false;
			boolean friendLeft = false;
			
			boolean foeUp = false;
			boolean foeRight = false;
			boolean foeDown = false;
			boolean foeLeft = false;
			
			int foeCount = 0;    // for holding how many foes this
			                     // Hobbit is surrounded by
			int friendCount = 0; // for holding the number of
			                     // fellow Hobbits are nearby
			
			for(Creature c : neighborData) {
				int[] tempLocation = c.getLocation(); // holds location of current Nazgul
				
				// skip if this Hobbit finds itself
				if(Arrays.equals(this.location, tempLocation)) {
					continue;
				}
				
				// get differences between the xs and ys
				int xDiff = tempLocation[0] - this.location[0];
				int yDiff = tempLocation[1] - this.location[1];
				
				// move on if outside of radius
				int xTravel = Math.abs(xDiff);
				int yTravel = Math.abs(yDiff);
				
				if(xTravel > this.radiusSize || yTravel > this.radiusSize) {
					continue;
				}
				
				// if there is a Creature to the left
				if(xDiff < 0) {
					// if there is a Nazgul found
					if(Nazgul.class.isInstance(c)) {
						foeCount++;     // note the number of times a
						                // Nazgul was seen
						foeLeft = true; // note that there was a Nazgul
						                // on the left side
						break;
					}
					if(Hobbit.class.isInstance(c)) {
						friendCount++;     // note how many times there
						                   // are fellow Hobbits nearby
						friendLeft = true; // note that there is a Hobbit
						                   // on the left
					}
				}
				
				// the below versions of this indicate Hobbits
				// and Nazgul in other directions
				
				// if there is a Creature to the right
				if(xDiff > 0) {
					if(Nazgul.class.isInstance(c)) {
						foeCount++;
						foeRight = true;
						break;
					}
					if(Hobbit.class.isInstance(c)) {
						friendCount++;
						friendRight = true;
					}
				}
				
				// if there is a Creature above this Hobbit
				if(yDiff < 0) {
					if(Nazgul.class.isInstance(c)) {
						foeCount++;
						foeUp = true;
						break;
					}
					if(Hobbit.class.isInstance(c)) {
						friendCount++;
						friendUp = true;
					}
				}
				
				// if there is a Creature below this Hobbit
				if(yDiff > 0) {
					if(Nazgul.class.isInstance(c)) {
						foeCount++;
						foeDown = true;
						break;
					}
					if(Hobbit.class.isInstance(c)) {
						friendCount++;
						friendDown = true;
					}
				}
			}
			
			// if a Hobbit does not see any other Creature,
			// or if it sees Nazgul in all directions, move
			// in a random direction
			if((friendCount == 0 && foeCount == 0) || foeCount == 4) {
				moveRandomDirection();
				return;
			}
			
			// if a Hobbit has a Nazgul directly behind it,
			// go the opposite way
			if(foeCount > 0) {
				if(canMoveDown && foeUp) {
					move(direction.DOWN);
				}
				else if(canMoveLeft && foeRight) {
					move(direction.LEFT);
				}
				else if(canMoveUp && foeDown) {
					move(direction.UP);
				}
				else if(canMoveRight && foeLeft) {
					move(direction.RIGHT);
				}
				return;
			}

			// move in the direction of a fellow Hobbit
			
			if(canMoveUp && friendUp) {
				move(direction.UP);
			}
			else if(canMoveRight && friendRight) {
				move(direction.RIGHT);
			}
			else if(canMoveDown && friendDown) {
				move(direction.DOWN);
			}
			else if(canMoveLeft && friendLeft) {
				move(direction.LEFT);
			}
			
		} // end else if(can move in at least one direction)
		
		// Hobbit cannot move in any direction at all:
		// do not allow it to collect an item of the ground
		else {
			this.canCheckGround = false;
		}
	}
	
	// trigger the movements of the Hobbit
	// (rule: OK for only a Hobbit to cross to
	// the other side of the walls)
	@Override
	public void move(direction d) {
		
		if(d == direction.UP) {
			
			// if at upper wall loop to bottom of map
			if(this.location[1] == 0) {
				this.location[1] = 99;
			}
			else {
				this.location[1]--;
			}
			
		}
		else if(d == direction.RIGHT) {
			
			// if at right edge move to left edge
			if(this.location[0] == 99) {
				this.location[0] = 0;
			}
			else {
				this.location[0]++;
			}
			
		}
		else if(d == direction.DOWN) {
			
			// if at bottom edge move to top edge
			if(this.location[1] == 99) {
				this.location[1] = 0;
			}
			else {
				this.location[1]++;
			}
			
		}
		else if(d == direction.LEFT) {
			
			// if at left edge move to right edge
			if(this.location[0] == 0) {
				this.location[0] = 99;
			}
			else {
				this.location[0]--;
			}
		}
		else {
			System.out.println("Something strange happened, and the Hobbit cannot move");
		}
		
	}
	
	// define how a Hobbit attacks a Nazgul
	@Override
	public void attack(Creature victim) {

		Random rng = new Random(); // for random number generation
		int damage = 0;            // for holding the damage that
		                           // this Hobbit has dealt to the
		                           // other cre

		// "roll the dice" for a critical hit
		// if it's a 6, if so, deal double damage and
		// ignore the Nazgul's defenses
		int diceRoll = Math.abs(rng.nextInt() % 6) + 1;
		
		if(diceRoll == 6) {
			System.out.println("A Hobbit just landed a CRITICAL HIT on a Nazgul!!");
			damage = 2 * offense;
			victim.alterHealth(damage);
		}
		
		// otherwise, the damage formula is this Hobbit's
		// attack, less the victim's defense
		// if the victim has too high a defense, it cannot
		// attack the victim
		else if(this.offense - victim.getDefense() <= 0) {
			System.out.println("A Hobbit could not attack a Nazgul (too low attack)");
		}
		else {
			damage = this.offense - victim.getDefense();
			victim.alterHealth(damage);
		}
		
		
	}
	
	@Override
	public Creature replicate() {
		
		int reproduceHere[] = { 0, 0 }; // filler location,
		                                // resulting baby should not be here
		
		// find a place to put the baby within bounds of the map
		if(canMoveUp && this.location[0] > 0) {
			reproduceHere[0] = this.location[0];
			reproduceHere[1] = this.location[1] - 1;
		}
		else if(canMoveRight && this.location[1] < 99) {
			reproduceHere[0] = this.location[0] + 1;
			reproduceHere[1] = this.location[1];
		}
		else if(canMoveDown && this.location[0] < 99) {
			reproduceHere[0] = this.location[0];
			reproduceHere[1] = this.location[1] + 1;
		}
		else if(canMoveLeft && this.location[1] > 0) {
			reproduceHere[0] = this.location[0] - 1;
			reproduceHere[1] = this.location[1];
		}
		
		Hobbit baby = new Hobbit(this, reproduceHere); // call the copy constructor using this
		                                               // Hobbit as the "parent"/"copied" Hobbit
		
		this.health--;                   // decrement the health of this Hobbit
		this.turnsSinceReproduction = 0; // and reset the reproduction counter
		return baby;                     // return the baby
	}
	
	// post turn decrementation of hunger
	// and consequential decrease in health if
	// it goes below zero sustenance
	@Override
	public void stay() {
		sustenance--; // decrement sustenance
		turnsSinceReproduction++;
		
		if(this.sustenance <= 0 && this.sustenance >= -2) {
			alterHealth(3);
		}
		else if(this.sustenance < -2) {
			alterHealth(5);
		}
	}
	
	// change the color of the Hobbit based on
	// its sustenance level
	@Override
	public Color color() {
		if(this.sustenance >= 4) {
			return Color.GREEN;
		}
		else if(this.sustenance >= 1) {
			return Color.YELLOW;
		}
		else {
			return Color.RED;
		}
	}
	
	// determine behaviors of Hobbits when they
	// pick up items
	@Override
	public void getItem(Item collectedItem) {
		
		// get the details from the item
		Item.itemType ciType = collectedItem.getCurrentItemType();
		int modRate = collectedItem.getModifyingRate();
		
		// add the item levels into the the correct stat
		if(ciType == Item.itemType.HEALTH) {
			this.health += modRate;
		}
		else if(ciType == Item.itemType.OFFENSE) {
			this.offense += modRate;
		}
		else if(ciType == Item.itemType.DEFENSE) {
			this.defense += modRate;
		}
		else if(ciType == Item.itemType.SIGHT) {
			this.radiusSize += modRate;
		}
		else if(ciType == Item.itemType.NOURISHMENT) {
			this.sustenance += modRate;
			this.health += 3; // bonus: eating food increases some health
		}
		else if(ciType == Item.itemType.REACH) {
			this.reach += modRate;
		}
		
		// add into inventory
		inventory.add(collectedItem);
		
	}
	
	@Override
	public Creature seekEnemy(ArrayList<Creature> neighborData) {
		
		// this is done assuming Map has returned 
		// based on the reach value, decide if there
		// is a Creature that can be attacked
		
		for(Creature c : neighborData) {
			// if the search finds the Creature at this location
			// skip over it
			if(Arrays.equals(this.location, c.getLocation())) {
				continue;
			}
			
			int[] tempLocation = c.getLocation();
			
			int yDiff = Math.abs(tempLocation[1] - this.location[1]);
			int xDiff = Math.abs(tempLocation[0] - this.location[0]);
			
			if(xDiff <= reach && yDiff <= reach && Nazgul.class.isInstance(c)) {
				return c; // if there is a Creature that this
				          // Hobbit can reach, return it so
				          // it can attack it
			}
			
		}
		
		return null; // nothing found: return a null Creature
	}
	
	@Override
	public boolean canReplicate() {
		
		// do not allow reproduction if next to a wall
		if(this.location[0] == 0 || this.location[0] == 99) {
			return false;
		}
		else if(this.location[1] == 0 || this.location[1] == 99) {
			return false;
		}
		
		// rule: allow reproduction only if this Hobbit meets
		// the requirements below, aka. is it healthy?
		else if(this.sustenance >= 3 && this.health >= 4 && this.turnsSinceReproduction > 3) {
			// is there space to put the baby?
			if(canMoveUp || canMoveRight || canMoveDown || canMoveLeft) {
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false;
		}
	}
	
	// return reach
	public int getReach() {
		return this.reach;
	}
	
}
