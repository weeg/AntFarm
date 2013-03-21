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
		Logger.exit(this);
	}
	
	/**
	 * A hangyaszsun animalasa
	 */
	public void animate() {
		
		Logger.enter(this, "animate");
		ArrayList<Entity> entities = getPosition().getEntities();
		for (Entity entity : entities) {
			entity.collide(this);
		}
		Logger.exit(this);
	}

	
}
