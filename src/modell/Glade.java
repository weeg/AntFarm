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
	 * A hangyaszagsemelegesito spray.
	 */
	private AntOdorNeutralizerSpray antOdorNeutralizerSpray;
	
	/**
	 * A hangyairto spray.
	 */
	private AntKillerSpray antKillerSpray;
	
	/**
	 * Az aktiv elemek listaja.
	 */
	private ArrayList<Active> activeObjects = new ArrayList<Active>();
	
	/**
	 * Hogy vege van-e a jateknak
	 */
	private boolean gameIsOver = false;
	
	/**
	 * A rendelelo nezet.
	 */
	protected View view;
	
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
				fieldView.setModel(field);
				fieldView.setCoord(new Point(i - 1, j - 1));

				// Nezet hozzarendelese a mezohoz
				field.setView(fieldView);
				fieldView.change();

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
			for (Field f : fieldArray[5 + (i * 2) * 7][5 + i * 5].getNeighbours(1)) {
				Water water  = new Water();
				water.setView(new WaterView());
				f.addEntity(water);
			}		
		}
		
		// Kavicsok hozzaadasa
		for (int i = 0; i < 6; i++){
			Stone stone  = new Stone();
			stone.setView(new StoneView());
			fieldArray[7 + (i % 5) * 3 + i][3 + (i % 3) + i * 4].addEntity(stone);
		}
		
		// Faronk hozzaadasa
		for (Field f : fieldArray[20][23].getNeighbours(2)) {
			Log log = new Log();
			log.setView(new LogView());
			f.addEntity(log);
		}			
		
		// Elelem hozzaadasa
		for (int i = 0; i < 2; i++) {
			Food food = new Food();
			food.setQuantity(25 - (i % 2) * 10);
			food.setView(new FoodView());
			fieldArray[25 - (i % 2) * 15][15 + (i % 2) * 8].addEntity(food);
			addFood(food);
			
		}

		// Hangyalesok hozzaadasa
		for (int i = 0; i < 6; i++){
			AntLion antlion  = new AntLion();
			antlion.setView(new AntLionView());
			fieldArray[5 + (i % 5) * 3 + 2 * i][2 - (i % 3) + i * 4].addEntity(antlion);
		}
		
		// Hangyaszsun hozzaadasa
		Anteater anteater = new Anteater();
		anteater.setView(new AnteaterView());
		anteater.setDirection(Direction.S);
		fieldArray[12][0].addEntity(anteater);
		addActiveObject(anteater);
		
		// Hangyaszagsemlegesito spray hozzaadasa
		AntOdorNeutralizerSpray antOdorNeutralizerSpray = new AntOdorNeutralizerSpray(this);
		antOdorNeutralizerSpray.setRadius(3);
		antOdorNeutralizerSpray.setQuantity(20);
		setAntOdorNeutralizerSpray(antOdorNeutralizerSpray);
		
		// Hangyairto spray hozzaadasa
		AntKillerSpray antKillerSpray = new AntKillerSpray(this);
		antKillerSpray.setRadius(3);
		antKillerSpray.setQuantity(20);
		setAntKillerSpray(antKillerSpray);
	}
	
	/**
	 * Az ido leptetese
	 */
	public void tick() {
		if (getFoodQuantity() == 0) {
			gameIsOver = true;
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
	 * Visszaadja a renderelo nezetet.
	 * @return a renderelo nezet. 
	 */
	public View getView() {
		return view;
	}
	
	/**
	 * Visszaadja a hangyaszagsemlegesito sprayt.
	 * @return a hangyaszagsemlegesito spray.
	 */
	public AntOdorNeutralizerSpray getAntOdorNeutralizerSpray() {
		return antOdorNeutralizerSpray;
	}
	
	/**
	 * Beallitja a hangyaszagsemlegesito sprayt.
	 * @param antOdorNeutralizerSpray a hangyaszagsemlegesito spray.
	 */
	public void setAntOdorNeutralizerSpray(AntOdorNeutralizerSpray antOdorNeutralizerSpray) {
		this.antOdorNeutralizerSpray = antOdorNeutralizerSpray;
	}
	
	/**
	 * Visszaadja a hangyairto sprayt.
	 * @return a hangyairto spray.
	 */
	public AntKillerSpray getAntKillerSpray() {
		return antKillerSpray;
	}
	
	/**
	 * Beallitja a hangyairto sprayt.
	 * @param antKillerSpray a hangyairto spray.
	 */
	public void setAntKillerSpray(AntKillerSpray antKillerSpray) {
		this.antKillerSpray = antKillerSpray;
	}
	
	/**
	 * Vege van-e a jateknak
	 */
	public boolean IsGameOver() {
		return gameIsOver;
	}
	
	/**
	 * Aktiv elemek visszaadasa.
	 * @return
	 */
	public ArrayList<Active> getActiveObjects() {
		return activeObjects;
	}
	
	/**
	 * Beallitja a renderelo nezetet.
	 * @param view A kirajzolashoz hasznalt renderelo nezet.
	 */
	public void setView(View view) {
		view.setModel(this);
		this.view = view;
	}

}
