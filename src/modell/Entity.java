package modell;

import view.View;

/**
 * Az entitas objektum. 
 */
public abstract class Entity implements Drawable {
	
	/** A mezo, amin az entitas van */
	protected Field position;
	
	/** Renderelo nezet hozzaasa */
	protected View view;
	
	/**
	 * Visszadja az entitas poziciojat.
	 * @return a pozicio.
	 */
	public Field getPosition() {
		return position;
	}
	
	/**
	 * Beallitja az entitas uj poziciojat.
	 * @param position Az �j pozici�.
	 */
	public void setPosition(Field position) {
		this.position = position;
	}
	
	/**
	 * Utkozes egy hangyaval. A leszarmazottak kulonbozo modon implemetalhatjak, 
	 * attol fuggoen mi tortenjen az utkozes soran.
	 * @param ant A hangya, amivel utkozik.
	 */
	public void collide(Ant ant) {		
		// A leszarmazottak fogjak implementalni szukseg eseten
	}
	
	/**
	 * Utkozes egy hangyaszsunnel. A leszarmazottak kulonbozo modon implemetalhatjak, 
	 * attol fuggoen mi tortenjen az utkozes soran.
	 * @param anteater A hangyassun, amivel utkozik.
	 */
	public void collide(Anteater anteater) {
		// A leszarmazottak fogjak implementalni szukseg eseten
	}
	/**
	 * Utkozes egy kaviccsal. A leszarmazottak kulonbozo modon implemetalhatjak, 
	 * attol fuggoen mi tortenjen az utkozes soran.
	 * @param stone A kavics, amivel utkozik.
	 */
	public void collide(Stone stone) {
		// A leszarmazottak fogjak implementalni szukseg eseten
	}
	
	/**
	 * Beallitja a renderelo nezetet.
	 * @param view A kirajzolashoz hasznalt renderelo nezet.
	 */
	public void setView(View view) {
		view.setModel(this);
		this.view = view;
	}
	
	/**
	 * Visszaadja a renderelo nezetet.
	 * @return a renderelo nezet. 
	 */
	public View getView() {
		return view;
	}
}
