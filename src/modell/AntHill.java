package modell;

import java.util.Random;

import view.AntView;

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
	 * Utkozes egy kavicsot. Blokkolja a kavicsot.
	 * @param s a ko, amivel utkozott.
	 */
	public void collide(Stone s) {
		s.block();
	}
	
	private double probability = 0.3;
	
	/**
	 * A hangyaboly animalasa
	 */
	public void animate() {		
		
		if (probability < 0.5) {
			probability += 0.01;
		} else if (probability < 0.8) {
			probability += 0.005;
		} else if (probability < 0.9) {
			probability += 0.001;
		}
		
		Random random = new Random();
		if (random.nextDouble() < probability) {
			Ant ant = new Ant(position, Direction.random());
			ant.setView(new AntView());
			position.addEntity(ant);
			position.getGlade().addActiveObject(ant);
		}
	}
}
