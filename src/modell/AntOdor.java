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
	 * A hangyaszag konstruktora.
	 * @param glade A tisztas objektum.
	 * @param field A mezo, amin van a hangyaszag
	 */
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
	 * @return Sikeres-e a semlegesites.
	 */
	public boolean neutralize() {
		
		Logger.enter(this, "neutralize");
		position.removeOdor(this);
		glade.removeActiveObject(this);					
		Logger.exit(this);	
		return true;
	}
}
