// Patrick Temple
// Prof. Whitener
// CSCI165
// 4 May 2020

// Final Project: Driver
// Purpose: To be the front-end engine of
// the game

// drawing framework based on
// https://stackoverflow.com/q/22072796

// imported classes:
// for creating and managing lists of Items
// and creatures

import java.util.Random;    // for random number generation
import java.util.Scanner;
import java.util.ArrayList; // for storing objects into arrays
import java.util.Arrays;
import java.util.Collections;

// for making a window that the Creatures
// appear on, and rendering them to that window

import java.awt.Color;                // to color shapes
import java.awt.EventQueue;           // to start the drawing
import java.awt.Graphics;             // to manage graphics
import java.awt.Graphics2D;
import javax.swing.JFrame;            // to make the window
import javax.swing.JPanel;            // that is drawed in
import java.awt.event.ActionEvent;    // to manage when the program redraws 
import java.awt.event.ActionListener; // and renders to the window/screen
import javax.swing.Timer;             // for re-drawing after every second


public class Driver extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JFrame window = new JFrame();           // for managing the window
	                                                // properties
	private static final int DELAY = 1000;          // amount of time that the program
	                                                // has to stop for to complete one turn
	                                                // and to redraw everything
	private static int ELAPSED = 0;                 // for holding how many "minutes" have
	                                                // passed in the simulation
	
	private static ArrayList<Creature> occupants = new ArrayList<Creature>(); // for holding occupants
	private static ArrayList<Item> items = new ArrayList<Item>();             // for holding items
	private static Map session = new Map(occupants, items);                   // for holding both
	                                                                          // the above ArrayLists
	
	final Timer timer = new Timer(DELAY, this); // for making the program interact
	                                            // with the world/creatures, and then
	                                            // waiting one second
	
	// constructor that starts the drawing
	public Driver() {
		super();
		initUI();
		
		timer.start(); // start the timer
	}
	
	// set up the window that the program will draw into
	private void initUI() {
		window.add(this);
		
		
		resetWindowTitle(); // set the title
		
		// configure sizing and positions
		window.setSize(501, 529);
		window.setLocationRelativeTo(null);                    // no default position
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close when the user X's out
		window.setVisible(true);                               // make the window visible
		
	}
	
	// the engine of the program that can interact
	// with all of the classes - runs every second
	@Override
	public void actionPerformed(final ActionEvent e) {
		
		ELAPSED++;          // increment elapsed time
		resetWindowTitle(); // reset the window title
		
		Collections.sort(occupants); // sort the ArrayList by order of
		                             // lexicographical location (that is,
		                             // (0, 0) < (0, 1) < (1, 0)
		
		// make Creatures move around and attack
		
		
		int index = 0;
		
		while(index < occupants.size()) {
			Creature c = occupants.get(index); // get the next creature
			
			// get the neighbor data for chooseAction
			ArrayList<Creature> neighborData = session.findNeighbors(c.getLocation(), c.getRadiusSize());
			c.chooseAction(neighborData); // let the Creature decide what to do
			c.stay();                     // decrement sustenance and increment
			                              // turns since reproduction
			
			// if there is an item, and the Creature is allowed
			// to pick it up, give it to the Creature
			if(c.getCheckGroundStatus() && session.doesSpaceHaveItem(c.getLocation())) {
				Item item = session.getItem(c.getLocation());
				c.getItem(item);
				session.deleteItem(c.getLocation()); // delete off the map so another
				                                     // Creature canot pick up the same item
			}
			
			// if this Creature can replicate, allow it
			// to do so, and add it to the map
			if(c.canReplicate()) {
				Creature baby = c.replicate();
				occupants.add(baby);
			}
			index++;
		}
		session.removeDeadCreatures(); // remove any dead Creatures from the map
		
		repaint();                     // re-render the window
		
	}
	
	// call painting functions
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		redrawCreatures(g);     // after each turn redraw the map
		
	}
	
	private void redrawCreatures(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		for(Creature c : occupants) {
			// get the location of the Creature, the color it is
			// supposed to be, and draw it on the map
			int[] creatureLocation = c.getLocation();
			
			g2d.setColor(c.color());
			g2d.fillRect(creatureLocation[0] * 5, creatureLocation[1] * 5, 5, 5);
		}
		
	}
	
	// reset window title when a turn is completed
	private void resetWindowTitle() {
		window.setTitle("Hobbits Vs. Nazgul | Elapsed: " + ELAPSED + " min.");
	}
	
	// add the desire number of Creatures to the Map
	public static void seedWorldWithCreatures(int hobbitsDesired, int nazgulDesired) {
		
		Random rng = new Random();
		
		// make desired number of Hobbits
		
		for(int i = 0; i < hobbitsDesired; i++) {
			
			int[] hobbitLocation = { 0, 0 };
			
			// try to pick an unused location for each Creature
			do {
				hobbitLocation[0] = (Math.abs(rng.nextInt() % 100));
				hobbitLocation[1] = (Math.abs(rng.nextInt() % 100));
			} while(session.isSpaceOccupied(hobbitLocation));
			
			// randomize different health, attack, and defense levels
			int healthStat  = Math.abs(rng.nextInt() % 9) + 6;
			int attackStat  = Math.abs(rng.nextInt() % 9) + 1;
			int defenseStat = Math.abs(rng.nextInt() % 9) + 1;
			
			// create and add the Hobbit
			Hobbit temp = new Hobbit(hobbitLocation, healthStat, attackStat, defenseStat);
			occupants.add(temp);
			
		}
		
		// make desired number of Nazgul - similar to above
		
		for(int i = 0; i < nazgulDesired; i++) {
			
			int[] nazgulLocation = { 0, 0 };
			do {
				nazgulLocation[0] = (Math.abs(rng.nextInt() % 100));
				nazgulLocation[1] = (Math.abs(rng.nextInt() % 100));
			} while(session.isSpaceOccupied(nazgulLocation));
			
			int healthStat  = Math.abs(rng.nextInt() % 9) + 6;
			int attackStat  = Math.abs(rng.nextInt() % 9) + 1;
			int defenseStat = Math.abs(rng.nextInt() % 9) + 1;
			
			Nazgul temp = new Nazgul(nazgulLocation, healthStat, attackStat, defenseStat);
			occupants.add(temp);
			
		}
	}
	
	public static void seedWorldWithItems() {
		
		// save y = 20 to 29 and 70 to 79 for some MagicalItems
		
		Random rng = new Random();
		
		// fill world with health and nourishment items
		for(int y = 0; y < 100; y += 5) {
			if((y >= 70 && y < 80) || (y >= 20 && y < 30)) {
				continue;
			}
			for(int x = 0; x < 100; x++) {
				if(x % 5 < 2) {
					int[] itemLocation = { x, y };
					int itemEffectLevel = Math.abs(rng.nextInt() % 5) + 1;
					Item healthItem = new Item(itemLocation, itemEffectLevel, Item.itemType.HEALTH);
					items.add(healthItem);
				}
				else {
					int[] itemLocation = { x, y };
					int itemEffectLevel = Math.abs(rng.nextInt() % 5) + 1;
					Item foodItem = new Item(itemLocation, itemEffectLevel, Item.itemType.NOURISHMENT);
					items.add(foodItem);
				}
			}
		}
		
		// make offensive items
		for(int y = 1; y < 100; y+= 5) {
			if((y >= 70 && y < 80) || (y >= 20 && y < 30)) {
				continue;
			}
			for(int x = 0; x < 100; x++) {
				int[] itemLocation = { x, y };
				int itemEffectLevel = Math.abs(rng.nextInt() % 5) + 1;
				Item attackItem = new Item(itemLocation, itemEffectLevel, Item.itemType.OFFENSE);
				items.add(attackItem);
			}
		}
		
		// fill world with defensive items
		for(int y = 2; y < 100; y += 5) {
			if((y >= 70 && y < 80) || (y >= 20 && y < 30)) {
				continue;
			}
			for(int x = 0; x < 100; x++) {
				int[] itemLocation = { x, y };
				int itemEffectLevel = Math.abs(rng.nextInt() % 5) + 1;
				Item defensiveItem = new Item(itemLocation, itemEffectLevel, Item.itemType.DEFENSE);
				items.add(defensiveItem);
			}
		}
		
		// fill world with reach and sight items
		for(int y = 3; y < 100; y += 5) {
			if((y >= 70 && y < 80) || (y >= 20 && y < 30)) {
				continue;
			}
			for(int x = 0; x < 100; x++) {
				if(x % 3 == 0) {
					int[] itemLocation = { x, y };
					int itemEffectLevel = Math.abs(rng.nextInt() % 5) + 1;
					Item reachItem = new Item(itemLocation, itemEffectLevel, Item.itemType.REACH);
					items.add(reachItem);
				}
				else {
					int[] itemLocation = { x, y };
					int itemEffectLevel = Math.abs(rng.nextInt() % 5) + 1;
					Item sightItem = new Item(itemLocation, itemEffectLevel, Item.itemType.SIGHT);
					items.add(sightItem);
				}
			}
		}
		
		// fill y = 20 to 29 with magical items
		for(int y = 20; y < 30; y++) {
			for(int x = 0; x < 100; x++) {
				int[] itemLocation = { x, y };
				int itemEffectLevel = Math.abs(rng.nextInt() % 5) + 1;
				int multiplierLevel = Math.abs(rng.nextInt() % 3) + 2;
				if(x % 6 == 0) {
					MagicalItem magicHealer = new MagicalItem(itemLocation, itemEffectLevel, 
							                                  Item.itemType.HEALTH, multiplierLevel);
					items.add(magicHealer);
				}
				else if(x % 6 == 1) {
					MagicalItem magicAttack = new MagicalItem(itemLocation, itemEffectLevel, 
							                                  Item.itemType.OFFENSE, multiplierLevel);
					items.add(magicAttack);
				}
				else if(x % 6 == 2) {
					MagicalItem magicDefense = new MagicalItem(itemLocation, itemEffectLevel,
							                                   Item.itemType.DEFENSE, multiplierLevel);
					items.add(magicDefense);
				}
				else if(x % 6 == 3) {
					MagicalItem magicSightItem = new MagicalItem(itemLocation, itemEffectLevel,
							                                     Item.itemType.SIGHT, multiplierLevel);
					items.add(magicSightItem);
				}
				else if(x % 6 == 4) {
					MagicalItem magicFoodItem = new MagicalItem(itemLocation, itemEffectLevel,
							                                    Item.itemType.NOURISHMENT, multiplierLevel);
					items.add(magicFoodItem);
				}
				else {
					MagicalItem magicReachItem = new MagicalItem(itemLocation, itemEffectLevel,
							                                     Item.itemType.REACH, multiplierLevel);
					items.add(magicReachItem);
				}
			}
		}
		
		// do the same thing as above with y = 70 to 79
		for(int y = 70; y < 80; y++) {
			for(int x = 0; x < 100; x++) {
				int[] itemLocation = { x, y };
				int itemEffectLevel = Math.abs(rng.nextInt() % 5) + 1;
				int multiplierLevel = Math.abs(rng.nextInt() % 3) + 2;
				if(x % 6 == 0) {
					MagicalItem magicHealer = new MagicalItem(itemLocation, itemEffectLevel, 
							                                  Item.itemType.HEALTH, multiplierLevel);
					items.add(magicHealer);
				}
				else if(x % 6 == 1) {
					MagicalItem magicAttack = new MagicalItem(itemLocation, itemEffectLevel, 
							                                  Item.itemType.OFFENSE, multiplierLevel);
					items.add(magicAttack);
				}
				else if(x % 6 == 2) {
					MagicalItem magicDefense = new MagicalItem(itemLocation, itemEffectLevel,
							                                   Item.itemType.DEFENSE, multiplierLevel);
					items.add(magicDefense);
				}
				else if(x % 6 == 3) {
					MagicalItem magicSightItem = new MagicalItem(itemLocation, itemEffectLevel,
							                                     Item.itemType.SIGHT, multiplierLevel);
					items.add(magicSightItem);
				}
				else if(x % 6 == 4) {
					MagicalItem magicFoodItem = new MagicalItem(itemLocation, itemEffectLevel,
							                                    Item.itemType.NOURISHMENT, multiplierLevel);
					items.add(magicFoodItem);
				}
				else {
					MagicalItem magicReachItem = new MagicalItem(itemLocation, itemEffectLevel,
							                                     Item.itemType.REACH, multiplierLevel);
					items.add(magicReachItem);
				}
			}
		}
		
		// every fourth row will not get items
		
	}
	
	// get things started
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to Patrick Temple's version of \n\n" +
		                   "Hobbits vs. Nazgul\n");
		
		// get number of Hobbits and Nazgul to add in
		System.out.print("Enter the number of Hobbits you want added to the map: ");
		int hobbitsDesired = reader.nextInt();
		System.out.print("Enter the number of Nazgul you want added to the map: ");
		int nazgulDesired = reader.nextInt();
		
		// call functions to add Creatures and Nazgul to the map
		seedWorldWithCreatures(hobbitsDesired, nazgulDesired);
		seedWorldWithItems();
		
		System.out.println("Let the games begin! (opening a new window...)");
		
		reader.close();
		
		// start the game
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Driver ex = new Driver();
				ex.setVisible(true);
			}
			
		});
		
	}
}
