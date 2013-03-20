package modell;

import java.util.ArrayList;
import java.util.HashMap;

public class Field {

	// A szomszedokat tarolo objektum
	private HashMap<Direction, Field> neighbours;
	
	// A szagokat tarolo lista
	private ArrayList<Odor> odors;
	
	// Az entitasokat tarolo lista
	private ArrayList<Entity> entities;
	
	// Az adott iranyban levo szomszed megadasa
	public void setNeighbour(Direction dir, Field neighbour) {
		
	}
	
	// Visszaadja az adott iranyban levo szomszedot
	public Field getNeighbour(Direction dir) {
		return null;
	}
	
	// Egy listat ad vissza azokkal a mezokkel, amik a mezotol raius tavolsagon belul vannak
	public ArrayList<Field> getNeighbours(int radius) {
		return null;
	}
	
	// A mezoben levo szagok osszintenzitasa
	public int getOdorIntensity() {
		return 0;
	}
	
	// Egy szag hozzaadasa a mezohoz
	public void addOdor(Odor odor) {
		
	}
	
	// Visszaadja a mezoben talalhato szagok listajat
	public ArrayList<Odor> getOdors() {
		return null;
	}

	// Szag torlese a mezorol
	public void removeOdor(Odor odor) {
		
	}

	// Entitas hozzaadasa a mezohoz
	public void addEntity(Entity entity) {
		
	}

	// Entitas eltavolitasa
	public void removeEntity(Entity entity) {
		
	}

	// Az osszes entitasa lekerese
	public ArrayList<Entity> getEntities() {
		return null;
	}	
}
