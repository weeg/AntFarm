package modell;

import java.awt.Point;
import java.util.ArrayList;

import view.*;

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
		int height = 30;
		int width  = 30;

		Field[][] fieldArray = new Field[width][height];
		
		// Mezok letrehozasa
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				// Uj mezo letrehozasa
				Field field = new Field(this);

				// Uj nezet letrehozasa
				FieldView fieldView = new FieldView();
				fieldView.setModell(field);
				fieldView.setCoord(new Point(i - 1, j - 1));

				// Nezet hozzarendelese a mezohoz
				field.setView(fieldView);

				fieldArray[i][j] = field;
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				Field field = fieldArray[i][j];
				
				// Szomszedok megadasa
				// North
				try {
					field.setNeighbour(Direction.N, fieldArray[i][j - 1]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// Northeast
				try {
					field.setNeighbour(Direction.NE, fieldArray[i + 1][j - i % 2]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// Souteast
				try {
					field.setNeighbour(Direction.SE, fieldArray[i + 1][j + (i + 1) % 2]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// South
				try {
					field.setNeighbour(Direction.S, fieldArray[i][j + 1]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// Southwest
				try {
					field.setNeighbour(Direction.SW, fieldArray[i - 1][j + (i + 1) % 2]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				// Northwest
				try {
					field.setNeighbour(Direction.NW, fieldArray[i - 1][j - i % 2]);
				} catch (ArrayIndexOutOfBoundsException e) { }
				
				// Palyaszelek hozzaadasa
				if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
					DeadEnd deadEnd = new DeadEnd();
					field.addEntity(deadEnd);
				}
				
				// Hozzaadas a listahoz
				fields.add(field);
			}
		}		
		
		// Hangyaboly letrehozasa
		AntHill antHill = new AntHill();
		antHill.setView(new AntHillView());
		fieldArray[5][10].addEntity(antHill);
		addActiveObject(antHill);
		
		// Viz hozzaadasa
		for (int i = 0; i < 2; i++){
			Water water  = new Water();
			water.setView(new WaterView());
			fieldArray[5 + i * 3][8 + i * 5].addEntity(water);
		}
		
		// Kavicsok hozzaadasa
		for (int i = 0; i < 6; i++){
			Stone stone  = new Stone();
			stone.setView(new StoneView());
			fieldArray[2 + (i % 5) * 2 + i][1 + (i % 3) + i * 2].addEntity(stone);
		}
		
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
