package modell;

import skeleton.Logger;

/**
 * Az entitás objektum. 
 */
public abstract class Entity {
	
	/**
	 * A mező, amin az entitás van.
	 */
	protected Field position;
	
	/**
	 * Visszadja a poziciót.
	 * @return a pozició.
	 */
	public Field getPosition() {
		return position;
	}
	
	/**
	 * Beállítja az új poziciót.
	 * @param position Az új pozició.
	 */
	public void setPosition(Field position) {
		this.position = position;
	}
	
	/**
	 * Ütközés egy hangyával. A leszármazottak különbőző módon implemetálhatják, 
	 * attól függően mi történjen az ütközés során.
	 * @param ant A hangya, amivel ütközik.
	 */
	public void collide(Ant ant) {		
		// A leszarmazottak fogjak implementalni szukseg eseten
	}
	
	/**
	 * Ütközés egy hangyászsünnel. A leszármazottak különbőző módon implemetálhatják, 
	 * attól függően mi történjen az ütközés során.
	 * @param anteater A hangyássün, amivel ütközik.
	 */
	public void collide(Anteater anteater) {
		// A leszarmazottak fogjak implementalni szukseg eseten
	}
}
