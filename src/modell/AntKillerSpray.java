package modell;

import java.util.ArrayList;

import skeleton.Logger;

public class AntKillerSpray extends Spray {
	
	/**
	 * A hangyairto spray default konstruktora
	 */
	public AntKillerSpray() {
		Logger.attach("antKillerSpray", this);
	}
	
	/**
	 * A hangyairto spray hasznalata
	 * @param center Az a mezo, amire (es kore) fuj a felhasznalo a spray-el
	 */
	public void use(Field center) {
		
		Logger.enter(this, "use", Logger.getObjectName(center));
		ArrayList<Field> fields = center.getNeighbours(getRadius());
		Poison poison = new Poison();
		for (Field field : fields) {
			field.addEntity(poison);
		}		
		// Glade???
		Logger.exit(this);
	}

}
