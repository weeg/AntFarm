package modell;

import java.util.ArrayList;

import skeleton.Logger;

public class Ant extends Entity implements Active {

	/** 
	 * Az irany, amerre a hangya megy
	 */
	private Direction direction;
	
	/**
	 * Azok a mezok, amiken vegigment a hangya
	 */
	private ArrayList<Field> memory = new ArrayList<Field>();
	
	/**
	 *  Van-e a hangyanal elelem
	 */
	private boolean hasFood;
	
	/**
	 * Meg van-e mergezve a hangya
	 */
	private boolean poisened;
	
	/**
	 *  Akadalyba utkozott-e a hangya
	 */
	private boolean blocked;
	
	/**
	 *  Meg hany korig fog elni (ha mergezett)
	 */
	private int TTL;
	
	/**
	 * A hangya default konstruktora
	 */
	public Ant() {
		Logger.attach("ant", this);
	}
	
	/**
	 *  Visszaadja, hogy a hangyanal van-e elelem
	 * @return Igaz, ha a hangyanal van elelem
	 */
	public boolean hasFood() {
		
		Logger.enter(this, "hasFood");		
		Logger.exit(this);
		int r = Logger.choose("Legyen-e a hangyanal elelem?", "Igen", "Nem");
		switch (r) {
		case 1:
			return true;
		case 2:
			return false;		
		}
		return false;
	}
	
	/**
	 *  A hangya megolese
	 */
	public void kill() {
		Logger.enter(this, "kill");
		Logger.exit(this);
	}
	
	/**
	 *  A hangya blokkolasa
	 */
	public void block() {
		Logger.enter(this, "block");
		Logger.exit(this);
	}
	
	/**
	 *  A hangya eszik
	 */
	public void eat() {
		Logger.enter(this, "eat");
		Logger.exit(this);
	}		
		
	/**
	 * A hangya animalasa
	 * @param A tisztas, amin a hangya talalhato
	 */
	public void animate(Glade glade) {		
		
	}
	
	/**
	 * Utkozes egy hangyaszsunnel
	 * @param anteater A hangyaszsun, amivel a hangya utkozik
	 */
	public void collide(Anteater anteater) {
	
		Logger.enter(this, "collide", Logger.getObjectName(anteater));
		if (anteater.isHungry()) {
			// remove entity
			// remove active entity
			anteater.increaseEatenAnts();
		}
		Logger.exit(this);
	}	
}
