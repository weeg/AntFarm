package modell;

public abstract class Barricade extends Entity {
	
	/**
	 * Utkozes egy hangyaval
	 * @param ant A hangya, amivel utkozik a barrikad
	 */
	public void collide(Ant ant) {		
		ant.block();		
	}
	
	/**
	 * Utkozes egy kavicsot. Blokkolja a kavicsot.
	 * @param s a ko, amivel utkozott.
	 */
	public void collide(Stone s) {
		s.block();
	}
}
