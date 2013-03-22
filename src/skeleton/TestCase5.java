package skeleton;

import modell.Glade;

public class TestCase5 extends TestCase {
	
	public void test() {
		Logger.off();
		Glade gl = new Glade();
		Logger.on();
		gl.tick();
	}

}
