package modell;

public class AntLion extends Entity {
	
	/**
	 * A hangyaleso default konstruktora
	 */
	public AntLion() {		
	}
	
	/**
	 *  Utkozes egy hangyaval
	 */
	public void collide(Ant ant) {		
		ant.kill();		
	}

}
