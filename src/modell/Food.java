package modell;

import java.util.ArrayList;

import skeleton.Logger;
/**
 * Az élelem objektum.
 */
public class Food extends Entity {

	/**
	 *  A meglévõ élelem mennyisége.
	 */
	private int quantity;
	
	/**
	 * Az élelemhez tartozó szagok.
	 */
	private ArrayList<FoodOdor> foodOdors = new ArrayList<FoodOdor>();
	
	public Food() {
		Logger.attach("Food", this);
	}
	
	/**
	 * Visszaadja a még meglévõ élelem mennyiségét.
	 * @return A meglévõ mennyiség.
	 */
	public int getQuantity() {
		Logger.enter(this, "getQuantity");
		for (FoodOdor fo : foodOdors) {
			fo.evaporate();
		}
		Logger.exit(this);
		return 0;
	}
	
	/**
	 * Hozzáad a listához egy új élelemszagot.
	 * @param foodOdor Az élelemszag.
	 */
	public void addFoodOdor(FoodOdor foodOdor) {
		Logger.enter(this, "addFoodOdor", Logger.getObjectName(foodOdor));
		foodOdors.add(foodOdor);
		Logger.exit(this);
	}
	
	/**
	 * Ütközés egy hangyával. Csökkenti az élelem mennyiségét.
	 * @param A hangya. 
	 */
	public void collide(Ant ant) {
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		Logger.exit(this);
	}
}
