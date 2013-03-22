package skeleton;

import modell.AntKillerSpray;
import modell.AntOdor;
import modell.AntOdorNeutralizerSpray;
import modell.Field;
import modell.Glade;
import modell.Spray;

public class TestCase4 extends TestCase {
	
	public void test() {

		int r = Logger.choose("Milyen spray-vel kivan fujni?", "Hangyaolo spray-vel", "Hangyaszagsemlegesito spray-vel");
		Logger.off();
		Glade glade = new Glade();
		Field f = new Field();
		Spray spray = null;
		switch (r) {
		case 1:
			spray = new AntKillerSpray(glade);			
			break;
		case 2:
			spray = new AntOdorNeutralizerSpray(glade);
			f.addOdor(new AntOdor(glade, f));
			break;		
		}		
		
		Logger.on();
		
		spray.use(f);
	}

}
