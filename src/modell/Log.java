package modell;

/**
 * Fatorzs akadaly.
 */
public class Log extends Barricade {
	
	public Log() {}
	
	/**
	 * Utkozes egy hangyaszsunnel. Blokkolja a hangyaszsunt.
	 * @param anteater A hangyaszsun, amivel utkozik.
	 */
	public void collide(Anteater anteater) {
		anteater.block();
	}
}
