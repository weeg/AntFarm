package modell;

import skeleton.Logger;

/**
 * Az élelemszag objektum.
 */
public class FoodOdor extends Odor {
	
	public FoodOdor() {
		Logger.attach("FoodOdor", this);
	}
	/**
	 * Törli magát a mezõrõl.
	 */
	public void evaporate() {
		Logger.enter(this, "evaporate");
		this.getPosition().removeOdor(this);
		Logger.exit(this);
	}
	
	public boolean neutralize() {	
		Logger.enter(this, "neutralize");
		Logger.exit(this);
		return false;
	}
}
