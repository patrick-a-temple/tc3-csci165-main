// Patrick Temple
// Prof. Whitener
// CSCI165
// 4 May 2020

// Final Project: Driver
// Purpose: To prove polymorphism works
// on different inherited functions.

// class features

import java.util.Random;    // for random number generation
import java.awt.Color;      // for Colors that a Hobbit can change into
import java.util.ArrayList; // for storing objects into arrays

public class Driver {
	public static void main(String[] args) {
		
		
		Random rng = new Random();
		
		int[] itemLocation = { 0, 0 }; // location as required by Items
		
		// ArrayLists for objects
		ArrayList<Creature> creatures = new ArrayList<Creature>();
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Creature> neighbors = new ArrayList<Creature>();
		
		// filler item
		Item exampleItem = new Item(itemLocation, 2, Item.itemType.HEALTH);
		
		// construct some Hobbits and Nazgul
		for(int i = 1; i <= 3; i++) {
			int locX = Math.abs(rng.nextInt() % 100);
			int locY = Math.abs(rng.nextInt() % 100);
			int[] location = { locX, locY };
			int health = Math.abs(rng.nextInt() % 15) + 1;
			int offense = Math.abs(rng.nextInt() % 6) + 1;
			int defense = Math.abs(rng.nextInt() % 5);
			
			Hobbit h = new Hobbit(location, health, offense, defense);
			creatures.add(h);
		}
		
		for(int i = 0; i < 6; i++) {
			int locX = Math.abs(rng.nextInt() % 100);
			int locY = Math.abs(rng.nextInt() % 100);
			int[] location = { locX, locY };
			int health = Math.abs(rng.nextInt() % 15) + 1;
			int offense = Math.abs(rng.nextInt() % 6) + 1;
			int defense = Math.abs(rng.nextInt() % 5);
			
			Nazgul n = new Nazgul(location, health, offense, defense);
			creatures.add(n);
		}
		
		neighbors.add(creatures.get(0));
		neighbors.add(creatures.get(3));
		
		
		// call each function to prove 
		// early polymorphism
		for(Creature c : creatures) {
			c.chooseAction(neighbors);
			c.move();
			c.attack(c);
			Creature xerox = c.replicate();
			c.stay();
			Color colour = c.color();
			c.getItem(exampleItem);
			boolean yesNo = c.canReplicate();
		}
		
		int[] l = { 10, 10 };
		
		items.add(exampleItem);
		
		Map map = new Map(creatures, items);
		
	}
}
