package skeleton;

public class Skeleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
	}

}
