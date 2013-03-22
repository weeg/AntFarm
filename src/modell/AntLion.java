package modell;

import skeleton.Logger;

public class AntLion extends Entity {
	
	/**
	 * A hangyaleso default konstruktora
	 */
	public AntLion() {
		Logger.attach("antLion", this);
	}
	
	/**
	 *  Utkozes egy hangyaval
	 */
	public void collide(Ant ant) {
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		ant.kill();
		Logger.exit(this);
	}

}
