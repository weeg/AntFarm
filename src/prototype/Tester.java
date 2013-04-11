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
		
		Logger.printConsole();
		Logger.printFile();
	}
	
	
	
	public static Object getVariable(Object object, String fieldName) throws Throwable {
        java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);       
        field.setAccessible(true);
       
        return field.get(object);
    }
	
	public static void setVariable(Object object, String fieldName, Object value) throws Throwable {
        java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);       
        field.setAccessible(true);
        field.set(object, value);
    }
}
