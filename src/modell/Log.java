package modell;

import skeleton.Logger;
/**
 * Fatörzs akadály.
 */
public class Log extends Barricade {
	
	public Log() {
		Logger.attach("Log", this);
	}
	
	/**
	 * Ütközés egy hangyászsünnel. Blokkolja a hangyászsünt.
	 * @param anteater A hangyászsün, amivel ütközik.
	 */
	public void collide(Anteater anteater) {
		Logger.enter(this, "collide", Logger.getObjectName(anteater));
		anteater.block();
		Logger.exit(this);
	}

}
