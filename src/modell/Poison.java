package modell;

import skeleton.Logger;
/**
 * A méreg objektum.
 */
public class Poison extends Entity implements Active {
	
	/**
	 * A hátralévő idő, amíg még a pályán lesz a méreg.
	 */
	private int TTL;

	public Poison() {
		Logger.attach("Poison", this);
	}
	
	/**
	 * A méreg animálása. Midenkörben csökkenti a hátralévő időt.
	 */
	public void animate() {
		Logger.enter(this, "animate");
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
