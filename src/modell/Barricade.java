package modell;

import skeleton.Logger;

public abstract class Barricade extends Entity {
	
	/**
	 * Utkozes egy hangyaval
	 * @ant A hangya, amivel utkozik a barrikad
	 */
	public void collide(Ant ant) {
		
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		ant.block();
		Logger.exit(this);
	}
}
