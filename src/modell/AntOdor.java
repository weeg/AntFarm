package modell;

import skeleton.Logger;

public class AntOdor extends Odor implements Active {
	
	/**
	 * A hangyaszag default konstruktora
	 */
	public AntOdor() {
		Logger.attach("antOdor", this);
	}
	
	/**
	 * A hangyaszag animalasa
	 */
	public void animate(Glade glade) {
		
		Logger.enter(this, "animate", Logger.getObjectName(glade));
		Logger.exit(this);
	}
	
	/**
	 * A hangyaszag semlegesitese
	 */
	public void neutralize() {
		
		Logger.enter(this, "neutralize");
		int r = Logger.choose("Elfogyott a hangyaszag?", "Igen", "Nem");
		if (r == 1) {
			getPosition().removeOdor(this);
			// Field???
		}
		Logger.exit(this);
	}

}
