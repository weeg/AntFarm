package modell;

import skeleton.Logger;
/**
 * A méreg objektum.
 */
public class Poison extends Entity implements Active {
	
	/**
	 * A hátralévõ idõ, amíg még a pályán lesz a méreg.
	 */
	private int TTL;

	public Poison() {
		Logger.attach("Poison", this);
	}
	
	public Poison(Glade glade, Field pos) {
		Logger.attach("Poison", this);
		this.glade = glade;
		setPosition(pos);
	}
	
	/**
	 * A méreg animálása. Midenkörben csökkenti a hátralévõ idõt.
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
	 * Ütközés egy hangyával. Megmérgezi a hangyát.
	 */
	public void collide(Ant ant) {
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		ant.poison();
		Logger.exit(this);
	}

}
