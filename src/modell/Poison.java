package modell;

import view.PoisonView;

/**
 * A mereg objektum.
 */
public class Poison extends Entity implements Active {
	
	/**
	 * A hatralevo ido, amig meg a palyan lesz a mereg.
	 */
	private int TTL = 10;
	
	/**
	 * A mereg default konstruktora.
	 */
	public Poison() {
		this.setView(new PoisonView());
	}
	
	/**
	 * A mereg konstruktora.
	 * @param glade A tisztas objektum.
	 * @param pos A mezo, amin a mereg van.
	 */
	public Poison(Field pos) {
		setPosition(pos);
	}
	
	/**
	 * A mereg animalasa. Mindenkorben csokkenti a hatralevo idot.
	 */
	public void animate() {
		TTL--;
		if (TTL == 0) {
			position.getGlade().removeActiveObject(this);
			position.removeEntity(this);
			this.getView().change();
		}		
	}
	
	/**
	 * Utkozes egy hangyaval. Megmergezi a hangyat.
	 */
	public void collide(Ant ant) {
		ant.poison();
	}

}
