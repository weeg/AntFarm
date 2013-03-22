package skeleton;

import modell.Ant;
import modell.Direction;
import modell.Field;
import modell.FoodOdor;
import modell.Glade;

public class TestCase2 extends TestCase{

	@Override
	public void test() {
		Logger.off();
		Field pos = new Field();
		Field f1 = new Field();
		FoodOdor fo1 = new FoodOdor();
		Field f2 = new Field();
		FoodOdor fo2 = new FoodOdor();
		Field f3 = new Field();
		FoodOdor fo3 = new FoodOdor();
		f1.addOdor(fo1);
		f2.addOdor(fo2);
		f3.addOdor(fo3);
		pos.setNeighbour(Direction.N, f1);
		pos.setNeighbour(Direction.NE, f2);
		pos.setNeighbour(Direction.SE, f3);
		Glade gl = new Glade();
		Ant ant = new Ant(gl);
		ant.setPosition(pos);
		Logger.on();
		ant.animate();
	}

}
