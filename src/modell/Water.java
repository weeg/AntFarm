package modell;

import skeleton.Logger;

public class Water extends Barricade {
	
	public Water() {
		Logger.attach("Water", this);
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
