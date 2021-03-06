package modell;

import java.util.ArrayList;

/**
 * Az elelem objektum.
 */
public class Food extends Entity {

	/**
	 *  A meglevo elelem mennyisege.
	 */
	private int quantity;
	
	private int foodOdorRadius = 0;
	
	/**
	 * Az elelemhez tartozo szagok.
	 */
	private ArrayList<FoodOdor> foodOdors = new ArrayList<FoodOdor>();
	
	public Food() {}
	
	/**
	 * Beallitja az entitas uj poziciojat �s letrehozza a kajaszagokat is.
	 * @param position Az �j pozici�.
	 */
	public void setPosition(Field position) {
		this.position = position;
		createFoodOdors();
	}
	
	public void createFoodOdors() {
		// Kajaszagok szetszorasa
		if (foodOdorRadius != quantity / 5) {
			
			// Regi kajaszaok torlese
			for (FoodOdor fo: foodOdors) {
				Field f = fo.getPosition();
				f.removeOdor(fo);
			}
			foodOdors.clear();
			
			// Uj hatosugar
			foodOdorRadius = quantity / 5;
			
			// Uj kajaszagok lerakasa
			for (int radius = 0; radius <= foodOdorRadius; radius++){
				ArrayList<Field> neighbours = position.getNeighbours(radius);
				int intensity = (quantity - radius * 5) * 3;
				
				// Szomszedok vegigiteralasa
				for (Field neighbour: neighbours) {
					boolean hasFoodOdor = false;
					// Egy szagot egy mezohoz csak 1x adjon hozza
					for (FoodOdor fo: foodOdors) {
						if (fo.getPosition() == neighbour) {
							hasFoodOdor = true;
						}
					}
					
					// Uj kajaszag letrehozasa
					if (!hasFoodOdor) {
						FoodOdor foodOdor = new FoodOdor();
						foodOdor.setPosition(neighbour);
						foodOdor.setIntensity(intensity);
						
						neighbour.addOdor(foodOdor);
						foodOdors.add(foodOdor);
					}
				}
			}
		}
	}
	
	/**
	 * Visszaadja a meg meglevo elelem mennyiseget. Ha nulla akkor t�rli a szagokat.
	 * @return A megl�v� mennyis�g.
	 */
	public int getQuantity() {
		if (quantity == 0 && !foodOdors.isEmpty()) {
			for (FoodOdor fo : foodOdors) {
				fo.evaporate();
			}
			foodOdors.clear();
			position.removeEntity(this);
		} 
		return quantity;
	}
	
	/**
	 * Beallitja az elelem mennyiseget
	 * @param quantity Az elelem mennyisege
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Hozzaad a listahoz egy uj elelemszagot.
	 * @param foodOdor Az elelemszag.
	 */
	/*
	public void addFoodOdor(FoodOdor foodOdor) {
		foodOdors.add(foodOdor);
	}
	*/
	/**
	 * Utkozes egy hangyaval. Csokkenti az elelem mennyiseget.
	 * @param ant hangya. 
	 */
	public void collide(Ant ant) {
		ant.eat();
		quantity--;
		getView().change();
		createFoodOdors();
	}
	/**
	 * Utkozes egy kavicsot. Blokkolja a kavicsot.
	 * @param s a ko, amivel utkozott.
	 */
	public void collide(Stone s) {
		s.block();
	}
}
