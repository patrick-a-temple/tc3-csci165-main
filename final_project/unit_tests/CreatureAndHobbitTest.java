// Patrick Temple
// Prof. Whitener
// CSCI165
// 11 May 2020

// Final Project: CreatureAndHobbitTest
// Purpose: to test both Creature and Hobbit, as
// the Creature class is abstract and cannot have
// its own direct objects

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class CreatureAndHobbitTest {
	
	/* Hobbit specific tests */

	@Test
	void testChooseActionDangerFromAbove() {
		
		// Nazgul from above - test to see if a Hobbit
		// responds by moving down in response to a Nazgul
		// being directly above it
		int[] hobbitLocation = { 9, 9 };
		int[] nazgulLocation = { 9, 7 };
		int[] expectedMovement = { 9, 10 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		Hobbit h = new Hobbit(hobbitLocation, 10, 4, 2);
		Nazgul n = new Nazgul(nazgulLocation, 8, 5, 3);
		
		// give item to enhance view for Hobbit so it
		// does not attempt to attack the Nazgul
		Item glasses = new Item(hobbitLocation, 1, Item.itemType.SIGHT);
		h.getItem(glasses);
		
		world.add(h);
		world.add(n);
		
		h.chooseAction(world);
		int[] actual = h.getLocation();
		assertTrue(Arrays.equals(expectedMovement, actual));
		
	}
	
	@Test
	void testChooseActionDangerFromRight() {
		
		// result: the Hobbit should move left in
		// response to the Nazgul
		
		int[] hobbitLocation = { 9, 9 };
		int[] nazgulLocation = { 11, 9 };
		int[] expectedMovement = { 8, 9 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		Hobbit h = new Hobbit(hobbitLocation, 10, 4, 2);
		Nazgul n = new Nazgul(nazgulLocation, 8, 5, 3);
		
		// give item to enhance view for Hobbit so it
		// does not attempt to attack the Nazgul
		Item glasses = new Item(hobbitLocation, 1, Item.itemType.SIGHT);
		h.getItem(glasses);
		
		world.add(h);
		world.add(n);
		
		h.chooseAction(world);
		int[] actual = h.getLocation();
		assertTrue(Arrays.equals(expectedMovement, actual));
		
	}
	
	@Test
	void testChooseActionDangerFromBelow() {
		
		// result: see that the Hobbit will move up
		// in response to a Nazgul below
		
		int[] hobbitLocation = { 9, 9 };
		int[] nazgulLocation = { 9, 11 };
		int[] expectedMovement = { 9, 8 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		Hobbit h = new Hobbit(hobbitLocation, 10, 4, 2);
		Nazgul n = new Nazgul(nazgulLocation, 8, 5, 3);
		
		// give item to enhance view for Hobbit so it
		// does not attempt to attack the Nazgul
		Item glasses = new Item(hobbitLocation, 1, Item.itemType.SIGHT);
		h.getItem(glasses);
		
		world.add(h);
		world.add(n);
		
		h.chooseAction(world);
		int[] actual = h.getLocation();
		assertTrue(Arrays.equals(expectedMovement, actual));
		
	}
	
	@Test
	void testChooseActionDangerFromLeft() {
		
		// result: the Hobbit should move to the right
		
		int[] hobbitLocation = { 9, 9 };
		int[] nazgulLocation = { 7, 9 };
		int[] expectedMovement = { 10, 9 };
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		Hobbit h = new Hobbit(hobbitLocation, 10, 4, 2);
		Nazgul n = new Nazgul(nazgulLocation, 8, 5, 3);
		
		// give item to enhance view for Hobbit so it
		// does not attempt to attack the Nazgul
		Item glasses = new Item(hobbitLocation, 1, Item.itemType.SIGHT);
		h.getItem(glasses);
		
		world.add(h);
		world.add(n);
		
		h.chooseAction(world);
		int[] actual = h.getLocation();
		assertTrue(Arrays.equals(expectedMovement, actual));
		
	}

	@Test
	void testMove() {
		
		int[] upper = { 50, 0 } ;
		int[] left  = { 0, 50 } ;
		int[] lower = { 50, 99 };
		int[] right = { 99, 50 };
		
		// test that it can get to the the other end
		// of a map if at another end
		
		Hobbit atUpperWall = new Hobbit(upper, 10, 3, 2);
		Hobbit atLeftWall  = new Hobbit(left, 10, 5, 3);
		
		
		atUpperWall.move(Creature.direction.UP);
		assertTrue(Arrays.equals(lower, atUpperWall.getLocation()));
		
		// make the upper Hobbit go back home
		atUpperWall.move(Creature.direction.DOWN);
		assertTrue(Arrays.equals(upper, atUpperWall.getLocation()));
		
		
		atLeftWall.move(Creature.direction.LEFT);
		assertTrue(Arrays.equals(right, atLeftWall.getLocation()));
		
		// make the Hobbit that was at the left wall return
		// to its original location
		atLeftWall.move(Creature.direction.RIGHT);
		assertTrue(Arrays.equals(left, atLeftWall.getLocation()));
		
		// just simply moving around -
		// moving left, up, right, down
		int[] startingLocation  = { 50, 50 };
		int[] expectedStepOne   = { 49, 50 };
		int[] expectedStepTwo   = { 49, 49 };
		int[] expectedStepThree = { 50, 49 };
		
		Hobbit mover = new Hobbit(startingLocation, 10, 2, 1);
		
		mover.move(Creature.direction.LEFT);
		assertTrue(Arrays.equals(expectedStepOne, mover.getLocation()));
		
		mover.move(Creature.direction.UP);
		assertTrue(Arrays.equals(expectedStepTwo, mover.getLocation()));
		
		mover.move(Creature.direction.RIGHT);
		assertTrue(Arrays.equals(expectedStepThree, mover.getLocation()));
		
		mover.move(Creature.direction.DOWN);
		assertTrue(Arrays.equals(startingLocation, mover.getLocation()));
	}

	@Test
	void testAttack() {
		
		// test the results of the attack function
		
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		int[] hobbitLocation = { 10, 10 };
		int[] nazgulLocation = {  9, 10 };
		
		Hobbit attacker = new Hobbit(hobbitLocation, 10, 7, 3);
		world.add(attacker);
		Nazgul victim   = new Nazgul(nazgulLocation, 5, 6, 2);
		world.add(victim);
		
		// let's say the blow was mortal
		attacker.chooseAction(world);
		assertTrue(victim.isDead());
		
	}

	@Test
	void testReplicate() {
		
		int[] parentLocation = { 10, 10 };
		
		// baby should go here by default
		int[] babyLocation = { 10, 9 };
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		
		Hobbit parent = new Hobbit(parentLocation, 10, 5, 3);
		parent.turnsSinceReproduction = 7;
		parent.sustenance = 8;
		parent.refreshDirectSpaces(world);
		world.add(parent);
		
		Creature baby = parent.replicate();
		
		assertTrue(Arrays.equals(babyLocation, baby.getLocation()));
		
	}

	@Test
	void testStay() {
		
		// result: depending on the Hobbit's situation,
		// see how the stats of the Hobbits change
		
		int[] firstLocation = { 1, 1 };
		int[] secondLocation = { 2, 2 };
		int[] thirdLocation  = { 3, 3 };
		
		// a healthy Hobbit
		Hobbit healthy = new Hobbit(firstLocation, 10, 3, 4);
		healthy.sustenance = 6;
		healthy.turnsSinceReproduction = 1;
		healthy.stay();
		
		// test that reproduction count is incremented up,
		// the sustenance went down, and the health has not
		// went down
		assertEquals(2, healthy.turnsSinceReproduction);
		assertEquals(5, healthy.sustenance);
		assertEquals(10, healthy.health);
		
		// do the same with two hungrier Hobbits
		Hobbit starving = new Hobbit(secondLocation, 6, 4, 2);
		starving.sustenance = 0;
		starving.stay();
		assertEquals(-1, starving.sustenance);
		assertEquals(3, starving.health);
		
		
		Hobbit direlyFamished = new Hobbit(thirdLocation, 8, 9, 4);
		direlyFamished.sustenance = -3;
		direlyFamished.stay();
		assertEquals(-4, direlyFamished.sustenance);
		assertEquals(3, direlyFamished.health);
		
	}

	@Test
	void testColor() {
		
		// results: see if the colors are correct for
		// the respective situations
		
		int[] firstLocation = { 1, 1 };
		int[] secondLocation = { 2, 2 };
		int[] thirdLocation  = { 3, 3 };
		
		Hobbit healthy = new Hobbit(firstLocation, 10, 3, 4);
		healthy.sustenance = 6;
		assertEquals(Color.GREEN, healthy.color());
		
		Hobbit hungry = new Hobbit(secondLocation, 6, 4, 2);
		hungry.sustenance = 2;
		assertEquals(Color.YELLOW, hungry.color());
		
		Hobbit starving = new Hobbit(thirdLocation, 5, 3, 1);
		starving.sustenance = 0;
		assertEquals(Color.RED, starving.color());
	}

	@Test
	void testGetItem() {
		
		// result: see if the Hobbit adds the correct values
		// into its stats
		
		int[] filler = { 0, 0 };
		int[] hobbitLocation = { 9, 9 };
		Item healthItem  = new Item(filler, 3, Item.itemType.HEALTH);
		Item offenseItem = new Item(filler, 3, Item.itemType.OFFENSE);
		Item defenseItem = new Item(filler, 3, Item.itemType.DEFENSE);
		Item sightItem   = new Item(filler, 3, Item.itemType.SIGHT);
		Item foodItem    = new Item(filler, 6, Item.itemType.NOURISHMENT);
		Item reachItem   = new Item(filler, 3, Item.itemType.REACH);
		
		Hobbit backpacker = new Hobbit(hobbitLocation, 3, 3, 3);
		
		backpacker.getItem(healthItem);
		assertEquals(6, backpacker.health);
		
		backpacker.getItem(offenseItem);
		assertEquals(6, backpacker.offense);
		
		backpacker.getItem(defenseItem);
		assertEquals(6, backpacker.defense);
		
		backpacker.getItem(sightItem);
		assertEquals(4, backpacker.radiusSize);
		
		backpacker.getItem(foodItem);
		assertEquals(12, backpacker.sustenance);
		assertEquals(9, backpacker.health);
		
		backpacker.getItem(reachItem);
		assertEquals(4, backpacker.getReach());
		
	}
	
	// test a variety of conditions on a Hobbit
	// and make sure it is OK to have it reproduce
	@Test
	void testCanReplicate() {
		
		int[] wellLocation   = { 10, 10 };
		int[] youngLocation  = { 8, 8 };
		int[] hungryLocation = { 6, 6 };
		int[] unwellLocation = { 4, 4 };
		int[] deadLocation   = { 12, 12 };
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		Hobbit well = new Hobbit(wellLocation, 10, 3, 3); // the only Hobbit that
		                                                  // can pass canReplicate()
		well.turnsSinceReproduction = 5;
		
		Hobbit young = new Hobbit(youngLocation, 7, 2, 1); // no turns since reproduction
		young.sustenance = 4;
		Hobbit hungry = new Hobbit(hungryLocation, 7, 3, 4);
		hungry.sustenance = 2;
		hungry.turnsSinceReproduction = 4;
		
		Hobbit unwell = new Hobbit(unwellLocation, 2, 4, 1);
		unwell.sustenance = 1;
		unwell.turnsSinceReproduction = 5;
		
		Hobbit dead = new Hobbit(deadLocation, 0, 1, 1);
		dead.turnsSinceReproduction = 5;
		
		
		world.add(well);
		world.add(young);
		world.add(unwell);
		world.add(hungry);
		world.add(dead);
		
		well.refreshDirectSpaces(world);
		young.refreshDirectSpaces(world);
		hungry.refreshDirectSpaces(world);
		unwell.refreshDirectSpaces(world);
		dead.refreshDirectSpaces(world);
		
		assertTrue(well.canReplicate());
		assertFalse(young.canReplicate());
		assertFalse(hungry.canReplicate());
		assertFalse(unwell.canReplicate());
		assertFalse(dead.canReplicate());
		
	}
	
	// will a Hobbit stop itself from replicating when
	// pinned against a wall?
	@Test
	void testCanReplicateAgainstWall() {
		int[] blockedLocation  = { 50, 0 };
		int[] blockingLeftLoc  = { 49, 0 };
		int[] blockingRightLoc = { 51, 0 };
		int[] blockingBelowLoc = { 50, 1 };
		
		// make a Hobbit that would be otherwise be able to
		// replicate
		ArrayList<Creature> world = new ArrayList<Creature>();
		Hobbit blocked = new Hobbit(blockedLocation, 12, 5, 5);
		blocked.turnsSinceReproduction = 5;
		
		Hobbit blockingLeft  = new Hobbit(blockingLeftLoc, 7, 3, 4);
		Nazgul blockingRight = new Nazgul(blockingRightLoc, 10, 3, 8);
		Nazgul blockingBelow = new Nazgul(blockingBelowLoc, 23, 1, 5);
		
		world.add(blocked);
		world.add(blockingLeft);
		world.add(blockingRight);
		world.add(blockingBelow);
		
		assertFalse(blocked.canReplicate());
		
	}

	@Test
	void testSeekEnemy() {
		
		// result: the Hobbit should see the Nazgul
		
		int[] hobbitLocation = { 5, 5 };
		int[] nazgulLocation = { 4, 5 };
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		Hobbit tester = new Hobbit(hobbitLocation, 6, 2, 5);
		world.add(tester);
		
		assertFalse(tester.seekEnemy(world) != null);
		
		Nazgul enemy = new Nazgul(nazgulLocation, 8, 1, 5);
		world.add(enemy);
		
		assertTrue(tester.seekEnemy(world) != null);
		
	}
	
	/* Creature specific tests */

	@Test
	void testAlterHealth() {
		
		// result: see if Creatures can alter
		// their health correctly
		
		int[] hobbitLocation = { 5, 5 };
		
		Hobbit tester = new Hobbit(hobbitLocation, 10, 5, 3);
		tester.alterHealth(3);
		assertEquals(7, tester.health);
		
		// give mortal blow just to make sure
		// the health does not go below zero
		tester.alterHealth(20);
		assertEquals(0, tester.health);
	}
	
	// see if it can find dead Creatures
	@Test
	void testIsDead() {
		
		// result: two Creatures are dead
		
		int[] hobbitLocation     = { 5, 5 };
		int[] deadHobbitLocation = { 3, 3 };
		int[] deadNazgulLocation = { 7, 7 };
		Hobbit alive      = new Hobbit(hobbitLocation, 10, 5, 3);
		Hobbit deadHobbit = new Hobbit(deadHobbitLocation, 0, 3, 1);
		Nazgul deadNazgul = new Nazgul(deadNazgulLocation, 0, 7, 5);
		
		assertFalse(alive.isDead());
		assertTrue(deadHobbit.isDead());
		assertTrue(deadNazgul.isDead());
		
	}

	@Test
	void testGetCheckGroundStatus() {
		
		int[] hobbitLocation = { 5, 5 };
		Hobbit tester = new Hobbit(hobbitLocation, 10, 5, 3);
		// canCheckGround should automatically
		// be true
		
		assertTrue(tester.getCheckGroundStatus());
		tester.canCheckGround = false;
		assertFalse(tester.getCheckGroundStatus());
	}
	
	// gradually increase the blocked spaces on
	// one Hobbit and see where it can and can't move
	@Test
	void testRefreshDirectSpaces() {
		
		int[] hobbitLocation = { 9, 9 };
		int[] upperLocation  = { 9, 8 };
		int[] leftLocation   = { 8, 9 };
		int[] rightLocation  = { 10, 9 };
		int[] lowerLocation  = { 9, 10 };
		ArrayList<Creature> world = new ArrayList<Creature>();
		
		Hobbit h = new Hobbit(hobbitLocation, 10, 4, 2);
		world.add(h);
		h.refreshDirectSpaces(world);
		assertTrue(h.canMoveUp && h.canMoveRight && h.canMoveDown && h.canMoveLeft);
		
		Hobbit up = new Hobbit(upperLocation, 6, 8, 4);
		world.add(up);
		h.refreshDirectSpaces(world);
		assertFalse(h.canMoveUp);
		
		Hobbit down = new Hobbit(lowerLocation, 6, 8, 4);
		world.add(down);
		assertFalse(h.canMoveUp && h.canMoveDown);
		
		Hobbit left = new Hobbit(leftLocation, 6, 8, 4);
		world.add(left);
		assertFalse(h.canMoveUp && h.canMoveDown && h.canMoveLeft);
		
		Hobbit right = new Hobbit(rightLocation, 6, 8, 4);
		world.add(right);
		assertFalse(h.canMoveUp && h.canMoveDown && h.canMoveLeft && h.canMoveRight);
		
		
	}
	
	// test the behaviors of compareTo
	@Test
	void testCompareTo() {
		
		int[] hereLocation = { 10, 10 };
		int[] lessLocation = { 5, 5 };
		int[] moreLocation = { 15, 15 };
		
		Hobbit here = new Hobbit(hereLocation, 6, 3, 3);
		Hobbit less = new Hobbit(lessLocation, 6, 3, 3);
		Hobbit more = new Hobbit(moreLocation, 6, 3, 3);
		Hobbit copy = here;
		
		assertEquals(1, here.compareTo(less));
		assertEquals(-1, here.compareTo(more));
		assertEquals(0, here.compareTo(copy));
		
		int[] left = { 0, 49 };
		int[] right = { 0, 50 };
		
		// extra case test added: points right next to each other
		Hobbit hobbitOnLeft = new Hobbit(left, 10, 4, 5);
		Nazgul nazgulOnRight = new Nazgul(right, 20, 5, 4);
		
		assertEquals(1, nazgulOnRight.compareTo(hobbitOnLeft));
		
	}

}
