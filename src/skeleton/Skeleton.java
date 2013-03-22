package skeleton;

public class Skeleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
<<<<<<< HEAD
		TestCase testCase = null;
		int r = Logger.choose("Melyik tesztesetet futtatja?", "Dummy", "Jatek kezdese", "Hangya animalasa");
		switch(r) {
=======
		
		while (true) {
			
			int r = Logger.choose("Melyik tesztesetet futtatja?", "Jatek kezdese", "Hangya animalasa",
					  "Hangyaszsun animalasa", "Fujas spray-vel");
			
			TestCase testCase = null;
			switch (r) {
>>>>>>> neutralize nem jó
			case 1:
				testCase = new TestCase1();				
				break;
			case 2:				
				testCase = new TestCase2();				
				break;
			case 3:
				testCase = new TestCase3();
				break;
			case 4:
				testCase = new TestCase4();
				break;
			case 5:
				testCase = new TestCase5();
				break;				
			}
			testCase.test();
			
			r = Logger.choose("A tesztesett lefutott. Kivan ujat futtatni?", "Igen", "Nem, kilepek");
			if (r == 2) {
				break;
			}
		}			
	}

}
