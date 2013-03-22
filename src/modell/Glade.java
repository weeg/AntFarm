package modell;

import java.util.ArrayList;

import skeleton.Logger;

public class Glade {

	/**
	 * A mezőket tároló lista.
	 */
	private ArrayList<Field> fields = new ArrayList<Field>();
	
	/**
	 * Az élelmeket tároló lista.
	 */
	private ArrayList<Food> foods = new ArrayList<Food>();
	
	/**
	 * A spray-ket tároló lista.
	 */
	private ArrayList<Spray> sprays = new ArrayList<Spray>();
	
	/**
	 * Az aktiv elemek listája.
	 */
	private ArrayList<Active> activeObjects = new ArrayList<Active>();
	
	/**
	 * Az eltelt idő.
	 */
	private int time;
	
	public Glade() {
		Logger.attach("Glade", this);
	}
	
	/**
	 * A játék indítása.
	 */
	public void start() {
		Logger.enter(this, "start");
		for (int i = 0; i < 7; i++) {
			fields.add(new Field());
		}
		for (Field f : fields) {
			f.setNeighbour(Direction.N, new Field());
		}
		Food f = new Food();
		fields.get(0).addEntity(f);
		for (int i = 0; i < 3; i++) {
			FoodOdor fo = new FoodOdor();
			f.addFoodOdor(fo);
			fields.get(i).addOdor(fo);
		}
		AntHill ah = new AntHill(this);
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
	 * Az idő léptetése.
	 */
	public void tick() {
		Logger.enter(this, "tick");
		Logger.exit(this);
	}
	
	/**
	 * Egy aktív elem hozzáadása.
	 * @param active Az aktív elem.
	 */
	public void addActiveObject(Active active) {
		Logger.enter(this, "addActiveObject", Logger.getObjectName(active));
		activeObjects.add(active);
		Logger.exit(this);
	}
	
	/**
	 * Egy aktív elem eltávolítása.
	 * @param active Az eltávolítandó elem.
	 */
	public void removeActiveObject(Active active) {
		Logger.enter(this, "removeActiveObject", Logger.getObjectName(active));
		activeObjects.remove(active);
		Logger.exit(this);
	}
}
