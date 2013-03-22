package modell;

import java.util.ArrayList;

import skeleton.Logger;

public class Anteater extends Entity implements Active {
	
	/** A hangyaszsun iranya */
	private Direction direction;
	
	/** Az aktualis palyaralepes soran mar megevett hangyak szama */
	private int eatenAnts;
	
	/** Visszamarado ido, amig pihen az ujabb palyara lepes elott */
	private int timeToRest;
	
	/** Pihen-e a hangyaszsun */
	private boolean isResting;
	
	/** Lekuzdhetetlen akadalyba utkozott-e */
	private boolean blocked;
	
	/**
	 * A hangyaszsun default konstruktor
	 */
	public Anteater() {
		Logger.attach("anteater", this);
	}
	
	public Anteater(Field position) {
		this();
		this.position = position;
	}
	
	/**
	 * Visszaadja, hogy ehes-e a hangyaszsun
	 */
	public boolean isHungry() {
		Logger.enter(this, "isHungry");
		Logger.exit(this);
		int r = Logger.choose("Ehes a hangyaszsun?", "Igen", "Nem");
		switch (r) {
		case 1:
			return true;
		case 2:
			return false;		
		}
		return false;
	}
	
	/**
	 * Pihenni kuldi a hangyaszsunt
	 */
	public void goRest() {
		Logger.enter(this, "goRest");
		Logger.exit(this);
	}
	
	/**
	 * Utkozes egy hangyaval
	 */
	public void collide(Ant ant) {
		// Semmi nem tortenik, de azert a fuggvenyt megmutatjuk
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		Logger.exit(this);
	}

	/**
	 * Megevett hangyak szamanak novelese
	 */
	public void increaseEatenAnts() {
		Logger.enter(this, "increaseEatenAnts");
		Logger.exit(this);
	}
	
	/**
	 * A hangyaszsun blokkolasa
	 */
	public void block() {
		Logger.enter(this, "block");
		blocked = true;
		Logger.exit(this);
	}
	
	/**
	 * A hangyaszsun animalasa
	 */
	public void animate() {
		
		Logger.enter(this, "animate");				
		direction = Direction.N;		
		Field target = position.getNeighbour(direction);
		
		int r = Logger.choose("Mivel utkozzon a hangyaszsun?", "Kovel", "Tocsaval", "Faronkkel", 
				"Hangyaval", "Palya szelevel");
		Logger.off();
		switch (r) {
			case 1:
				target.addEntity(new Stone());
				break;
			case 2:
				target.addEntity(new Water());
				break;
			case 3:
				target.addEntity(new Log());
				break;
			case 4:
				target.addEntity(new Ant());
				break;
			case 5:
				target.addEntity(new DeadEnd());
				break;			
		}
		Logger.on();
		for (Entity e : target.getEntities()) {
			e.collide(this);
		}
		
		if (blocked) {
			changeDirection();
		} else {
			position.removeEntity(this);
			target.addEntity(this);
		}
		
		Logger.exit(this);	
	}
	
	private void changeDirection() {
		Logger.enter(this, "changeDirection");
		Logger.exit(this);
	}


	
}
