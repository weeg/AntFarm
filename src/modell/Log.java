package modell;

import skeleton.Logger;
/**
 * Fatorzs akadaly.
 */
public class Log extends Barricade {
	
	public Log() {
		Logger.attach("Log", this);
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
