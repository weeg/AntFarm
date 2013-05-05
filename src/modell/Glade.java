package modell;

import java.util.ArrayList;

public class Glade {

	/**
	 * A mezoket tarolo lista.
	 */
	private ArrayList<Field> fields = new ArrayList<Field>();
	
	/**
	 * Az elelmeket tarolo lista.
	 */
	private ArrayList<Food> foods = new ArrayList<Food>();
	
	/**
	 * A spray-ket tarolo lista.
	 */
	private ArrayList<Spray> sprays = new ArrayList<Spray>();
	
	/**
	 * Az aktiv elemek listaja.
	 */
	private ArrayList<Active> activeObjects = new ArrayList<Active>();
	
	/**
	 * Az eltelt ido.
	 */
	private int time;
	
	public Glade() {}
	
	/**
	 * A jatek inditasa.
	 */
	public void start() {	}
	
	/**
	 * Az ido leptetese
	 */
	public void tick() {

		if (getFoodQuantity() == 0) {
			gameOver();
			return;
		}
		ArrayList<Active> copy = new ArrayList<Active>(activeObjects);
		for (Active a : copy) {
			try {
				a.animate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Visszaadja a palyan levo elelem mennyiseget.
	 * @return Elelem mennyisege.
	 */
	public int getFoodQuantity() {
		int foodQuantity = 0;
		for(Food f : foods) {
			foodQuantity += f.getQuantity();
		}	

		return foodQuantity;
	}
	
	/**
	 * Mezo hozzaadsa a palyahoz
	 * @param field
	 */
	public void addField(Field field) {
		fields.add(field);
	}
	
	/**
	 * Mezok visszaadasa.
	 * @return Mezok
	 */
	public ArrayList<Field> getFields() {
		return fields;
	}
	
	/**
	 * Egy aktiv elem hozzaadasa.
	 * @param active Az aktiv elem.
	 */
	public void addActiveObject(Active active) {
		activeObjects.add(active);
	}
	
	/**
	 * Egy aktiv elem eltavolitasa.
	 * @param active Az eltavolitando elem.
	 */
	public void removeActiveObject(Active active) {
		activeObjects.remove(active);
	}
	
	/**
	 * Egy elelem hozzaadasa.
	 * @param food Az elelem.
	 */
	public void addFood(Food food) {
		foods.add(food);
	}
	
	/**
	 * Elelemek visszaadasa
	 */
	public ArrayList<Food> getFoods() {
		return foods;
	}
	
	/**
	 * Egy spray hozzaadasa.
	 * @param spray A spray.
	 */
	public void addSpray(Spray spray) {
		sprays.add(spray);
	}
	
	/**
	 * Spray-k visszaadasa
	 */
	public ArrayList<Spray> getSprays() {
		return sprays;
	}
	
	/**
	 * A jatek vege metodus.
	 */
	public void gameOver() {}
	
	/**
	 * Aktiv elemek visszaadasa.
	 * @return
	 */
	public ArrayList<Active> getActiveObjects() {
		return activeObjects;
	}
}
