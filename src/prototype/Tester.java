package prototype;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tester {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		// Teszt file beolvasasa
		ArrayList<String> commands = readFile("tests/test_1.txt");

		
		for (String command : commands) {
			//System.out.println(command);
			Commands.parseCommand(command);
		}
		
		Logger.printConsole();
	}
	
	/**
	 * Parancsok beolvasasa egy megadott fajlbol.
	 * @param file
	 * @return A parancsok listaja
	 * @throws IOException
	 */
	public static ArrayList<String> readFile(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
	    try {
	    	ArrayList<String> commands = new ArrayList<String>();
	        String line = br.readLine();

	        while (line != null) {
	        	// Az ures sorokat nem tegye bele
	        	if (!line.equals("")) {
	        		commands.add(line);
	        	}
	            line = br.readLine();
	        }
	        
	        return commands;
	    } finally {
	        br.close();
	    }
	}
}
