package modell;

import java.util.ArrayList;
import java.util.HashMap;

import view.View;

public class Field implements Drawable {
	
	/** A tisztas, amin van a mezo */
	private Glade glade;

	/** A szomszedokat tarolo objektum */
	private HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>();
	
	/** A szagokat tarolo lista */
	private ArrayList<Odor> odors = new ArrayList<Odor>();
	
	/** Az entitasokat tarolo lista */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	/** Renderelo nezet hozzaasa */
	protected View view;
	
	public Field() {}
	
	/**
	 * A mezo konstruktora
	 * @param glade A tisztas, amin van a mezo
	 */
	public Field(Glade glade) {
		this.glade = glade;
	}
	
	/**
	 * Az adott iranyban levo szomszed megadasa.
	 * @param dir Az irany.
	 * @param neighbour A szomszed mezo.
	 */
	public void setNeighbour(Direction dir, Field neighbour) {
		neighbours.put(dir, neighbour);
	}
	
	/**
	 * Visszaadja az adott iranyban levo szomszedot.
	 * @param dir Az irany.
	 * @return A szomszed mezo.
	 */
	public Field getNeighbour(Direction dir) {
		return neighbours.get(dir);
	}
	
	/**
	 * Egy listat ad vissza azokkal a mezokkel, amik a mezotol radius tavolsagon belul vannak.
	 * @param radius A tavolsag.
	 * @return Lista a mezokrol.
	 */
	public ArrayList<Field> getNeighbours(int radius) {
		ArrayList<Field> list = new ArrayList<Field>();
		
		if (radius > 0) {
			Direction dir = Direction.N;
			for (int i = 0; i <= 5; i++) {
				Field neighbour = neighbours.get(dir.turn(i));
				if (neighbour != null) {
					ArrayList<Field> neighbour_list = neighbour.getNeighbours(radius-1);
					// Egy szomszedot csak 1x adjon hozza
					for (Field n : neighbour_list) {
						if (!list.contains(n)) {
							list.add(n);
						}
					}
					if (!list.contains(neighbour)) {
						list.add(neighbour);
					}
				}
			}
		}
		
		list.add(this);
		return list;
	}
	
	/**
	 * A mezoben levo szagok osszintenzitasa.
	 * @return Az osszintenzitas.
	 */
	public int getOdorIntensity() {
		int intens = 0;
		for (Odor o : odors) {
			intens += o.getIntensity();
		}
		return intens;
	}
	
	/**
	 * Egy szag hozzaadása a mezohoz.
	 * @param odor A szagobjektum.
	 */
	public void addOdor(Odor odor) {
		odor.setPosition(this);
		odors.add(odor);
		getView().change();
	}
	
	/**
	 * Visszaadja a mezoben talalhato szagok listajat.
	 * @return A szaglista.
	 */
	public ArrayList<Odor> getOdors() {				
		return odors;
	}

	/**
	 * Szag torlese a mezorol.
	 * @param odor A torlendo szagobjektum.
	 */
	public void removeOdor(Odor odor) {
		odors.remove(odor);
		getView().change();
	}

	/**
	 * Entitas hozzaadasa a mezohoz.
	 * @param e Egy entitas.
	 */
	public void addEntity(Entity e) {
		
		// Egy entitast, csak 1x adjon hozza a listahoz. pl.: mergek eseten
		if (!entities.contains(e)) {
			entities.add(e);
			
			// Pozicio felulirasa
			e.setPosition(this);
		}	
	}

	/**
	 * Entitas eltavolitasa.
	 * @param e Torlendo entitas.
	 */
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	/**
	 * Az osszes entitas lekerese.
	 * @return Az entitasok listaja.
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	/**
	 * Visszaadja a tisztast, amin van a mezo
	 * @return A tisztas objektum
	 */
	public Glade getGlade() {
		return glade;
	}
	
	/**
	 * Beallitja a renderelo nezetet.
	 * @param view A kirajzolashoz hasznalt renderelo nezet.
	 */
	public void setView(View view) {
		view.setModel(this);
		this.view = view;
	}
	
	/**
	 * Visszaadja a renderelo nezetet.
	 * @return a renderelo nezet. 
	 */
	public View getView() {
		return view;
	}
}
