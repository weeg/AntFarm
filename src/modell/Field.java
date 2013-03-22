package modell;

import java.util.ArrayList;
import java.util.HashMap;

import skeleton.Logger;

public class Field {

	/**
	 * A szomszédokat tároló objektum.
	 */
	private HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>();
	
	/**
	 * A szagokat tároló lista.
	 */
	private ArrayList<Odor> odors = new ArrayList<Odor>();
	
	/**
	 * Az entitásokat tároló lista.
	 */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Field() {
		Logger.attach("Field", this);
	}
	
	/**
	 * Az adott irányban lévõ szomszéd megadása.
	 * @param dir Az irány.
	 * @param neighbour A szomszéd mezõ.
	 */
	public void setNeighbour(Direction dir, Field neighbour) {
		Logger.enter(this, "setNeighbour", "dir:" + dir.toString(), Logger.getObjectName(neighbour));		
		Logger.exit(this);
	}
	
	/**
	 * Visszaadja az adott irányban lévõ szomszédot.
	 * @param dir Az irány.
	 * @return A szomszéd mezõ.
	 */
	public Field getNeighbour(Direction dir) {
		Logger.enter(this, "getNeighbour", "dir: " + dir.toString());
		Logger.exit(this);
		return new Field();
	}
	
	/**
	 * Egy listát ad vissza azokkal a mezõkkel, amik a mezõtõl radius távolságon belül vannak.
	 * @param radius A távolság.
	 * @return Lista a mezõkrõl.
	 */
	public ArrayList<Field> getNeighbours(int radius) {
		Logger.enter(this, "getNeighbours", "radius:int");
		Logger.exit(this);
		Logger.off();
		ArrayList<Field> list = new ArrayList<Field>();
		for (int i = 0; i < 3; i++) {			
			list.add(new Field());
		}
		list.add(this);
		Logger.on();
		return list;
	}
	
	/**
	 * A mezõben lévõ szagok összintenzitása.
	 * @return Az összintenzitás.
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
	 * Egy szag hozzáadása a mezõhöz.
	 * @param odor A szagobjektum.
	 */
	public void addOdor(Odor odor) {
		Logger.enter(this, "addOdor");
		odors.add(odor);
		Logger.exit(this);
	}
	
	/**
	 * Visszaadja a mezõben található szagok listáját.
	 * @return A szaglista.
	 */
	public ArrayList<Odor> getOdors() {
		Logger.enter(this, "getOdors");
		Logger.exit(this);				
		return odors;
	}

	/**
	 * Szag törlése a mezõrõl.
	 * @param odor A törlendõ szagobjektum.
	 */
	public void removeOdor(Odor odor) {
		Logger.enter(this, "removeOdors", Logger.getObjectName(odor));
		odors.remove(odor);
		Logger.exit(this);
	}

	/**
	 * Entitás hozzáadása a mezõhöz.
	 * @param entity Egy entitás.
	 */
	public void addEntity(Entity e) {
		Logger.enter(this, "addEntity", Logger.getObjectName(e));
		entities.add(e);
		Logger.exit(this);
	}

	/**
	 * Entitás eltávolítása.
	 * @param entity Törlendõ entitás.
	 */
	public void removeEntity(Entity e) {
		Logger.enter(this, "removeEntity", Logger.getObjectName(e));
		entities.remove(e);
		Logger.exit(this);
	}

	/**
	 * Az összes entitás lekérése.
	 * @return Az entitások listája.
	 */
	public ArrayList<Entity> getEntities() {
		Logger.enter(this, "getEntities");
		Logger.exit(this);
		return entities;
	}	
}
