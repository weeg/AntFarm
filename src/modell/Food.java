package modell;

import java.util.ArrayList;

public class Food extends Entity {

	// A meg meglevo elelem mennyisege
	private int quantity;
	
	// Az elelemhez tartozo szagok
	private ArrayList<FoodOdor> foodOdors;
	
	// Visszaadja a meg meglevo elelem mennyiseget
	public int getQuantity() {
		return 0;
	}
	
	// Hozzaad a listahoz egy uj elelemszagot
	public void addFoodOdor(FoodOdor foodOdor) {
		
	}
	
	// Utkozes egy hangyaval
	public void collide(Ant ant) {
		
	}
}
