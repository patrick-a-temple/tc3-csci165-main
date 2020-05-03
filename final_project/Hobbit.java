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

public class Hobbit extends Creature {
	
	// if needed class features go here
	private int reach; // how far a Hobbit can reach out to
	                   // attack a Nazgul (if a Hobbit has a higher
	                   // reach than its radius, it still cannot attack
	                   // outside its radius)
	
	// blank constructor
	public Hobbit() { super(); } // blank constructor
	
	public Hobbit(int[] location, int health, int offense, int defense) {
		super(location, health, offense, defense);
		this.sustenance = 6;
		this.radiusSize = 1;
	}
	
	@Override
	public void chooseAction(ArrayList<Creature> neighborData) {
		System.out.println("A Hobbit is trying to think of its next move");
	}

	@Override
	public void move() {
		System.out.println("A hobbit is attempting to move");
	}

	@Override
	public void attack(Creature victim) {
		System.out.println("A hobbit is attempting to attack a Nazgul");
	}

	@Override
	public Creature replicate() {
		System.out.println("A hobbit is attempting to replicate");
		return null;
	}

	@Override
	public void stay() {
		System.out.println("A hobbit is staying");
	}

	@Override
	public Color color() {
		System.out.println("A hobbit is changing color");
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

	@Override
	public void getItem(Item collectedItem) {
		System.out.println("A Hobbit is getting an item");
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
		else if(ciType == Item.itemType.NOURISHMENT) {
			this.sustenance += modRate;
			this.health += 3;
		}
		else if(ciType == Item.itemType.REACH) {
			this.reach += modRate;
		}
		inventory.add(collectedItem);
	}

	@Override
	public boolean canReplicate() {
		System.out.println("This Hobbit is seeing if it can replicate");
		if(this.sustenance >= 3 && this.health >= 4 &&
				this.turnsSinceReproduction > 3) {
			return true;
		}
		else {
			return false;
		}
	}

}
