package modell;

import skeleton.Logger;
/**
 * A mereg objektum.
 */
public class Poison extends Entity implements Active {
	
	/**
	 * A hatralevo ido, amig meg a palyan lesz a mereg.
	 */
	private int TTL;
	
	/**
	 * A mereg default konstruktora.
	 */
	public Poison() {
		Logger.attach("Poison", this);
	}
	
	/**
	 * A mereg konstruktora.
	 * @param glade A tisztas objektum.
	 * @param pos A mezo, amin a mereg van.
	 */
	public Poison(Glade glade, Field pos) {
		Logger.attach("Poison", this);
		this.glade = glade;
		setPosition(pos);
	}
	
	/**
	 * A mereg animalasa. Mindenkorben csokkenti a hatralevo idot.
	 */
	public void animate() {
		Logger.enter(this, "animate");
		int r = Logger.choose("Elillant a mereg?", "Igen", "Nem");
		if (r == 1) {
			glade.removeActiveObject(this);
			position.removeEntity(this);
		}		
		Logger.exit(this);
	}
	
	/**
	 * Utkozes egy hangyaval. Megmergezi a hangyat.
	 */
	public void collide(Ant ant) {
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		ant.poison();
		Logger.exit(this);
	}

}
