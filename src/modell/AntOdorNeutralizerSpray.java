package modell;

import skeleton.Logger;

public class AntOdorNeutralizerSpray extends Spray {
	
	/**
	 * A hangyaszag semlegesito spray default konstruktora
	 */
	public AntOdorNeutralizerSpray() {
		Logger.attach("antOdorNeutralizerSpray", this);
	}
	
	/**
	 * A hangyaszag semlegesito spray hasznalata
	 * @param center Az a mezo, amire (es kore) fuj a felhasznalo a spray-el
	 */
	public void use(Field center) {
		
		Logger.enter(this, "use", Logger.getObjectName(center));
		// TODO
		Logger.exit(this);
	}}
