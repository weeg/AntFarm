package modell;

import java.util.ArrayList;
import java.util.Stack;

import skeleton.Logger;

public class Ant extends Entity implements Active {

	/** Az irany, amerre a hangya megy */
	private Direction direction;
	
	/** A tisztas, amin van a hangya */
	private Glade glade;
	
	/** Azok a mezok, amiken vegigment a hangya */
	private Stack<Field> memory = new Stack<Field>();
	
	/** Van-e a hangyanal elelem */
	private boolean hasFood = false;
	
	/** Meg van-e mergezve a hangya */
	private boolean poisened = false;
	
	/** Akadalyba utkozott-e a hangya */
	private boolean blocked = false;
	
	/**
	 * Halott-e a hangya.
	 */
	private boolean killed = false;
	
	/** Meg hany korig fog elni (ha mergezett) */
	private int TTL;
	
	/**
	 * A hangya default konstruktora
	 */
	public Ant() {
		Logger.attach("ant", this);
	}
	
	/**
	 * Hangya konstruktor
	 * @param glade A tisztas, amin van a hangya
	 * @param position A mezo, amin van a hangya
	 * @param dir A hangya iranya
	 */
	public Ant(Glade glade, Field position, Direction dir) {
		this();
		this.glade = glade;
		this.position = position;
		this.direction = dir;
	}
	
	/**
	 *  Visszaadja, hogy a hangyanal van-e elelem
	 * @return Igaz, ha a hangyanal van elelem
	 */
	public boolean hasFood() {
		Logger.enter(this, "hasFood");		
		Logger.exit(this);
		return hasFood;
	}
	
	/**
	 *  A hangya megolese
	 */
	public void kill() {
		
		Logger.enter(this, "kill");
		getPosition().removeEntity(this);
		glade.removeActiveObject(this);
		killed = true;
		Logger.exit(this);
	}
	
	/**
	 *  A hangya blokkolasa
	 */
	public void block() {
		Logger.enter(this, "block");
		blocked = true;
		Logger.exit(this);
	}
	
	/**
	 *  A hangya eszik
	 */
	public void eat() {
		Logger.enter(this, "eat");
		Logger.exit(this);
	}		
	
	
	private void changeDirection() {
		Logger.enter(this, "changeDirection");
		Logger.exit(this);
	}
	
	/**
	 * A hangya animalasa
	 */
	public void animate() {		
		Logger.enter(this, "animate", Logger.getObjectName(glade));
		int a = Logger.choose("Legyen-e a hangyanal elelem?", "Igen", "Nem");
		switch (a) {
			case 1:
				hasFood = true;
				break;
			case 2:
				hasFood = false;	
				break;
		}
		Logger.off();
		direction = Direction.NE;
		Field target = null;
		if (hasFood) {
			target = popFieldFromMemory();
		}
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
			if (target == null) {
				target = f;
			}
			if (target.getOdorIntensity() < intensity) {
				target = f; 
			}
		}
		int r = Logger.choose("Mivel ütközzön a hangya?", "Akadállyal (Kővel)", "Hangyával", "Hangyabollyal", 
				"Hangyalesővel", "Hangyászsünnel", "Élelemmel", "Méreggel", "Pályaszélével");
		switch (r) {
			case 1:
				target.addEntity(new Stone());
				break;
			case 2:
				target.addEntity(new Ant());
				break;
			case 3:
				target.addEntity(new AntHill(glade));
				break;
			case 4:
				target.addEntity(new AntLion());
				break;
			case 5:
				target.addEntity(new Anteater());
				break;
			case 6:
				target.addEntity(new Food());
				break;
			case 7:
				target.addEntity(new Poison());
				break;
			case 8:
				target.addEntity(new DeadEnd());
				break;
		}
		Logger.on();
		for (Entity e : target.getEntities()) {
			e.collide(this);
		}
		if (killed == false) { 
			if (hasFood == false) {
				if (blocked) {
					this.changeDirection();
				} else {
					AntOdor ao = new AntOdor();
					this.getPosition().addOdor(ao);
					glade.addActiveObject(ao);
				}
			}
			this.getPosition().removeEntity(this);
			target.addEntity(this);
		}
		Logger.exit(this);	
	}

	private Field popFieldFromMemory() {
		Logger.enter(this, "popFieldFromMemory");
		Logger.exit(this);
		//return memory.pop();
		return new Field();
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
