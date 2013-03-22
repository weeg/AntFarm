package skeleton;

import modell.Ant;
import modell.AntHill;
import modell.AntLion;
import modell.Anteater;
import modell.Direction;
import modell.Field;
import modell.Glade;

public class TestCase0 extends TestCase {
	
	public void test() {
		Glade gl = new Glade();
		Ant ant = new Ant();
		Anteater anteater = new Anteater();
		AntHill anthill = new AntHill(gl);
		AntLion antLion = new AntLion();
		antLion.collide(ant);
	}
	

}
