package skeleton;

public class Skeleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			TestCase testCase = null;
			int r = Logger.choose("Melyik tesztesetet futtatja?", "Jatek kezdese", "Hangya animalasa",
					  "Hangyaszsun animalasa", "Fujas spray-vel", "Egy kor animalasa");
			switch (r) {
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
			
			r = Logger.choose("A teszteset lefutott. Kivan ujat futtatni?", "Igen", "Nem, kilepek");
			Logger.reset();
			if (r == 2) {
				break;
			}
		}			
	}

}
