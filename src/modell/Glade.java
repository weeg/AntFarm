package modell;

import java.util.ArrayList;

import skeleton.Logger;

public class Glade {

	/**
	 * A mezõket tároló lista.
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
	 * Az eltelt idõ.
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
		for (int i = 1; i < 7; i++) {
			fields.get(0).setNeighbour(Direction.parseInt(i), fields.get(i));
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
	 * Az idõ léptetése.
	 */
	public void tick() {
		Logger.enter(this, "tick");
		
		Logger.off();
		Field center = new Field();
		Field[] neighbours = new Field[3];
		for (int i = 0; i < 3; i++) {
			neighbours[i] = new Field();
			neighbours[i].addOdor(new FoodOdor());
		}
		center.setNeighbour(Direction.N, neighbours[0]);
		center.setNeighbour(Direction.NE, neighbours[1]);
		center.setNeighbour(Direction.SE, neighbours[2]);
		AntHill ah = new AntHill(this);
		ah.setPosition(center);
		center.addEntity(ah);
		Food f = new Food();
		FoodOdor fo = new FoodOdor();
		AntOdor ao = new AntOdor();
		ao.setGlade(this);
		Poison p = new Poison(this, neighbours[0]);
		f.addFoodOdor(fo);
		this.foods.add(f);
		neighbours[2].addEntity(f);
		neighbours[1].addOdor(fo);
		fo.setPosition(neighbours[1]);
		neighbours[0].addOdor(ao);
		neighbours[0].addEntity(p);
		this.activeObjects.add(ao);
		this.activeObjects.add(ah);
		this.activeObjects.add(p);
		Logger.on();
		foods.get(0).getQuantity();
		for (Object a : activeObjects.toArray()) {
			((Active) a).animate();
		}
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
