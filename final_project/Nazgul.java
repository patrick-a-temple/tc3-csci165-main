import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Nazgul extends Creature {
	
	
	// class features
	
	// a Nazgul can attack a Hobbit from
	// anywhere inside of its radius, and then
	// move towards the Hobbit it is attacking.
	int[] victimLocation;
	
	
	
	// constructors
	public Nazgul() { super(); } // blank constructor
	
	public Nazgul(int[] location, int health, int offense, int defense) {
		super(location, health, offense, defense);
		this.sustenance = 8;
		this.radiusSize = 2;
	}
	
	// special copy constructor
	private Nazgul(Nazgul parent, int[] location) {
		super(location, 10, parent.offense, parent.defense); // copy parent's offense
		                                                     // and defense
		this.sustenance = 7;
		this.radiusSize = parent.radiusSize; // copy sight radius from parent
		turnsSinceReproduction = 0;
	}
	
	private void setVictimLocation(int[] vLocation) {
		this.victimLocation = vLocation;
	}
	
	private void resetSustenance() {
		this.sustenance = 8;
	}
	
	// see if this Nazgul is directly next to the
	// victim
	private boolean isNextToVictim() {
		
		// get differences between locations
		int yDiff = victimLocation[1] - this.location[1];
		int xDiff = victimLocation[0] - this.location[0];
		
		if(Math.abs(yDiff) <= 1 || Math.abs(xDiff) <= 1) {
			// if victim is above
			if(xDiff == 0 && yDiff == -1) {
				return true;
			}
			// if victim is to the right
			else if(xDiff == 1 && yDiff == 0) {
				return true;
			}
			// if victim is below
			else if(xDiff == 0 && yDiff == 1) {
				return true;
			}
			// if victim is to the left
			else if(xDiff == -1 && yDiff == 0) {
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
	
	// move closer to a Hobbit that 
	private void moveCloserToHobbit(ArrayList<Creature> neighborData) {
		
		boolean clearAbove = true;
		boolean clearRight = true;
		boolean clearBelow = true;
		boolean clearLeft  = true;
		
		// calculate locations around this Hobbit,
		// and see if they are outside if the radius
		int xDiff = 0;
		int yDiff = 0;
		
		
		int[] locationAboveVictim   = { this.victimLocation[0], this.victimLocation[1] - 1 };
		yDiff = locationAboveVictim[1] - this.victimLocation[1];
		xDiff = locationAboveVictim[0] - this.victimLocation[0];
		
		if(Math.abs(xDiff) > this.radiusSize || Math.abs(yDiff) > this.radiusSize) {
			clearAbove = false;
		}
		
		int[] locationRightOfVictim = { this.victimLocation[0] + 1, this.victimLocation[1] };
		yDiff = locationRightOfVictim[1] - this.victimLocation[1];
		xDiff = locationRightOfVictim[0] - this.victimLocation[0];
		
		if(Math.abs(xDiff) > this.radiusSize || Math.abs(yDiff) > this.radiusSize) {
			clearRight = false;
		}
		
		int[] locationBelowVictim   = { this.victimLocation[0], this.victimLocation[1] + 1 };
		yDiff = locationBelowVictim[1] - this.victimLocation[1];
		xDiff = locationBelowVictim[0] - this.victimLocation[0];
		
		if(Math.abs(xDiff) > this.radiusSize || Math.abs(yDiff) > this.radiusSize) {
			clearBelow = false;
		}
		
		int[] locationLeftOfVictim  = { this.victimLocation[0] - 1, this.victimLocation[1] };
		yDiff = locationLeftOfVictim[1] - this.victimLocation[1];
		xDiff = locationLeftOfVictim[0] - this.victimLocation[0];
		
		if(Math.abs(xDiff) > this.radiusSize || Math.abs(yDiff) > this.radiusSize) {
			clearLeft = false;
		}
		
		
		// if a certain option is out of the
		// radius, do not allow it to go there
		// in case there is a Hobbit the Nazgul can see
		// with the neighborData radius restrictions
		
		// for upper wall
		
		// if the victim Hobbit was next to a wall,
		// do not allow it to try to move next to
		// an invalid location
		
		
		if(this.victimLocation[1] == 0) {
			clearAbove = false;
		}
		
		if(this.victimLocation[0] == 99) {
			clearRight = false;
		}
		
		if(this.victimLocation[1] == 99) {
			clearBelow = false;
		}
		
		if(this.victimLocation[0] == 0) {
			clearBelow = false;
		}
		
		// finally check if there are any Creatures
		// that match the location of the places next
		// to the victim
		for(Creature c : neighborData) {
			
			// skip if it finds the the victim
			// or this Nazgul
			if(Arrays.equals(this.victimLocation, c.getLocation()) ||
				Arrays.equals(this.location, c.getLocation())) {
				continue;
			}
			
			int[] tempLocation = c.getLocation();
			
			if(Arrays.equals(locationAboveVictim, tempLocation)) {
				clearAbove = false;
				continue;
			}
			if(Arrays.equals(locationRightOfVictim, tempLocation)) {
				clearRight = false;
				continue;
			}
			if(Arrays.equals(locationBelowVictim, tempLocation)) {
				clearBelow = false;
				continue;
			}
			if(Arrays.equals(locationLeftOfVictim, tempLocation)) {
				clearBelow = false;
				continue;
			}
			
		}
		
		if(!clearAbove && !clearRight && !clearBelow && !clearLeft) {
			System.out.println("A Nazgul could not move because the Hobbit it attacked was blocked");
			return;
		}
		
		if(clearAbove) {
			this.setLocation(locationAboveVictim);
			return;
		}
		if(clearRight) {
			this.setLocation(locationRightOfVictim);
			return;
		}
		if(clearBelow) {
			this.setLocation(locationBelowVictim);
			return;
		}
		if(clearLeft) {
			this.setLocation(locationLeftOfVictim);
			return;
		}
		
		
		
	}
	
	@Override
	public void chooseAction(ArrayList<Creature> neighborData) {
		
		// if the Nazgul could not pick up items on the
		// last turn, make it so it can tentatively
		
		this.canCheckGround = true;
		
		// see what ways a Nazgul can move
		refreshDirectSpaces(neighborData);
		
		// see if this Nazgul can find the last
		// Hobbit it attacked, or a different Hobbit
		Creature hobbitFound = seekEnemy(neighborData);
		
		if(hobbitFound != null) {
			attack(hobbitFound);
			setVictimLocation(hobbitFound.getLocation());
			resetSustenance();
			this.canCheckGround = false; // rule: cannot pick up any
			                             // item after attacking
			// rule: Nazgul can move after attacking,
			// and that's about to be handled right now -
			// move close to that particular Hobbit
			if(hobbitFound.isDead()) {
				System.out.println("A Nazgul killed a Hobbit");
			}
			else if(isNextToVictim()) {
				System.out.println("The Nazgul is next to its victim");
			}
			else {
				moveCloserToHobbit(neighborData);
			}
			
			
		}
		
		else if(canMoveUp || canMoveRight || canMoveDown || canMoveLeft) {
			
			// make flags for if there is a Hobbit or Nazgul nearby
			boolean friendUp = false;
			boolean friendRight = false;
			boolean friendDown = false;
			boolean friendLeft = false;
			
			
			
			int friendCount = 0; // for holding the number of
			                     // fellow Hobbits are nearby
			
			for(Creature c : neighborData) {
				
				int[] tempLocation = c.getLocation();
				
				// skip this Nazgul's location
				if(Arrays.equals(this.location, tempLocation)) {
					continue;
				}
				
				// get differences between the xs and ys
				int xDiff = tempLocation[0] - this.location[0];
				int yDiff = tempLocation[1] - this.location[1];
				
				int xTravel = Math.abs(xDiff);
				int yTravel = Math.abs(yDiff);
				
				// in case the other Creature is outside of
				// this Nazgul's radius, skip it
				if(xTravel > this.radiusSize || yTravel > this.radiusSize) {
					continue;
				}
				
				// Creature found on left
				if(xDiff < 0) {
					if(Nazgul.class.isInstance(c)) {
						friendCount++;
						friendLeft = true;
					}
				}
				
				// Creature on the right
				if(xDiff > 0) {
					if(Nazgul.class.isInstance(c)) {
						friendCount++;
						friendRight = true;
					}
				}
				
				// Creature above
				if(yDiff < 0) {
					if(Nazgul.class.isInstance(c)) {
						friendCount++;
						friendUp = true;
					}
				}
				
				// Creature below
				if(yDiff > 0) {
					if(Nazgul.class.isInstance(c)) {
						friendCount++;
						friendDown = true;
					}
				}
			}
			
			// if no other Creatures found, move randomly
			if(friendCount == 0 || friendCount == 4 ) {
				System.out.println("Nazgul moving in random direction");
				moveRandomDirection();
				return;
			}
			
			
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
			
		}
		
		else {
			System.out.println("A Nazgul is blocked in, it can't move");
			this.canCheckGround = false;
		}
		
	}
	
	@Override
	public void move(direction d) {
		System.out.println("A Nazgul is moving");
		
		if(d == direction.UP) {
			// if at top edge of map do
			// not allow the Nazgul to move
			if(this.location[1] == 0) {
				System.out.println("A Nazgul cannot walk through the north wall");
			}
			else {
				this.location[1]--;
			}
		}
		else if(d == direction.RIGHT) {
			if(this.location[1] == 99) {
				System.out.println("A Nazgul cannot walk through the east wall");
			}
			else {
				this.location[0]++;
			}
		}
		else if(d == direction.DOWN) {
			if(this.location[1] == 99) {
				System.out.println("A Nazgul cannot walk through the south wall");
			}
			else {
				this.location[1]++;
			}
		}
		else if(d == direction.LEFT) {
			if(this.location[0] == 0) {
				System.out.println("A Nazgul cannot walk through the west wall");
			}
			else {
				this.location[0]--;
			}
		}
	}

	@Override
	public void attack(Creature victim) {
		System.out.println("A Nazgul is attempting to attack a Hobbit");
		
		Random rng = new Random();
		
		// "roll the dice" to see if the Nazgul gets
		// a critcal hit, if so, deal twice the damage
		
		// rule: critical hits do not nullify a Hobbit's defense
		
		int diceRoll = Math.abs(rng.nextInt() % 6) + 1;
		int damage = 0;
		
		if(diceRoll == 6) {
			System.out.println("A Nazgul landed a critical hit!");
			damage = (2 * offense) - victim.getDefense();
			victim.alterHealth(damage);
		}
		else if(this.offense - victim.getDefense() <= 0) {
			System.out.println("A Nazgul could not attack a Hobbit");
		}
		else {
			damage = this.offense - victim.getDefense();
			victim.alterHealth(damage);
		}
		
	}

	@Override
	public Creature replicate() {
		System.out.println("A Nazgul is attempting to replicate");
		
		int reproduceHere[] = { 0, 0 }; // filler location,
		                                // should not be placed here
		
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
		
		Nazgul baby = new Nazgul(this, reproduceHere); // call the copy constructor using
		                                               // this Nazgul as the "parent"
		this.health--;                   // decreasse health and 
		this.turnsSinceReproduction = 0; // reset the counter for reproduction turns
		return baby;
	}

	@Override
	public void stay() {
		// rule: Nazguls die at zero sustenance
		sustenance--;
		turnsSinceReproduction++;
		
		if(this.sustenance <= 0) {
			this.health = 0;
		}
	}

	@Override
	public Color color() {
		if(this.sustenance >= 4) {
			return Color.BLACK;
		}
		else if(this.sustenance >= 2) {
			return Color.GRAY;
		}
		else {
			return Color.LIGHT_GRAY;
		}
	}

	@Override
	public void getItem(Item collectedItem) {
		System.out.println("A Nazgul is getting an item");
		Item.itemType ciType = collectedItem.getCurrentItemType();
		int modRate = collectedItem.getModifyingRate();
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
		/* Rule:
		 * Nazgul cannot eat a mortal's food, therefore
		 * it cannot use NOURISHMENT type items, nor can
		 * it use a REACH item as it can attack a Hobbit from
		 * anywhere inside of its radius.                     */
		inventory.add(collectedItem);
	}
	
	@Override
	public Creature seekEnemy(ArrayList<Creature> neighborData) {
		
		// rule: Nazgul can attack any Hobbit within
		// its line of sight
		
		// rule: if it finds the Hobbit that it attacked last
		// at the same exact location, default to that option first
		
		//Creature found = null;
		ArrayList<Creature> foundHobbit = new ArrayList<Creature>();
		
		for(Creature c : neighborData) {
			if(Arrays.equals(this.location, c.getLocation())) {
				continue;
			}
			
			int[] tempLocation = c.getLocation();
			
			// if it finds the last victim it attacked
			// at that same location, default to that
			if(Hobbit.class.isInstance(c) && Arrays.equals(this.victimLocation, tempLocation)) {
				return c;
			}
			// if it finds a Hobbit not sharing the same
			// location as the one this Nazgul previously
			// attacked, add it to an ArrayList
			else if(Hobbit.class.isInstance(c)) {
				foundHobbit.add(c);
			}
			
		}
		
		// if no Hobbits found return null
		if(foundHobbit.isEmpty()) {
			return null;
		}
		// if there is only one, return that Hobbit
		else if(foundHobbit.size() == 1) {
			return foundHobbit.get(0);
		}
		// else, pick a random Hobbit from the ArrayList
		// if there are multiple Hobbits
		else {
			Random rng = new Random();
			int pick = Math.abs(rng.nextInt() % (foundHobbit.size() - 1));
			return foundHobbit.get(pick);
		}
		
		
		//return null; // nothing found: return null
	}

	@Override
	public boolean canReplicate() {
		if(this.sustenance >= 2 && this.health >= 3 && this.turnsSinceReproduction >= 8) {
			// can it move?
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

}
