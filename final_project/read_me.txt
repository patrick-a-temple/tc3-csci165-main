Patrick Temple
Prof. Whitener
CSCI165
11 May 2020 - VERSION 2

This read me file will briefly explain the basic rules and use of my program. The requirement for two additional classes that implement Creature is described at the bottom.

Change Log:
2 May 2020: Basic class structure, UML design
10 or 11 May 2020: Final working code, with UML and readme file

This folder contains my final project for Computer Science II. It is a version of a cellular automaton that has two different types of characters: Hobbits and Nazgul.

How to run:

Open Driver.java, compile, and run. Enter the number of Hobbits and Nazgul you want to load onto the map into the terminal. The game will begin running, no further interaction is required, just sit back and watch.

Structure choices:

Both Hobbits and Nazgul implement Creature, as both share derived functions that are overridden dependant on what the specific Creature type can do. For example, there are certain ways that a different Creature could do an action, such as deciding to move, or attacking another Creature. Hobbits can use more item types than a Nazgul can, to add,

The Item class is different, as it is not abstract and can have objects made of it. It is a very basic class, having only a location and an enumeration for the different types of items there are (health, offense, defense, sight, nourishment, and reach).

Map holds the Creatures and Items on the map. 

Items are different: I chose to make MagicalItem inherit properties from Item, and make Item a working class. Right now, the only thing special about a MagicalItem is that it multiplies the modifier the item would have on a status. It does this in the constructor: original value * multiplier = new value. This is something I would have considered improving, but I did not due to time restrictions.

Some general rules (most are below in the basic structure):
Reach in Hobbits: how far a Hobbit can reach to attack another Nazgul
If a Hobbit has a higher reach than a radius of sight value, then it cannot attack outside its radius of sight
Hobbits cannot move after attacking
If a Hobbit can move, they will follow a fellow Hobbit
Hobbits and Nazgul can only move one tile at a time in any non-diagonal direction (with the exception of a Nazgul after attacking a Hobbit, if this is the case, it will attempt to go to the side of that Hobbit, unless that Hobbit was killed or completely blocked).
Nazgul can move closer to a Hobbit they have just attacked. If clear to do so, the Nazgul will move to the direct side of a Hobbit.
If a Nazgul does not see a Hobbit, they will approach a fellow Nazgul one step at a time.
Nazgul can only obtain sustenance from attacking Hobbits, and cannot "eat"/use nourishment items. Nazgul cannot use reach items, in addition. They can still collect them, however. Let's just say this makes it harder for Hobbits to eat as a side effect.
Hobbits are the exact reverse: they must eat (read: collect nourishment items) to regain sustenance, and they can obtain and reap the benefits of reach items.
If any creature has no other surrounding creatures, it will draw a random number to decide what direction it will move.
If a Hobbit has a sustenance equal or less than zero, but no less than -2 sustenance, remove 3 health from this Hobbit each turn it has sustenance this low. If lower than -2, remove 5 health instead.



Structure of the game

(If I mention direction priority: I mean up first, right second, down third, left last.)

From the Driver:
Ask user for number of Hobbits and Nazgul they want on the map
Arrange items on map, store them inside an ArrayList
According to count, make the appropriate number of Nazgul and Hobbits and store them in an ArrayList
Put the ArrayLists together

Open window and begin session

Loop:
Reorder all Creatures in the occupants ArrayList based on position on map
Give neighborhood data to Creature, which will respond in the following ways:
	Hobbit:
		If dead (> 0 HP) do not allow to move
		Look for a Nazgul
		If Nazgul is reachable in a radius of reach, attack it:
                Draw random number from 1 to 6, if it's
				Calculate attack's effect on health: this Hobbit's attack minus the Nazgul's defense = the attack
                If attack effect is 0, draw random number (1-6), and if it's six, it's a critical hit which bypasses the Nazgul's defense and times the attack by two
                Do not move or check ground
		See if they can move at least one pixel up, right, down, left
		Cannot move: stay, do not pick up item on ground
		Can move:
            Go the opposite direction of a Nazgul if there is one present
            If surrounded in all directions, move in a random direction
			Are there Hobbits present:
				Move towards one according to direction priority
            At least one clear way within radius (even without another Hobbit: move according to direction priority
			No Hobbit or Nazgul within radius of sight: pick a random direction
        NOTE: OK for a hobbit to cross the wall but loop around to the other side
    Nazgul:
        If dead (> 0 HP) do not allow to move
		Check area in this order: up, right, down, left.
		If Hobbit directly within their radius of sight, attack:
            Calculate attack's effect on health: this Nazgul's attack minus the Hobbit's defense = the attack
            If attack effect is 0, draw random number (1-6), and if it's six, it's a critical hit which bypasses the Hobbit's defense and does double damage
            If possible, move to a space close to the Hobbit, unless that Hobbit died in that attack or if the Hobbit is already surrounded.
            Do not check ground.
            Reset the Nazgul's sustenance back to eight.
        If no attack occured this turn: Move like Hobbit, except move towards fellow Nazgul if they are nearby
            
Decrement each Creature's sustenance
        
Call each Creature's getCheckGroundStatus, if they are OK to check the ground for an item:
    Have the map see if there is an item at that location, if so collect it and give it to the Creature (getItem) - also, store the item into the creature's inventory
    Apply the effect of the item to the Creature
    Delete the item from the ArrayList
    
See if there are any occupants are dead, if so, delete the dead creatures

Redraw the map

Wait one second

Loop through until the user closes the program


Opportunities for Growth:

I could add more Creatures. I was thinking of a Creature that could heal instead any other Creature, maybe the name could be Filler. By default, I would want the radius to be 5, so it could jump to locations near any other Creature. If the Healer calls attack, it will heal the other player regardless of their defense. 

Another Creature I was thinking of was the Killer. If it is attacked, it will deal the same amount of damage as the attacker gave it. I am thinking it would be stationary, had a very weak attack and defense and range of 1.
