package modell;

import skeleton.Logger;

/**
 * A pályaszéle objektum. 
 *
 */
public class DeadEnd extends Entity {

	public DeadEnd() {
		Logger.attach("Deadend", this);
	}
	
	/**
	 * Ütközés hangyával, megöli a hangyát.
	 * @param ant A hangya, amivel ütközik.
	 */
	public void collide(Ant ant) {
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		ant.kill();
		Logger.exit(this);
	}
	
	/**
	 * Ütközés hangyászsünnel, pihenni küldi a hangyászt.
	 * @param anteater A hangyászsün, amivel ütközik,.
	 */
	public void collide(Anteater anteater) {
		Logger.enter(this, "collide", Logger.getObjectName(anteater));
		anteater.goRest();
		Logger.exit(this);
	}
}
