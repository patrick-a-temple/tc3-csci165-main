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

public abstract class Creature implements Comparable<Creature>{
	
	// class features
	
	// location based on a 100 * 100 map
	// (actual window will be 500 * 500)
	// in order of x, y
	protected int[] location = new int[2]; // location of Creature
	
	protected ArrayList<Item> inventory = new ArrayList<Item>(); // a list of all items a Creature has
	
	// stats
	protected int health;                 // HP for the Creature
	protected int sustenance;             // consider as Hunger (Hobbit) or time
	                                      // since a Nazgul has stabbed a Hobbit
	protected int offense;                // attack stat for Creature that
	                                      // determines how many HP a hit gives
	                                      // the other type of Creature
	protected int defense;                // how much of an attack a Creature can
	                                      // repel
	protected int radiusSize;             // radius a Creature can detect another
	                                      // Creature inside of
	protected int turnsSinceReproduction; // time since a Creature has reproduced
	protected boolean canCheckGround;     // notes if Creature can check ground this turn
	
	
	
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
		if(health >= 0) {
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
	
	// abstract functions defined in
	// implemented classes
	
	public abstract void chooseAction(ArrayList<Creature> neighborData);
	
	public abstract void move();
	
	public abstract void attack(Creature victim);
	
	public abstract Creature replicate(); // needs neighborhood data
	
	public abstract void stay();
	
	public abstract Color color();
	
	public abstract void getItem(Item collectedItem);
	
	public abstract boolean canReplicate();
	
	// overridden function: compare by location of
	// map in a lexicograpical fashion
	// (ie. (9, 9) > (0, 0))
	@Override
	public int compareTo(Creature other) {
		int[] otherLocation = other.getLocation();
		return Arrays.compare(this.location, otherLocation);
	}
}
