package skeleton;

import modell.Ant;
import modell.Direction;
import modell.Field;
import modell.FoodOdor;
import modell.Glade;

public class TestCase2 extends TestCase {

	@Override
	public void test() {
		
		System.out.println("Teszt kornyezet inicializalasa:\n");
		Logger.off();
		Glade glade = new Glade();
		Field center = new Field();
		Field[] neighbours = new Field[3];
		for (int i = 0; i < 3; i++) {
			neighbours[i] = new Field();
			neighbours[i].addOdor(new FoodOdor());
		}
		center.setNeighbour(Direction.N, neighbours[0]);
		center.setNeighbour(Direction.NE, neighbours[1]);
		center.setNeighbour(Direction.SE, neighbours[2]);
				
		Ant ant = new Ant(glade, center, Direction.N);		
		
		System.out.println("\nA teszt inditasa: ");
		Logger.on();
		ant.animate();
	}

}
