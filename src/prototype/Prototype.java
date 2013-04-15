package prototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class Prototype {
	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		
		while (true) {
			int r = Logger.choose("Please choose what you want to do", "Run one test case", 
					"Run all test cases in folder", "Exit");
			File folder = new File("tests");
			ArrayList<String> fileNames = new ArrayList<String>();
			switch (r) {
			case 1:						
				for (File file : folder.listFiles(new FilenameFilter() {
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
				runTest(fileName);
				break;
			case 2:						
				for (File file : folder.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return !name.contains("out");
					}
				})) {
					if (file.isFile() && !file.isHidden()) {
						fileNames.add("tests/" + file.getName());
					}
				}
				for (String fileName1 : fileNames) {
					runTest(fileName1);
				}
				break;
			case 3:
				return;
			default:
				break;
			}
		}

	}
	
	private static void runTest(String fileName) throws Throwable {
		// Teszt file beolvasasa
		ArrayList<String> commands = Logger.readFile(fileName);
		
		for (String command : commands) {
			// Az ures sorokat ne dolgozza fel.
			if (!command.equals("")) {
				int line = commands.indexOf(command) + 1;
				int r = Commands.parseCommand(command, line);
				if (r != 0) {
					System.out.println("An error occured during running the test.");
				}
			}
		}
		
		//Logger.printConsole();
		Logger.printFile();
		
		System.out.println("\nThe following test ran successfully: " + 
				fileName + ".\n");
	}
}
