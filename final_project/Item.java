// Patrick Temple
// Prof. Whitener
// CSCI165
// 4 May 2020

// Final Project: Item
// Purpose: to create a class for items,
// and make a base class for MagicalItem


public class Item {
	
	// class features
	
	protected enum itemType  { HEALTH, OFFENSE, DEFENSE, 
		                       SIGHT, NOURISHMENT, REACH };
	protected int  modifyingRate; // raises/lowers stat by x points
	protected itemType currentItemType; // holds current type of item
	protected int[] location;
	
	public Item() { /* blank constructor */ }
	
	public Item(int[] location, int modifyingRate, itemType it) {
		this.location = location;
		this.modifyingRate = modifyingRate;
		this.currentItemType = it;
	}
	
	// getters and setters
	
	public itemType getCurrentItemType() {
		return currentItemType;
	}

	public int getModifyingRate() {
		return modifyingRate;
	}

	public int[] getLocation() {
		return location;
	}

	public void setLocation(int[] location) {
		this.location = location;
	}
	
}
