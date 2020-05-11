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
	private static Map session = new Map(occupants, items);
	
	final Timer timer = new Timer(DELAY, this);
	
	// constructor
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
	
	// where to put functions that change Creature state
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
			Creature c = occupants.get(index);
			ArrayList<Creature> neighborData = session.findNeighbors(c.getLocation(), c.getRadiusSize());
			c.chooseAction(neighborData);
			c.stay();
			if(c.getCheckGroundStatus() && session.doesSpaceHaveItem(c.getLocation())) {
				Item item = session.getItem(c.getLocation());
				c.getItem(item);
				session.deleteItem(c.getLocation());
			}
			if(c.canReplicate()) {
				Creature baby = c.replicate();
				occupants.add(baby);
			}
			index++;
		}
		session.removeDeadCreatures();
		
		repaint(); // re-render the window
		
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		redrawCreatures(g);
		
	}
	
	private void redrawCreatures(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		for(Creature c : occupants) {
			int[] creatureLocation = c.getLocation();
			
			g2d.setColor(c.color());
			
			g2d.fillRect(creatureLocation[0] * 5, creatureLocation[1] * 5, 5, 5);
		}
		
	}
	
	// reset window title when a turn is completed
	private void resetWindowTitle() {
		window.setTitle("Hobbits Vs. Nazgul | Elapsed: " + ELAPSED + " min.");
	}
	
	public static void seedWorldWithCreatures(int hobbitsDesired, int nazgulDesired) {
		
		Random rng = new Random();
		
		// make desired number of Hobbits
		
		for(int i = 0; i < hobbitsDesired; i++) {
			
			int[] hobbitLocation = { 0, 0 };
			do {
				hobbitLocation[0] = (Math.abs(rng.nextInt() % 100));
				hobbitLocation[1] = (Math.abs(rng.nextInt() % 100));
			} while(session.isSpaceOccupied(hobbitLocation));
			
			int healthStat  = Math.abs(rng.nextInt() % 9) + 6;
			int attackStat  = Math.abs(rng.nextInt() % 9) + 1;
			int defenseStat = Math.abs(rng.nextInt() % 9) + 1;
			
			Hobbit temp = new Hobbit(hobbitLocation, healthStat, attackStat, defenseStat);
			occupants.add(temp);
			
		}
		
		// make desired number of Nazgul
		
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
		
		// every fifth horizontal line (y = 0; y = 3 ...)
		// add a health item or a nourishment item
		// every fifth line will have magical items
		
		// save y = 20 to 29 and 70 to 79 for some MagicalItems
		
		Random rng = new Random();
		
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
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to Patrick Temple's version of \n\n" +
		                   "Hobbits vs. Nazgul\n");
		
		// get number of Hobbits to add in
		System.out.print("Enter the number of Hobbits you want added to the map: ");
		int hobbitsDesired = reader.nextInt();
		System.out.print("Enter the number of Nazgul you want added to the map: ");
		int nazgulDesired = reader.nextInt();
		
		
		
		seedWorldWithCreatures(hobbitsDesired, nazgulDesired);
		seedWorldWithItems();
		
		System.out.println("Let the games begin! (opening a new window...)");
		
		reader.close();
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Driver ex = new Driver();
				ex.setVisible(true);
			}
			
		});
		
	}
}
