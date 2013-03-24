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
	
	public AntOdor(Glade glade, Field field) {
		this();
		this.glade = glade;
		this.position = field;
	}
	
	/**
	 * A hangyaszag animalasa
	 */
	public void animate() {
		
		Logger.enter(this, "animate");
		int r = Logger.choose("Elfogyott a hangyaszag?", "Igen", "Nem");
		if (r == 1) {
			glade.removeActiveObject(this);
		}		
		Logger.exit(this);
	}
	
	/**
	 * A hangyaszag semlegesitese
	 */
	public boolean neutralize() {
		
		Logger.enter(this, "neutralize");
		position.removeOdor(this);
		glade.removeActiveObject(this);					
		Logger.exit(this);	
		return true;
	}

	public Glade getGlade() {
		return glade;
	}

	public void setGlade(Glade glade) {
		this.glade = glade;
	}

}
