package modell;

import skeleton.Logger;

public class Water extends Barricade {
	
	public Water() {}
	/**
	 * Utkozes egy hangyaszsunnel. Blokkolja a hangyaszsunt.
	 * @param anteater A hangyaszsun, amivel utkozik.
	 */
	public void collide(Anteater anteater) {
		anteater.block();
	}

}
