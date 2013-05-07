package modell;

import java.awt.Point;
import java.util.ArrayList;

import view.AntHillView;
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

		Field[][] fieldArray = new Field[width][height];
		
		// Mezok letrehozasa
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				// Uj mezo letrehozasa
				Field field = new Field(this);

				// Uj nezet letrehozasa
				FieldView fieldView = new FieldView();
				fieldView.setModell(field);
				fieldView.setCoord(new Point(i, j));

				// Nezet hozzarendelese a mezohoz
				field.setView(fieldView);

				fieldArray[i][j] = field;
			}
		}

		// Szomszedok megadasa
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// North
				try {
					fieldArray[i][j].setNeighbour(Direction.N, fieldArray[i][j - 1]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// Northeast
				try {
					fieldArray[i][j].setNeighbour(Direction.NE, fieldArray[i + 1][j - (i + 1) % 2]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// Souteast
				try {
					fieldArray[i][j].setNeighbour(Direction.SE, fieldArray[i + 1][j + i % 2]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// South
				try {
					fieldArray[i][j].setNeighbour(Direction.S, fieldArray[i][j + 1]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// Southwest
				try {
					fieldArray[i][j].setNeighbour(Direction.SW, fieldArray[i - 1][j + i % 2]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// Northwest
				try {
					fieldArray[i][j].setNeighbour(Direction.NW, fieldArray[i - 1][j - (i + 1) % 2]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				
				// Hozzaadas a listahoz
				fields.add(fieldArray[i][j]);
			}
		}		
		
		// Hangyaboly letrehozasa
		AntHill antHill = new AntHill();
		antHill.setView(new AntHillView());
		fieldArray[15][10].addEntity(antHill);
		addActiveObject(antHill);
	}
	
	/**
	 * Az ido leptetese
	 */
	public void tick() {
		if (getFoodQuantity() == 0) {
			gameOver();
			// return;
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
		view.setModell(this);
		this.view = view;
	}
	
	public View getView() {
		return view;
	}
}
