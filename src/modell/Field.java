package modell;

import java.util.ArrayList;
import java.util.HashMap;

import skeleton.Logger;

public class Field {
	
	/** A tisztas, amin van a mezo */
	private Glade glade;

	/** A szomszedokat tarolo objektum */
	private HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>();
	
	/** A szagokat tarolo lista */
	private ArrayList<Odor> odors = new ArrayList<Odor>();
	
	/** Az entitasokat tarolo lista */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	/**
	 * A mezo konstruktora
	 * @param glade A tisztas, amin van a mezo
	 */
	public Field(Glade glade) {
		Logger.attach("field", this);
		this.glade = glade;
	}
	
	/**
	 * Az adott iranyban levo szomszed megadasa.
	 * @param dir Az irany.
	 * @param neighbour A szomszed mezo.
	 */
	public void setNeighbour(Direction dir, Field neighbour) {
		Logger.enter(this, "setNeighbour", "dir:" + dir.toString(), Logger.getObjectName(neighbour));		
		Logger.exit(this);
	}
	
	/**
	 * Visszaadja az adott iranyban levo szomszedot.
	 * @param dir Az irany.
	 * @return A szomszed mezo.
	 */
	public Field getNeighbour(Direction dir) {
		Logger.enter(this, "getNeighbour", "dir: " + dir.toString());
		Logger.exit(this);
		Logger.off();
		Field n = new Field(glade);
		Logger.on();
		return n;
	}
	
	/**
	 * Egy listat ad vissza azokkal a mezokkel, amik a mezotol radius tavolsagon belul vannak.
	 * @param radius A tavolsag.
	 * @return Lista a mezokrol.
	 */
	public ArrayList<Field> getNeighbours(int radius) {
		Logger.enter(this, "getNeighbours", "radius:int");
		Logger.exit(this);
		Logger.off();
		ArrayList<Field> list = new ArrayList<Field>();
		for (int i = 0; i < 3; i++) {			
			list.add(new Field(glade));
		}
		list.add(this);
		Logger.on();
		return list;
	}
	
	/**
	 * A mezoben levo szagok osszintenzitasa.
	 * @return Az osszintenzitas.
	 */
	public int getOdorIntensity() {
		Logger.enter(this, "getOdorIntensity");
		int intens = 0;
		for (Odor o : odors) {
			intens += o.getIntensity();
		}
		Logger.exit(this);
		return intens;
	}
	
	/**
	 * Egy szag hozzaadása a mezohoz.
	 * @param odor A szagobjektum.
	 */
	public void addOdor(Odor odor) {
		Logger.enter(this, "addOdor", Logger.getObjectName(odor));
		odors.add(odor);
		Logger.exit(this);
	}
	
	/**
	 * Visszaadja a mezoben talalhato szagok listajat.
	 * @return A szaglista.
	 */
	public ArrayList<Odor> getOdors() {
		Logger.enter(this, "getOdors");
		Logger.exit(this);				
		return odors;
	}

	/**
	 * Szag torlese a mezorol.
	 * @param odor A torlendo szagobjektum.
	 */
	public void removeOdor(Odor odor) {
		Logger.enter(this, "removeOdors", Logger.getObjectName(odor)+":Odor");
		odors.remove(odor);
		Logger.exit(this);
	}

	/**
	 * Entitas hozzaadasa a mezohoz.
	 * @param e Egy entitas.
	 */
	public void addEntity(Entity e) {
		Logger.enter(this, "addEntity", Logger.getObjectName(e));
		entities.add(e);
		Logger.exit(this);
	}

	/**
	 * Entitas eltavolitasa.
	 * @param e Torlendo entitas.
	 */
	public void removeEntity(Entity e) {
		Logger.enter(this, "removeEntity", Logger.getObjectName(e));
		entities.remove(e);
		Logger.exit(this);
	}

	/**
	 * Az osszes entitas lekerese.
	 * @return Az entitasok listaja.
	 */
	public ArrayList<Entity> getEntities() {
		Logger.enter(this, "getEntities");
		Logger.exit(this);
		return entities;
	}

	/**
	 * Visszaadja a tisztast, amin van a mezo
	 * @return A tisztas objektum
	 */
	public Glade getGlade() {
		return glade;
	}
}
