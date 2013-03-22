package modell;

import skeleton.Logger;

public class Water extends Barricade {
	
	public Water() {
		Logger.attach("Water", this);
	}
	/**
	 * Utkozes egy hangyaszsunnel. Blokkolja a hangyaszsunt.
	 * @param anteater A hangyaszsun, amivel utkozik.
	 */
	public void collide(Anteater anteater) {
		Logger.enter(this, "collide", Logger.getObjectName(anteater));
		anteater.block();
		Logger.exit(this);
	}

}
