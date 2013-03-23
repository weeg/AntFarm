package skeleton;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Logger {
	
	/**
	 * A hivott fuggvenyek neveit tarolo tomb
	 */
	private static Stack<String> stack = new Stack<String>();
	
	/**
	 * Az egyes osztalyok objektumainak szama
	 */
	private static Map<String, Integer> objectIDs = new HashMap<String, Integer>();
	
	/**
	 * A peldanyokat (referenciait es neveit) tarolo tomb
	 */
	private static Map<Object, String> objects = new HashMap<Object, String>();
	
	/**
	 * Resets the logger
	 */
	public static void reset() {
		stack.removeAllElements();
		objectIDs.clear();
		objects.clear();
	}
	
	/**
	 * Csendes uzemmodhoz.
	 */
	private static boolean silent = false;
	
	/**
	 * Visszaadja egy objektum nevet a referenciaja alapjan
	 * @param o Az objektum referenciaja
	 * @return Az objektum neve
	 */
	public static String getObjectName(Object o) {
		return objects.get(o);
	}
	
	/**
	 * Hozzaadja az aktualis behuzast a sorhoz
	 */
	private static void tab() {
		
		for (int i = 0; i < stack.size(); i++) {
			System.out.print("   ");
		}
	}
	
	/**
	 * A letrejovo objektumok regisztralasa a naploba 
	 * @param s A letrejovo objektum neve
	 * @param o A letrejovo objektum referenciaja
	 */
	public static void attach(String s, Object o) {
		
		// Objektum azonosito generalasa
		int currentValue = 0;
		if (objectIDs.containsKey(s)) {
			currentValue = objectIDs.get(s);			
		}
		objectIDs.put(s, currentValue + 1);
		
		// Az objektum tarolasa
		objects.put(o, s + objectIDs.get(s));
		
		// Kiiras
		if (silent == false) {
			tab();
			System.out.println("-:> " + s + "." + s + "(): " + objects.get(o) + " created");
		}
	}	
		
	/**
	 * Fuggvenybe torteno belepes kiirasa es regisztralasa
	 * @param sender Az az objektum, amely belepett egy fuggvenyebe
	 * @param function A fuggveny neve
	 * @param parameters A fuggveny parameterei
	 */
	public static void enter(Object sender, String function, String... parameters) {
		if (silent == false) {
			
			// Kiiras
			tab();
			System.out.print("--> " + objects.get(sender) + "." + function + "(");
			for (int i = 0; i < parameters.length; i++) {
				System.out.print(parameters[i]);
				if (i < parameters.length - 1) {
					System.out.print(", ");
				}			
			}
			System.out.println(")");
		
			// A fuggveny mentese a stackre
			stack.add(function);
		}
	}
	
	/**
	 * Fuggvenybol torteno kilepes kiirasa
	 * @param sender Az az objektum, amely kilepett egy fuggvenybol
	 */
	public static void exit(Object sender) {
		if (silent == false) {	
			
			// Fuggveny nevenek lekerese a stackrol
			String function = stack.pop();
			// Kiiras
			tab();
			System.out.println("<-- " + objects.get(sender) + "." + function + "()" );
		}
	}
	
	/**
	 * A felhasznalo valaszthat a rendelkezesre allo alternativak kozul
	 * @param question A kerdes, amit felteszunk
	 * @param answeres A lehetseges valaszlehetosegek
	 * @return A kivalasztott valaszlehetoseg
	 */
	public static int choose(String question, String... answers) {
		
		System.out.println();
		System.out.println(question);
		for (int i = 0; i < answers.length; i++) {
			System.out.println((i + 1) + ": " + answers[i]);
		}		
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		while (answer == 0) {
		    try {
		    	System.out.print("Valasz: ");
				 String s = bufferedReader.readLine();
				 answer = Integer.parseInt(s);
				 if (answer <= 0 || answer > answers.length) {
					 answer = 0;
					 throw new Exception();					 
				 }
			} catch (Exception e) {
				System.out.println("Az adott valasz nem megfelelo. A vart valasz egy 1 es " + answers.length + " kozotti szam.");
			}
		}
		System.out.println();
		return answer;
	    
	}
	
	/**
	 * Bekapcsolja a loggolast.
	 */
	public static void on() {
		silent = false;
	}
	/**
	 * Kikapcsolja a loggolast. (Csendesuzemmod)
	 */
	public static void off() {
		silent = true;
	}
}