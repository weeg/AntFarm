package modell;

import java.util.ArrayList;

public class AntOdorNeutralizerSpray extends Spray {
	
	/**
	 * A hangyaszag semlegesito spray default konstruktora
	 */
	public AntOdorNeutralizerSpray() {	
	}
	
	/**
	 * A hangyaszag semlegesito spray konstruktora
	 * @param glade A tisztas objektum.
	 */
	public AntOdorNeutralizerSpray(Glade glade) {
		this();
		this.glade = glade;
	}
	
	/**
	 * A hangyaszag semlegesito spray hasznalata
	 * @param center Az a mezo, amire (es kore) fuj a felhasznalo a spray-el
	 */
	public void use(Field center) {
		if (quantity > 0) {
			ArrayList<Field> fields = center.getNeighbours(radius);
			for (Field field : fields) {
				ArrayList<Odor> copy = new ArrayList<Odor>(field.getOdors());
				for (Odor odor : copy) {
					odor.neutralize();
				}
				field.getView().change();
			}			
			quantity--;
		}
	}
}
