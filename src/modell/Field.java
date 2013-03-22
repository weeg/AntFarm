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
	 * Az adott irányban lévő szomszéd megadása.
	 * @param dir Az irány.
	 * @param neighbour A szomszéd mező.
	 */
	public void setNeighbour(Direction dir, Field neighbour) {
		Logger.enter(this, "setNeighbour", "dir:" + dir.toString(), Logger.getObjectName(neighbour));
		neighbours.put(dir, neighbour);
		Logger.exit(this);
	}
	
	/**
	 * Visszaadja az adott irányban lévő szomszédot.
	 * @param dir Az irány.
	 * @return A szomszéd mező.
	 */
	public Field getNeighbour(Direction dir) {
		Logger.enter(this, "getNeighbour", "dir:Direction");
		Logger.exit(this);
		return neighbours.get(dir);
	}
	
	/**
	 * Egy listát ad vissza azokkal a mezőkkel, amik a mezőtől radius távolságon belül vannak.
	 * @param radius A távolság.
	 * @return Lista a mezőkről.
	 */
	public ArrayList<Field> getNeighbours(int radius) {
		Logger.enter(this, "getNeighbours", "radius:int");
		Logger.exit(this);
		return null;
	}
	
	/**
	 * A mezőben lévő szagok összintenzitása.
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
	 * Egy szag hozzáadása a mezőhöz.
	 * @param odor A szagobjektum.
	 */
	public void addOdor(Odor odor) {
		Logger.enter(this, "addOdor");
		odors.add(odor);
		Logger.exit(this);
	}
	
	/**
	 * Visszaadja a mezőben található szagok listáját.
	 * @return A szaglista.
	 */
	public ArrayList<Odor> getOdors() {
		Logger.enter(this, "getOdors");
		Logger.exit(this);
		return odors;
	}

	/**
	 * Szag törlése a mezőről.
	 * @param odor A törlendő szagobjektum.
	 */
	public void removeOdor(Odor odor) {
		Logger.enter(this, "removeOdors", Logger.getObjectName(odor));
		odors.remove(odor);
		Logger.exit(this);
	}

	/**
	 * Entitás hozzáadása a mezőhöz.
	 * @param entity Egy entitás.
	 */
	public void addEntity(Entity e) {
		Logger.enter(this, "addEntity", Logger.getObjectName(e));
		entities.add(e);
		Logger.exit(this);
	}

	/**
	 * Entitás eltávolítása.
	 * @param entity Törlendő entitás.
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
