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
	@SuppressWarnings("unchecked")
	public void use(Field center) {
		ArrayList<Field> fields = center.getNeighbours(radius);
		for (Field field : fields) {						
			for (Odor odor : (ArrayList<Odor>)field.getOdors().clone()) {
				odor.neutralize();
			}			
		}		
	}
}
