package modell;

import java.util.ArrayList;

import skeleton.Logger;
/**
 * Az elelem objektum.
 */
public class Food extends Entity {

	/**
	 *  A meglevo elelem mennyisege.
	 */
	private int quantity;
	
	/**
	 * Az elelemhez tartozo szagok.
	 */
	private ArrayList<FoodOdor> foodOdors = new ArrayList<FoodOdor>();
	
	public Food() {
		Logger.attach("Food", this);
	}
	
	/**
	 * Visszaadja a meg meglevo elelem mennyiseget.
	 * @return A meglévõ mennyiség.
	 */
	public int getQuantity() {
		Logger.enter(this, "getQuantity");
		int r = Logger.choose("Elfogyott mar az elelem?", "Igen", "Nem");
		if (r == 1) {
			for (FoodOdor fo : foodOdors) {
				fo.evaporate();
			}
		}		
		Logger.exit(this);
		return quantity;
	}
	
	/**
	 * Hozzaad a listahoz egy uj elelemszagot.
	 * @param foodOdor Az elelemszag.
	 */
	public void addFoodOdor(FoodOdor foodOdor) {
		Logger.enter(this, "addFoodOdor", Logger.getObjectName(foodOdor)+":FoodOdor");
		foodOdors.add(foodOdor);
		Logger.exit(this);
	}
	
	/**
	 * Utkozes egy hangyaval. Csokkenti az elelem mennyiseget.
	 * @param ant hangya. 
	 */
	public void collide(Ant ant) {
		Logger.enter(this, "collide", Logger.getObjectName(ant) + ":Ant");
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		ant.eat();
		Logger.exit(this);
	}
}
