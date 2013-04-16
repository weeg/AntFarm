package modell;

public class AntHill extends Entity implements Active {
	/**
	 * A hangyaboly default konstruktora
	 */
	public AntHill() {		
	}
	
	/**
	 *  Utkozes egy hangyaval
	 *  @param ant A hangya, amivel a hangyaboly utkozik
	 */
	public void collide(Ant ant) {

		if (ant.hasFood()) {
			ant.kill();
		}		
	}
	
	/**
	 * A hangyaboly animalasa
	 */
	public void animate() {		
				
		Ant ant = new Ant(position, Direction.SE);
		position.addEntity(ant);
		position.getGlade().addActiveObject(ant);
	}
}
