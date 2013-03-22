package skeleton;

public class Skeleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
<<<<<<< HEAD
		TestCase testCase = null;
		int r = Logger.choose("Melyik tesztesetet futtatja?", "Dummy", "Jaték kezdése", "Hangya animálása");
		switch(r) {
			case 1:
				testCase = new TestCase0();
				testCase.test();
				break;
			case 2:
				testCase = new TestCase1();
				testCase.test();
				break;
			case 3:
				testCase = new TestCase2();
				testCase.test();
		}
=======
		
		TestCase testCase = new TestCase2();
		testCase.test();

>>>>>>> b125f0457bea5883431a762fdc57648e30e066ac
	}

}
