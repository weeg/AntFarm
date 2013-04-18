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
					if (file.isFile() && !file.isHidden() && !file.getName().equals(".DS_Store")) {
						fileNames.add(file.getName());
					}
				}
				String[] s = new String[fileNames.size()];
				
				int f = Logger.choose("Please choose file", fileNames.toArray(s));
				String fileName = "tests/" + fileNames.get(f - 1);
				
				try {
					// Teszt futtatasa
					if (runTest(fileName, printToConsole, f)) {
						// Siker eseten az eredmeny ellenorzese
						Checker.check(fileName);
					}
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
					if (file.isFile() && !file.isHidden() && !file.getName().equals(".DS_Store")) {
						fileNames.add("tests/" + file.getName());
					}
				}
				for (String fileName1 : fileNames) {
					try {
						int num = fileNames.indexOf(fileName1) + 1;
						// Tesztek futtatasa
						if (runTest(fileName1, printToConsole, num)) {
							// Siker eseten az eredmeny ellenorzese
							Checker.check(fileName1);
						}
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
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
	private static boolean runTest(String fileName, boolean printToConsole, int num) throws Throwable {
		// Teszt file beolvasasa		
		ArrayList<String> commands = Logger.readFile(fileName);
		
		boolean success = true;
		
		for (String command : commands) {
			// Az ures sorokat ne dolgozza fel.
			if (!command.equals("")) {
				int line = commands.indexOf(command) + 1;
				try {
					Commands.parseCommand(command, line);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					// Hiba eseten aljon le a futassal
					success = false;
					break;
				}			
			}
		}
		
		if (printToConsole) {
			Logger.printConsole();
		}
		Logger.printFile(fileName);
		
		if (success) {
			System.out.println("\n"+getTestName(fileName)+": " + Logger.getTitle() + ":");
		}			
				
		// Adatok torlese a teszt lefutasa utan
		Logger.clear();
		Commands.clear();
		
		return success;
	}
	
	/**
	 * Visszaadja a teszt file nevet.
	 * @param fileName teszteset eleresi utja
	 * @return teszteset file neve
	 */
	private static String getTestName(String fileName) {
		
		String[] splits = fileName.split("/");
		
		// Csak a teszt nevet adja vissza
		if (splits.length > 1) {
			return splits[splits.length - 1];
			
	    // Nem szabvanyos file nev eseten az egesz fajl nevet adja vissza.
		} else {
			return fileName;
		}
	}
}
