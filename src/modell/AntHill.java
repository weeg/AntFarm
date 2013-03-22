package modell;

import skeleton.Logger;

public class AntHill extends Entity implements Active {
	
	/** A tisztas, amin van a hangyaboly */
	private Glade glade;
	
	/**
	 * A hangyaboly default konstruktora
	 */
	public AntHill(Glade gl) {
		Logger.attach("anthill", this);
		glade = gl;
	}
	
	/**
	 *  Utkozes egy hangyaval
	 *  @param ant A hangya, amivel a hangyaboly utkozik
	 */
	public void collide(Ant ant) {
		
		Logger.enter(this, "collide", Logger.getObjectName(ant));
		if (ant.hasFood()) {
			ant.kill();
		}
		Logger.exit(this);
	}
	
	/**
	 * A hangyaboly animalasa
	 */
	public void animate() {		
		
		Logger.enter(this, "animate", Logger.getObjectName(glade));
		int r = Logger.choose("Hozzon letre a hangyaboly egy uj hangyat?", "Igen", "Nem");
		if (r == 1) {
			Ant ant = new Ant();
		}		
		Logger.exit(this);
	}
}
