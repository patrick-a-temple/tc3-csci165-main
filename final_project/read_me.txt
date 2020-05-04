Patrick Temple
Prof. Whitener
CSCI165
4 May 2020

So far, this readme file is serving as a place where the author is keeping information about concepts to construct further in the game. The requirement for two additional classes that implement Creature is described at the bottom. If I have time, I may implement these.

Change Log:
2 May 2020: Basic class structure, UML design
Soon: fully functional game

This folder contains my final project for Computer Science II. It is a version of a cellular automaton that has two different types of characters: Hobbits and Nazgul.

Structure choices:

Both Hobbits and Nazgul implement Creature, as both share derived functions that are overrided dependant on their function. For example, I plan on having my Hobbits making different from Nazgul. Like a Hobbit's desire to move farther away from a Nazgul, a Nazgul desires to get closer to other Hobbits.

Items are different: I chose to make MagicalItem inherit properties from Item, and make Item a working class. Right now, the only thing special about a MagicalItem is that it multiplies the modifier the item would have on a status ailment. It does this in the constructor: original value * multiplier = new value. This is something I could improve on later.

How to run:

Open Driver.java, compile, and run. Enter the number of Hobbits and Nazgul you want to load onto the map, then the speed into the terminal. The game will begin running, no further interaction is required, just sit back and watch.

Some general rules (work in progress):
If a Hobbit has a higher reach than a radius value, then it cannot attack outside its radius
Hobbits cannot move after attacking
If a Hobbit can move, they will follow a fellow Hobbit
Hobbits can only move one tile at a time in any non-diagonal direction
Nazgul can move closer to a Hobbit they are attacking once they complete their attack. If clear to do so, the Nazgul will move to the direct side of a Hobbit. Also, Nazguls that have attack cannot pick up an item
If a Nazgul does not see a Hobbit, they will approach a fellow Nazgul one step at a time. If nothing else, it will draw a random number to decide what direction it will move.



Structure of the game after file compiled and executed (a rough idea that could change)

(If I mention direction priority: I mean up first, right second, down third, left last.)

From the Driver:
Ask user for number of Hobbits and Nazgul they want on the map (min one each, default of ten, max of 20 maybe?)
(maybe also a BPM setting)
Arrange items on map, store them inside an ArrayList
According to count, make the appropriate number of Nazgul and Hobbits and store them in an ArrayList

Open window and begin session

Loop until one species of Creature is dead, or AL is empty of all creatures (draw)
Reorder all Creatures in the AL based on Speed
Give neighborhood data to Creature, which will respond in the following ways:
	Hobbit:
		If dead (> 0 HP) do not allow to move
		Check area in this order: up, right, down, left.
		If Nazgul is reachable in a radius of reach, attack it (priority of direction above):
				Calculate attack's effect on health: this Hobbit's attack minus the Nazgul's defense = the attack
                If attack effect is 0, draw random number (1-6), and if it's six, it's a critical hit which bypasses the Nazgul's defense and times the attack by two
                Do not move or check ground
		See if they can move at least one pixel up, right, down, left
		Cannot move: stay, do not pick up item on ground
		Can move:
			Check area in this order: up, right, down, left.
			Look for monsters in vicinity, if they are present:
				Surrounded in all directions, draw random number (1-4), if 1 move up, 2 move right, 3 move down, 4 move left
				At least one clear way within radius: move according to direction priority
			Are there Hobbits present:
				Move towards one according to direction priority
			Clear within radius: draw random (1-4), if 1 move up, 2 move right, 3 move down, 4 move left
        NOTE: OK for a hobbit to cross the wall but loop around to the other side
    Nazgul:
        If dead (> 0 HP) do not allow to move
		Check area in this order: up, right, down, left.
		If Hobbit directly at their side, attack it (priority of direction above):
		Go to Hobbit's location
				Calculate attack's effect on health: this Nazgul's attack minus the Hobbit's defense = the attack
                If attack effect is 0, draw random number (1-6), and if it's six, it's a critical hit which bypasses the Nazgul's defense and times the attack by two
                If possible, move to a space close to the Hobbit. Do not check ground.
            Move like Hobbit, except move towards Hobbits if they are nearby
        
Call each Creature's getCheckGroundStatus, if they are OK to check:
    Have the map see if there is an item at that location, if so collect it and give it to the Creature (getItem) - also, store the item into the creature's inventory
    Delete the item from the ArrayList (are we allowed to do that?)
    
See if there are any occupants are dead, if so, delete the dead creatures (that may be a problem with null pointer exceptions)

Redraw the map

Wait (default every second, however many seconds according to BPM setting)

loop through hopefully all of the Creatures are gone (if possible)


Opportunities for Growth:

I could add more Creatures. I was thinking of a Creature that could heal instead any other Creature, maybe the name could be Filler. By default, I would want the radius to be 5, so it could jump to locations near any other Creature. If the Healer calls attack, it will heal the other player regardless of their defense. 

Another Creature I was thinking of was the Killer. If it is attacked, it will deal the same amount of . I am thinking it would be cool if it was stationary, had a very weak attack and defense and range of 1.
