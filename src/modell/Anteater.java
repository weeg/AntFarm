package modell;

import java.util.ArrayList;

public class Anteater extends Entity implements Active {
	
	/** A hangyaszsun iranya */
	private Direction direction = Direction.N;

	/** Az aktualis palyaralepes soran mar megevett hangyak szama */
	private int eatenAnts = 0;
	
	/** Visszamarado ido, amig pihen az ujabb palyara lepes elott */
	private int timeToRest = restTime;
	private final static int restTime = 10;
	
	/** Pihen-e a hangyaszsun */
	private boolean isResting = false;
	
	/** Lekuzdhetetlen akadalyba utkozott-e */
	private boolean blocked;	
	
	/**
	 * A hangyaszsun default konstruktor
	 */
	public Anteater() {
		
	}
	
	public Anteater(Field position) {
		this();
		this.position = position;
	}
	
	/**
	 * Visszaadja, hogy ehes-e a hangyaszsun
	 */
	public boolean isHungry() {		
		return eatenAnts < 7 ? true : false;
	}
	
	/**
	 * Pihenni kuldi a hangyaszsunt
	 */
	public void goRest() {
		isResting = true;
		timeToRest = restTime;
		
		// A hangyaszsun megfordul, igy ugyanarra megy vissza, ahonnan jott
		direction = direction.turn(3);
	}
	
	/**
	 * Utkozes egy hangyaval, megoli a hangyat.
	 * @param ant A hangya, amivel utkozik.
	 */
	public void collide(Ant ant) {		
		if (isHungry()) {
			ant.kill();
			eatenAnts++;
		}		
	}

	/**
	 * Megevett hangyak szamanak novelese
	 * @param a A megevett hangya.
	 */
	public void increaseEatenAnts(Ant a) {
		eatenAnts++;
	}
	
	/**
	 * A hangyaszsun blokkolasa
	 */
	public void block() {		
		blocked = true;
	}
	
	/**
	 * A hangyaszsun animalasa
	 */
	public void animate() throws Exception{
		
		if (!isResting)
		{			
			Field target = position.getNeighbour(direction);
			
			blocked = false;
			
			// Uj lista, hogy torlesnel ne legyen gond.
			ArrayList<Entity> copy = new ArrayList<Entity>(target.getEntities());
			for (Entity e : copy) {
				e.collide(this);
			}
					
			if (blocked) {
				changeDirection();
			} else {
				getView().change();
				position.removeEntity(this);
				target.addEntity(this);
				getView().change();
			}			
		} else {
			timeToRest--;		
			if (timeToRest <= 0) {
				isResting = false;
			}
		}
	}
	
	/**
	 * Iranyvaltoztatas.
	 */
	private void changeDirection() {
		direction = direction.turn(-1);
	}
	
	/**
	 * Visszadja a hangyaszsun iranyat.
	 * @return Az irany.
	 */
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction d) {
		direction = d;
	}
}
