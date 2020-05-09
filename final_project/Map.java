// Patrick Temple
// Prof. Whitener
// CSCI165
// 4 May 2020

// Final Project: Map
// Purpose: to hold the objects and the 

// imported classes
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
	
	// class features
	private ArrayList<Creature> occupants = new ArrayList<Creature>(); // holds the information about Creatures on a map
	private ArrayList<Item> items = new ArrayList<Item>();             // holds the information about Items on a map
	
	// constructors
	
	public Map() { /* blank constructor */ }
	
	// overloaded constructor
	public Map(ArrayList<Creature> occupants, ArrayList<Item> items) {
		this.occupants = occupants;
		this.items = items;
	}
	
	// see if a space is occupied by a Creature
	// (not an Item, that has its own function)
	public boolean isSpaceOccupied(int[] location) {
		
		for(int i = 0; i < occupants.size(); i++) {
			Creature temp = occupants.get(i);
			int[] locationOfTemp = temp.getLocation();
			if(Arrays.equals(location, locationOfTemp)) {
				return true;
			}
		}
		return false;
		
	}
	
	// see if a space has an item
	public boolean doesSpaceHaveItem(int[] location) {
		
		for(int i = 0; i < items.size(); i++) {
			Item temp = items.get(i);
			int[] locationOfTemp = temp.getLocation();
			if(Arrays.equals(location, locationOfTemp)) {
				return true;
			}
		}
		return false;
		
	}
	
	// get item from location
	public Item getItem(int[] location) {
		
		for(int i = 0; i < items.size(); i++) {
			Item temp = items.get(i);
			int[] locationOfTemp = temp.getLocation();
			if(Arrays.equals(location, locationOfTemp)) {
				return temp;
			}
		}
		return null;
		
	}
	
	// delete item after it is collected
	public void deleteItem(int[] location) {
		for(int i = 0; i < items.size(); i++) {
			Item temp = items.get(i);
			int[] locationOfTemp = temp.getLocation();
			if(Arrays.equals(location, locationOfTemp)) {
				items.remove(i);
				return;
			}
		}
	}
	
	// find neighbors in a certain radius
	public ArrayList<Creature> findNeighbors(int[] location, int radius) {
		
		// found neighbors go here
		ArrayList<Creature> results = new ArrayList<Creature>();
		
		for(int i = 0; i < occupants.size(); i++) {
			Creature temp = occupants.get(i);
			int[] tempLocation = temp.getLocation();
			
			// if it finds the Creature at that location,
			// skip ahead to the next loop iteration
			if(Arrays.equals(location, tempLocation)) {
				continue;
			}
			
			// calculate difference, and if inside radius,
			// add to arraylist
			int differenceOfY = Math.abs(tempLocation[1] - location[1]);
			int differenceOfX = Math.abs(tempLocation[0] - location[0]);
			if(differenceOfX <= radius && differenceOfY <= radius) {
				results.add(temp);
			}
		}
		
		return results;
		
	}
	
	// remove dead Creatures
	public void removeDeadCreatures() {
		
		int index = 0;
		while(index < occupants.size()) {
			
			Creature temp = occupants.get(index);
			if(temp.isDead()) {
				occupants.remove(temp);
			}
			else {
				index++;
			}
			
		}
	}
	
}
