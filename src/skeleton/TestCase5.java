package skeleton;

import modell.Field;
import modell.Food;
import modell.FoodOdor;
import modell.Glade;

public class TestCase5 extends TestCase {

	@Override
	public void test() {
		Logger.off();
		Glade gl = new Glade();
		Logger.on();
		gl.tick();
	}

}
