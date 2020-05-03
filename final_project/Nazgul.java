import java.awt.Color;
import java.util.ArrayList;

public class Nazgul extends Creature {
	
	
	// class features
	
	// a Nazgul can attack a Hobbit from
	// anywhere inside of its radius, and then
	// move towards the Hobbit it is attacking.
	int[] victimLocation;
	
	
	
	// constructors
	public Nazgul() { super(); } // blank constructor
	
	public Nazgul(int[] location, int health, int offense, int defense) {
		super(location, health, offense, defense);
		this.sustenance = 8;
		this.radiusSize = 2;
	}
	
	private void setVictimLocation(int[] vLocation) {
		this.victimLocation = vLocation;
	}
	
	private void resetSustenance() {
		this.sustenance = 8;
	}
	
	@Override
	public void chooseAction(ArrayList<Creature> neighborData) {
		// TODO Auto-generated method stub
		System.out.println("A Nazgul is plotting its next action");
	}
	
	@Override
	public void move() {
		System.out.println("A Nazgul is attempting to move");
	}

	@Override
	public void attack(Creature victim) {
		System.out.println("A Nazgul is attempting to attack a Hobbit");
	}

	@Override
	public Creature replicate() {
		System.out.println("A Nazgul is attempting to replicate");
		return null;
	}

	@Override
	public void stay() {
		// TODO Auto-generated method stub
		System.out.println("A Nazgul is staying");
	}

	@Override
	public Color color() {
		System.out.println("A Nazgul is changing colors");
		if(this.sustenance >= 4) {
			return Color.BLACK;
		}
		else if(this.sustenance >= 2) {
			return Color.GRAY;
		}
		else {
			return Color.LIGHT_GRAY;
		}
	}

	@Override
	public void getItem(Item collectedItem) {
		System.out.println("A Nazgul is getting an item");
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
		/* Nazgul cannot eat a mortal's food, therefore
		 * it cannot use NOURISHMENT type items, nor can
		 * it use a REACH item as it can attack a Hobbit from
		 * anywhere inside of its radius.                     */
	}

	@Override
	public boolean canReplicate() {
		// TODO Auto-generated method stub
		return false;
	}

}
