package modell;

import skeleton.Logger;

/**
 * A palyaszele objektum. 
 *
 */
public class DeadEnd extends Entity {

	public DeadEnd() {
		Logger.attach("Deadend", this);
	}
	
	/**
	 * Utkozes hangyaval, megoli a hangyat.
	 * @param ant A hangya, amivel utkozik.
	 */
	public void collide(Ant ant) {
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		ant.kill();
		Logger.exit(this);
	}
	
	/**
	 * Utkozes hangyaszsunnel, pihenni kuldi a hangyaszt.
	 * @param anteater A hangyaszsun, amivel utkozik,.
	 */
	public void collide(Anteater anteater) {
		Logger.enter(this, "collide", Logger.getObjectName(anteater));
		anteater.goRest();
		Logger.exit(this);
	}
}
