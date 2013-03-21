package modell;

import java.util.ArrayList;

import skeleton.Logger;

public class Glade {

	/**
	 * A mezőket tároló lista.
	 */
	private ArrayList<Field> fields;
	
	/**
	 * Az élelmeket tároló lista.
	 */
	private ArrayList<Food> foods;
	
	/**
	 * A spray-ket tároló lista.
	 */
	private ArrayList<Spray> sprays;
	
	/**
	 * Az aktiv elemek listája.
	 */
	private ArrayList<Active> activeObjects;
	
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
		Logger.exit(this);
	}
	
	/**
	 * Egy aktív elem eltávolítása.
	 * @param active Az eltávolítandó elem.
	 */
	public void removeActiveObject(Active active) {
		Logger.enter(this, "removeActiveObject", Logger.getObjectName(active));
		Logger.exit(this);
	}
}
