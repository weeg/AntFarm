package prototype;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Prototype {
	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) {
		
		boolean printToConsole = false;
		
		while (true) {
			int r = Logger.choose("Please choose what you want to do", "Run one test case", 
					"Run all test cases in folder", "Set running settings", "Exit");
			File folder = new File("tests");
			ArrayList<String> fileNames = new ArrayList<String>();
			switch (r) {
			case 1:		
				// Egy teszt futtatasa a mappabol
				for (File file : folder.listFiles(new FilenameFilter() {
					// Csak a bemeneti test fajlokat mutatjuk
					public boolean accept(File dir, String name) {
						return !name.contains("out");
					}
				})) {
					if (file.isFile() && !file.isHidden()) {
						fileNames.add(file.getName());
					}
				}
				String[] s = new String[fileNames.size()];
				
				int f = Logger.choose("Please choose file", fileNames.toArray(s));
				String fileName = "tests/" + fileNames.get(f - 1);
				
				try {
					// Teszt futtatasa
					runTest(fileName, printToConsole);
					// Es ez eredmeny ellenorzese
					Checker.check(fileName);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:			
				// Minden teszt futtatasa a mappaban
				for (File file : folder.listFiles(new FilenameFilter() {
					// Csak a bemeneti test fajlokat mutatjuk
					public boolean accept(File dir, String name) {
						return !name.contains("out");
					}
				})) {
					if (file.isFile() && !file.isHidden()) {
						fileNames.add("tests/" + file.getName());
					}
				}
				for (String fileName1 : fileNames) {
					try {
						// Teszt futtatasa
						runTest(fileName1, printToConsole);
						// Es ez eredmeny ellenorzese
						Checker.check(fileName1);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				}
				break;
			case 3:
				// Annak beallitasa, hogy a teszt eredmenyet kiirjuk-e a konzolra
				int c = Logger.choose("Do you want to write the test results on the console?", "Yes", "No");
				if (c == 1) {
					printToConsole = true;
				} else {
					printToConsole = false;
				}
				break;
			case 4:
				return;
			default:
				break;
			}
		}
	}		
	
	/**
	 * Teszteset futtatasa
	 * @param fileName teszteset fajlneve
	 * @param printToConsole kiirjuk-e az eredmenyt a konzolra
	 * @throws Throwable
	 */
	private static void runTest(String fileName, boolean printToConsole) throws Throwable {
		// Teszt file beolvasasa		
		ArrayList<String> commands = Logger.readFile(fileName);
		
		for (String command : commands) {
			// Az ures sorokat ne dolgozza fel.
			if (!command.equals("")) {
				int line = commands.indexOf(command) + 1;
				int r = Commands.parseCommand(command, line);
				if (r != 0) {
					System.out.println("An error occured during running the following test: "
							+ fileName);
				}
			}
		}
		
		if (printToConsole) {
			Logger.printConsole();
		}
		Logger.printFile(fileName);
		
		Logger.clear();
		
		System.out.println("The following test ran successfully: " + fileName);				
	}
}
