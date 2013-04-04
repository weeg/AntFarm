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
	 * A hangyaszag konstruktora.
	 * @param field A mezo, amin van a hangyaszag
	 */
	public AntOdor(Field position) {
		this();
		this.position = position;
	}
	
	/**
	 * A hangyaszag animalasa
	 */
	public void animate() {
		
		Logger.enter(this, "animate");
		int r = Logger.choose("Elfogyott a hangyaszag?", "Igen", "Nem");
		if (r == 1) {
			position.getGlade().removeActiveObject(this);
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
		position.getGlade().removeActiveObject(this);					
		Logger.exit(this);	
		return true;
	}
}
