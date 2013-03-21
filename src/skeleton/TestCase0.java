package skeleton;

import modell.Ant;
import modell.AntHill;
import modell.AntLion;
import modell.Anteater;

public class TestCase0 extends TestCase {
	
	public void test() {
		
		Ant ant = new Ant();
		Anteater anteater = new Anteater();
		AntHill anthill = new AntHill();
		AntLion antLion = new AntLion();
		antLion.collide(ant);
	}
	

}
