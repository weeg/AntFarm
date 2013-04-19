package modell;

import java.util.ArrayList;
import java.util.Stack;

import prototype.Commands;
import prototype.Logger;

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
		
		// hangyaszag otthagyasa
		leaveAntOdor();
	}		
	
	/**
	 * Irany valtoztatas.
	 */
	private void changeDirection() {
		direction = direction.turn(-1);
	}
	
	/**
	 * A hangya animalasa
	 */
	public void animate() throws Exception {		
		
		Field target = null;
		// Alapertelemzettkent nincs blokkolva
		blocked      = false;
		
		if (poisened) {
			TTL--;
			if (TTL == 0) {
				TTL = 0;
				kill();
				return;
			}
		}
		
		if (hasFood) {
			target = popFieldFromMemory();
		} else {
			int intensity = 0;
			
			// Alapertelmezettkent lepjen elore.
			target = position.getNeighbour(direction);

			for (int i = -1; i <= 1; i++) {
				Field f = position.getNeighbour(direction.turn(i));
				
				// Ha a szomszedos szagok nagyobbak, akkor forduljon csak el.
				if (f != null && f.getOdorIntensity() > intensity) {
					target = f;
					intensity = target.getOdorIntensity();
				}
			}
		}
		
		// Uj lista, hogy torlesnel ne legyen gond.
		ArrayList<Entity> copy = new ArrayList<Entity>(target.getEntities());
		for (Entity e : copy) {
			e.collide(this);
		}
		if (killed == false) {
			if (blocked) {			
				changeDirection();
			} else {
				
				// Ha epp most utkozott a kajaval, akkor nem hagy maga utan szagot
				// Ezt az eat metodusban teszi meg.
				if (hasFood == false) {
					
					// hangyaszag otthagyasa
					leaveAntOdor();
					
					// Eltarolja a megtett utat.
					addFieldToMemory(position);
				}
				position.removeEntity(this);
				target.addEntity(this);
			}							
		}				
	}
	
	/**
	 * Ha ellep egy mezorol, akkor hangyaszagot hagy maga utan.
	 */
	private void leaveAntOdor() {
		AntOdor antOdor = new AntOdor();
		position.addOdor(antOdor);
		position.getGlade().addActiveObject(antOdor);
	}

	/**
	 * Egy mezo kivetele a utvonallista tetejerol.
	 * @return A mezo, ami kovetkezik a visszauthoz.
	 */
	private Field popFieldFromMemory() {
		return memory.isEmpty() ? null : memory.pop();
	}
	
	/**
	 * Megtett ut eltarolasa.
	 * @param field Mezo amirol ellep.
	 */
	private void addFieldToMemory(Field field) {
		memory.add(field);
	}
	
	/**
	 * Utkozes egy hangyaszsunnel
	 * @param anteater A hangyaszsun, amivel a hangya utkozik
	 */
	public void collide(Anteater anteater) {
		if (anteater.isHungry()) {
			kill();
			anteater.increaseEatenAnts(this);
		}		
	}
	
	/**
	 * Utkozes egy kaviccsal.
	 * @param s A kavics amivel utkozik a hangya.
	 */
	public void collide(Stone s) {
		kill();
	}
	
	/**
	 * Beallitja a hangyanal a mergezest
	 */
	public void poison() {
		poisened = true;
		TTL = 3;
	}
}
