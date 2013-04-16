package modell;

import java.util.ArrayList;

public class AntKillerSpray extends Spray {
	
	/**
	 * A hangyairto spray default konstruktora
	 */
	public AntKillerSpray() {		
	}
	
	/**
	 * A hangyairto spray konstruktora
	 * @param glade A tisztas objektum.
	 */
	public AntKillerSpray(Glade glade) {
		this();
		this.glade = glade;
	}
	
	/**
	 * A hangyairto spray hasznalata
	 * @param center Az a mezo, amire (es kore) fuj a felhasznalo a spray-el
	 */
	public void use(Field center) {				
		
		if (quantity > 0) {
			ArrayList<Field> fields = center.getNeighbours(getRadius());
			Poison poison = new Poison();
			for (Field field : fields) {
				field.addEntity(poison);
			}		
			glade.addActiveObject(poison);
			
			quantity--;
		}
		
	}

}
