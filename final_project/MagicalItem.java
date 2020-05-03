// Patrick Temple
// Prof. Whitener
// CSCI165
// 4 May 2020

// Final Project: MagicalItem
// Purpose: to extend Item and provide
// additional functionality to items


public class MagicalItem extends Item {
	
	// class features
	private int effectMultiplier;
	
	public MagicalItem() { super(); } // blank constructor
	
	public MagicalItem(int[] location, int modifyingRate, itemType it, int effectMultiplier) {
		super(location, modifyingRate * effectMultiplier, it);
		this.effectMultiplier = effectMultiplier;
	}
	
	// multiply the effect of the item by
	// the effect multiplier to make the item more powerful
	// method below this comment called in constructor
	private int multiplyRatio(int ratio) {
		return effectMultiplier * ratio;
	}
	
}
