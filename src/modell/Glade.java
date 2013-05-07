package modell;

import java.awt.Point;
import java.util.ArrayList;

import view.FieldView;
import view.View;

public class Glade implements Drawable {

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
	
	protected View view;
	
	public Glade() {}
	
	/**
	 * A jatek inditasa.
	 */
	public void start() {
		// Palya magassaga es szelessege
		int height = 20;
		int width  = 20;

		// Mezok letrehozasa
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				
				// Uj mezo letrehozasa
				Field field = new Field(this);

				// Uj nezet letrehozasa
				FieldView fieldView = new FieldView();
				fieldView.setModell(field);
				fieldView.setPosition(new Point(row, column));

				// Nezet hozzarendelese a mezohoz
				field.setView(fieldView);

				// Tarolasa gladeben				
				fields.add(field);
			}
		}

		// Szomszedok megadasa		
		for (Field field : fields) {

			// Aktualis mezo indexe
			int index = fields.indexOf(field);

			// Northwest
			try {
				Field nw = fields.get(index - width - 1);
				field.setNeighbour(Direction.NW, nw);
		    // Nem letezo index
			} catch (IndexOutOfBoundsException e) {}

			// North
			try {
				Field n = fields.get(index - width);
				field.setNeighbour(Direction.N, n);
			} catch (IndexOutOfBoundsException e) {}

			// Northeast
			try {
				Field ne = fields.get(index - width + 1);
				field.setNeighbour(Direction.NE, ne);
			} catch (IndexOutOfBoundsException e) {}

			// Southwest
			try {
				Field sw = fields.get(index - 1);
				field.setNeighbour(Direction.SW, sw);
			} catch (IndexOutOfBoundsException e) {}

			// South
			try {
				Field s = fields.get(index + width);
				field.setNeighbour(Direction.S, s);
			} catch (IndexOutOfBoundsException e) {}

			// Southeast
			try {
				Field se = fields.get(index + 1);
				field.setNeighbour(Direction.SE, se);
			} catch (IndexOutOfBoundsException e) {}
		}
	}
	
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
		
		view.change();
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
	
	public void setView(View view) {
		this.view = view;
	}
	
	public View getView() {
		return view;
	}
}
