package modell;

public class AntOdor extends Odor implements Active {
	
	
	/**
	 * A hangyaszag default konstruktora
	 */
	public AntOdor() {
		// Hangyaszag alapertelmezettkent 3
		setIntensity(3);
	}
	
	/**
	 * A hangyaszag konstruktora.
	 * @param field A mezo, amin van a hangyaszag
	 */
	public AntOdor(Field position) {
		this();
		this.position = position;
		
		// Hangyaszag alapertelmezettkent 3
		setIntensity(3);
	}
	
	/**
	 * A hangyaszag animalasa
	 */
	public void animate() {			
		if (intensity > 0) {
			intensity--;
		} else {
			position.removeOdor(this);
			position.getGlade().removeActiveObject(this);
		}
	}
	
	/**
	 * A hangyaszag semlegesitese
	 * @return Sikeres-e a semlegesites.
	 */
	public void neutralize() {
		position.removeOdor(this);
		position.getGlade().removeActiveObject(this);					
	}
}
