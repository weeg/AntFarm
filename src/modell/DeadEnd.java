package modell;

/**
 * A palyaszele objektum. 
 *
 */
public class DeadEnd extends Entity {

	public DeadEnd() {		
	}
	
	/**
	 * Utkozes hangyaval, megoli a hangyat.
	 * @param ant A hangya, amivel utkozik.
	 */
	public void collide(Ant ant) {
		ant.kill();		
	}
	
	/**
	 * Utkozes hangyaszsunnel, pihenni kuldi a hangyaszt.
	 * @param anteater A hangyaszsun, amivel utkozik,.
	 */
	public void collide(Anteater anteater) {
		anteater.goRest();		
	}
	
	/**
	 * Utkozes kaviccsal, eltunteti a kavicsot
	 */
	public void collide(Stone stone) {
		stone.kill();
	}
}
