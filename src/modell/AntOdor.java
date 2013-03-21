package modell;

import skeleton.Logger;

public class AntOdor extends Odor implements Active {
	
	/** A tisztas, amin van a szag */
	private Glade glade;
	
	/**
	 * A hangyaszag default konstruktora
	 */
	public AntOdor() {
		Logger.attach("antOdor", this);
	}
	
	/**
	 * A hangyaszag animalasa
	 */
	public void animate() {
		
		Logger.enter(this, "animate", Logger.getObjectName(glade));
		int r = Logger.choose("Elfogyott a hangyaszag?", "Igen", "Nem");
		if (r == 1) {
			neutralize();
		}
		Logger.exit(this);
	}
	
	/**
	 * A hangyaszag semlegesitese
	 */
	public void neutralize() {
		
		Logger.enter(this, "neutralize");
		getPosition().removeOdor(this);
		getGlade().removeActiveObject(this);					
		Logger.exit(this);
	}

	public Glade getGlade() {
		return glade;
	}

	public void setGlade(Glade glade) {
		this.glade = glade;
	}

}
