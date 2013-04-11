package modell;

import java.util.Stack;

import skeleton.Logger;

public class Ant extends Entity implements Active {

	/** Az irany, amerre a hangya megy */
	private Direction direction;
	
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
		
	}
	
	/**
	 * Hangya konstruktor
	 * @param position A mezo, amin van a hangya
	 * @param dir A hangya iranya
	 */
	public Ant(Field position, Direction dir) {
		this();
		this.position = position;
		this.direction = dir;
	}
	
	/**
	 *  Visszaadja, hogy a hangyanal van-e elelem
	 * @return Igaz, ha a hangyanal van elelem
	 */
	public boolean hasFood() {		
		return hasFood;
	}
	
	/**
	 *  A hangya megolese
	 */
	public void kill() {
		position.removeEntity(this);
		position.getGlade().removeActiveObject(this);
		killed = true;		
	}
	
	/**
	 *  A hangya blokkolasa
	 */
	public void block() {		
		blocked = true;		
	}
	
	/**
	 *  A hangya eszik
	 */
	public void eat() {
		hasFood = true;		
	}		
	
	/**
	 * Irany valtoztatas.
	 */
	private void changeDirection() {
		direction = direction.turn(1);
	}
	
	/**
	 * A hangya animalasa
	 */
	public void animate() {		
		
		Field target = null;
		
		if (hasFood) {
			target = popFieldFromMemory();
		} else {
			int intensity = 0;
			for (int i = -1; i <= 1; i++) {
				Field f = position.getNeighbour(direction.turn(i));
				if (target == null || target.getOdorIntensity() > intensity) {
					target = f;								
				}
			}
		}	
		for (Entity e : target.getEntities()) {
			e.collide(this);
		}
		if (killed == false) {
			if (blocked) {					
				changeDirection();
			} else {	
				if (hasFood == false) {
					AntOdor antOdor = new AntOdor();
					position.addOdor(antOdor);
					position.getGlade().addActiveObject(antOdor);
				}
				position.removeEntity(this);
				target.addEntity(this);
			}							
		}		
	}

	/**
	 * Egy mezo kivetele a utvonallista tetejerol.
	 * @return A mezo, ami kovetkezik a visszauthoz.
	 */
	private Field popFieldFromMemory() {						
		return memory.pop();
	}

	/**
	 * Utkozes egy hangyaszsunnel
	 * @param anteater A hangyaszsun, amivel a hangya utkozik
	 */
	public void collide(Anteater anteater) {
		if (anteater.isHungry()) {
			position.removeEntity(this);
			position.getGlade().removeActiveObject(this);
			anteater.increaseEatenAnts(this);
		}		
	}
	
	/**
	 * Beallitja a hangyanal a mergezest
	 */
	public void poison() {		
		poisened = true;		
	}
}
