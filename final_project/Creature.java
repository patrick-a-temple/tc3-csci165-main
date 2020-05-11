// Patrick Temple
// Prof. Whitener
// CSCI165
// 4 May 2020

// Final Project: Creature
// Purpose: to define the bare minimum
// features of a Creature

// class features

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Creature implements Comparable<Creature>{
	
	// class features
	
	protected enum direction { UP, RIGHT, DOWN, LEFT }; // for telling move what way
	                                                    // to go in a clean way
	
	// location based on a 100 * 100 map
	// (actual window will be 500 * 500)
	// in order of x, y
	protected int[] location = new int[2]; // location of Creature
	
	protected ArrayList<Item> inventory = new ArrayList<Item>(); // a list of all items a Creature has
	
	// stats
	protected int health;                    // HP for the Creature
	protected int sustenance;                // consider as Hunger (Hobbit) or time
	                                         // since a Nazgul has stabbed a Hobbit
	protected int offense;                   // attack stat for Creature that
	                                         // determines how many HP a hit gives
	                                         // the other type of Creature
	protected int defense;                   // how much of an attack a Creature can
	                                         // repel
	protected int radiusSize;                // radius a Creature can detect another
	                                         // Creature inside of
	protected int turnsSinceReproduction;    // time since a Creature has reproduced
	protected boolean canCheckGround = true; // notes if Creature can check ground this turn
	
	protected boolean canMoveUp;          // note the directions a Creature can move in
	protected boolean canMoveRight;
	protected boolean canMoveDown;
	protected boolean canMoveLeft;
	
	
	
	public Creature() { /* blank constructor */ }
	
	// overloaded constructor
	public Creature(int[] location, int health, int offense, int defense) {
		setLocation(location);
		this.health = health;
		this.offense = offense;
		this.defense = defense;
		this.turnsSinceReproduction = 0;
	}
	
	
	// methods
	// getters and setters
	public int[] getLocation() {
		return location;
	}

	public void setLocation(int[] location) {
		this.location = location;
	}
	
	
	public int getDefense() {
		return defense;
	}
	
	public int getRadiusSize() {
		return radiusSize;
	}
	
	// subtract health as the result of an
	// attack (use negative value if other Creature is
	// trying to heal this Creature)
	public void alterHealth(int change) {
		if(this.health - change <= 0) {
			this.health = 0;
		}
		else {
			this.health -= change;
		}
	}
	
	
	// returns a boolean value that determines
	// if a Creature is dead
	public boolean isDead() {
		if(health <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// return a value that says if a Creature
	// can check the ground this turn
	public boolean getCheckGroundStatus() {
		return canCheckGround;
	}
	
	// move in a random direction - this should ONLY
	// be called if there are no friends or foes nearby
	public void moveRandomDirection() {
		
		Random rng = new Random();
		int roll = Math.abs(rng.nextInt() % 3) + 1;
		direction way = direction.UP; // will tell move() what way to go
		if(roll == 1) {
			way = direction.UP;
		}
		if(roll == 2) {
			way = direction.RIGHT;
		}
		if(roll == 3) {
			way = direction.DOWN;
		}
		if(roll == 4) {
			way = direction.LEFT;
		}
		
		move(way);
		
	}
	
	// return a boolean array that symbolizes the spots one step ahead
	// are full or empty in the following order:
	// up, right, down, left
	public void refreshDirectSpaces(ArrayList<Creature> neighborData) {
		
		// set each to true unless it meets the
		// criteria for a space being blocked
		this.canMoveUp = true;
		this.canMoveRight = true;
		this.canMoveDown = true;
		this.canMoveLeft = true;
		
		// calculate the locations of spaces that
		// are one step away in non-diagonal directions
		int[] locationUp    = { this.location[0], (this.location[1] - 1) };
		int[] locationRight = { (this.location[0] + 1), this.location[1] };
		int[] locationDown  = { this.location[0], (this.location[1] + 1) };
		int[] locationLeft  = { (this.location[0] - 1), this.location[1] };
		
		for(Creature c : neighborData) {
			
			// skips if the neighborData contained this
			// Creature's location
			if(Arrays.equals(this.location, c.getLocation())) {
				continue;
			}
			
			// check if space directly above is occupied
			if(Arrays.equals(locationUp, c.getLocation())) {
				this.canMoveUp = false; // if it is occupied mark false
			}
			
			// do the same with the space to
			// the right of this Hobbit, as well
			// as below and to the left
			if(Arrays.equals(locationRight, c.getLocation())) {
				this.canMoveRight = false;
			}
			
			if(Arrays.equals(locationDown, c.getLocation())) {
				this.canMoveDown = false;
			}
			
			if(Arrays.equals(locationLeft, c.getLocation())) {
				this.canMoveLeft = false;
			}
			
		}
		
		
	}
	
	// abstract functions defined in
	// implemented classes
	
	public abstract void chooseAction(ArrayList<Creature> neighborData);
	
	public abstract void move(direction d);
	
	public abstract void attack(Creature victim);
	
	public abstract Creature replicate();
	
	public abstract void stay();
	
	public abstract Color color();
	
	public abstract void getItem(Item collectedItem);
	
	public abstract boolean canReplicate();
	
	public abstract Creature seekEnemy(ArrayList<Creature> neighborData);
	
	// overridden function: compare by location of
	// map in a lexicograpical fashion
	// (ie. (9, 9) > (0, 0))
	@Override
	public int compareTo(Creature other) {
		int[] otherLocation = other.getLocation();
		return Arrays.compare(this.location, otherLocation);
	}
}
