package modell;

import java.util.ArrayList;

import skeleton.Logger;

public class Ant extends Entity implements Active {

	/** Az irany, amerre a hangya megy */
	private Direction direction;
	
	/** A tisztas, amin van a hangya */
	private Glade glade;
	
	/** Azok a mezok, amiken vegigment a hangya */
	private ArrayList<Field> memory = new ArrayList<Field>();
	
	/** Van-e a hangyanal elelem */
	private boolean hasFood;
	
	/** Meg van-e mergezve a hangya */
	private boolean poisened;
	
	/** Akadalyba utkozott-e a hangya */
	private boolean blocked;
	
	/** Meg hany korig fog elni (ha mergezett) */
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
		getPosition().removeEntity(this);
		glade.removeActiveObject(this);
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
	 */
	public void animate() {		
		
		Logger.enter(this, "animate", Logger.getObjectName(glade));
		for (int i = 0; i < 3; i++) {
			Field f = null;
			switch (i) {
			case 0:
				f = getPosition().getNeighbour(Direction.getLeftDirection(direction));
				break;
			case 1:
				f = getPosition().getNeighbour(direction);
				break;
			case 2:
				f = getPosition().getNeighbour(Direction.getRightDirection(direction));
				break;
			}
			int intensity = f.getOdorIntensity();
			// TODO
		}
		Logger.exit(this);
	}
	
	/**
	 * Utkozes egy hangyaszsunnel
	 * @param anteater A hangyaszsun, amivel a hangya utkozik
	 */
	public void collide(Anteater anteater) {
	
		Logger.enter(this, "collide", Logger.getObjectName(anteater));
		if (anteater.isHungry()) {
			kill();
			anteater.increaseEatenAnts();
		}
		Logger.exit(this);
	}

	public Glade getGlade() {
		return glade;
	}

	public void setGlade(Glade glade) {
		this.glade = glade;
	}
	
	/**
	 * Beállítja a hangyánál a mérgezést.
	 */
	public void poison() {
		Logger.enter(this, "poison");
		this.poisened = true;
		Logger.exit(this);
	}
}
