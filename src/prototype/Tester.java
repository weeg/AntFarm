package prototype;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tester {
	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {

		// Teszt file beolvasasa
		String fileName = "tests/test_1.txt";
		
		ArrayList<String> commands = Logger.readFile(fileName);
		
		for (String command : commands) {
			// Az ures sorokat ne dolgozza fel.
			if (!command.equals("")) {
				int line = commands.indexOf(command) + 1;
				Commands.parseCommand(command, line);
			}
		}
		
		//Logger.printConsole();
		Logger.printFile();
	}
}
