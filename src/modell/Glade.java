package modell;

import java.util.ArrayList;

import skeleton.Logger;

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
	
	public Glade() {
		Logger.attach("Glade", this);
	}
	
	/**
	 * A jatek inditasa.
	 */
	public void start() {
		Logger.enter(this, "start");
		for (int i = 0; i < 7; i++) {
			fields.add(new Field(this));
		}
		for (int i = 1; i < 7; i++) {
			fields.get(0).setNeighbour(Direction.N.turn(i), fields.get(i));
		}
		Food f = new Food();
		fields.get(0).addEntity(f);
		for (int i = 0; i < 3; i++) {
			FoodOdor fo = new FoodOdor();
			f.addFoodOdor(fo);
			fields.get(i).addOdor(fo);
		}
		AntHill ah = new AntHill();
		fields.get(1).addEntity(ah);
		this.addActiveObject(ah);
		AntLion al = new AntLion();
		fields.get(2).addEntity(al);
		Anteater ae = new Anteater();
		fields.get(1).addEntity(ae);
		this.addActiveObject(ae);
		for (int i = 0; i < 3; i++) {
			Barricade b = new Stone();
			fields.get(3+i).addEntity(b);
		}
		Logger.exit(this);
	}
	
	/**
	 * Az ido leptetese
	 */
	public void tick() {
		Logger.enter(this, "tick");
		
		Logger.off();
		Field center = new Field(this);
		Field[] neighbours = new Field[3];
		for (int i = 0; i < 3; i++) {
			neighbours[i] = new Field(this);
			neighbours[i].addOdor(new FoodOdor());
		}
		center.setNeighbour(Direction.N, neighbours[0]);
		center.setNeighbour(Direction.NE, neighbours[1]);
		center.setNeighbour(Direction.SE, neighbours[2]);
		AntHill ah = new AntHill();
		ah.setPosition(center);
		center.addEntity(ah);
		Food f = new Food();
		FoodOdor fo = new FoodOdor();
		AntOdor ao = new AntOdor(neighbours[0]);
		Poison p = new Poison(this, neighbours[0]);
		f.addFoodOdor(fo);
		foods.add(f);
		neighbours[2].addEntity(f);
		neighbours[1].addOdor(fo);
		fo.setPosition(neighbours[1]);
		neighbours[0].addOdor(ao);
		neighbours[0].addEntity(p);
		activeObjects.add(ao);
		activeObjects.add(ah);
		activeObjects.add(p);
		Logger.on();		
		int foodQuantity = foods.get(0).getQuantity();		
		if (foodQuantity == 0) {
			gameOver();
			return;
		}
		for (Object a : activeObjects.toArray()) {
			((Active) a).animate();
		}
		Logger.exit(this);
	}
	
	/**
	 * Egy aktiv elem hozzaadasa.
	 * @param active Az aktiv elem.
	 */
	public void addActiveObject(Active active) {
		Logger.enter(this, "addActiveObject", Logger.getObjectName(active));
		activeObjects.add(active);
		Logger.exit(this);
	}
	
	/**
	 * Egy aktiv elem eltavolitasa.
	 * @param active Az eltavolitando elem.
	 */
	public void removeActiveObject(Active active) {
		Logger.enter(this, "removeActiveObject", Logger.getObjectName(active));
		activeObjects.remove(active);
		Logger.exit(this);
	}
	
	/**
	 * A jatek vege metodus.
	 */
	public void gameOver() {
		Logger.enter(this, "gameOver");
		Logger.exit(this);
	}
	
	/**
	 * Aktiv elemek visszaadasa.
	 * @return
	 */
	public ArrayList<Active> getActiveObjects() {
		return activeObjects;
	}
}
