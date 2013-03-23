package modell;

import java.util.ArrayList;
import java.util.Iterator;

import skeleton.Logger;

public class AntOdorNeutralizerSpray extends Spray {
	
	/**
	 * A hangyaszag semlegesito spray default konstruktora
	 */
	public AntOdorNeutralizerSpray() {
		Logger.attach("antOdorNeutralizerSpray", this);
	}
	
	public AntOdorNeutralizerSpray(Glade glade) {
		this();
		this.glade = glade;
	}
	
	/**
	 * A hangyaszag semlegesito spray hasznalata
	 * @param center Az a mezo, amire (es kore) fuj a felhasznalo a spray-el
	 */
	public void use(Field center) {
		
		Logger.enter(this, "use", Logger.getObjectName(center));
		ArrayList<Field> fields = center.getNeighbours(radius);		
		for (Field field : fields) {
			
			ArrayList<Odor> odors = field.getOdors();
			for (int i = 0; i < odors.size(); i++) {
				if (odors.get(i).neutralize()) {
					i--;
				}
			}
		}
		Logger.exit(this);
	}}
