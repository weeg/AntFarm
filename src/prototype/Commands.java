package prototype;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import modell.Active;
import modell.Ant;
import modell.AntHill;
import modell.AntKillerSpray;
import modell.AntLion;
import modell.AntOdor;
import modell.AntOdorNeutralizerSpray;
import modell.Anteater;
import modell.DeadEnd;
import modell.Entity;
import modell.Field;
import modell.Food;
import modell.FoodOdor;
import modell.Glade;
import modell.Log;
import modell.Stone;
import modell.Water;

public class Commands {
	
	/**
	 * A peldanyokat (referenciait es neveit) tarolo tombok. Kulcs es ertek alapjan is lehessen bennuk keresni.
	 */
	private static Map<String, Object> objectsByKey   = new LinkedHashMap<String, Object>();
	private static Map<Object, String> objectsByValue = new LinkedHashMap<Object, String>();
	
	public static void parseCommand(String command) {
		String[] params = command.split(" ");

		/*
		 * create parancs meghivasa
		 */
		if (params[0].equals("create")) {
			
			if (params.length == 4) {
				Commands.create(params[1], params[2], params[3]);
			} else if (params.length == 3) {
				Commands.create(params[1], params[2]);
			} else {
				Logger.add("Mismatch parameter number: "+command);
			}
		
		/*
		 * add parancs meghivasa
		 */
		} else if (params[0].equals("add")) {

			if (params.length == 3) {
				Commands.add(params[1], params[2]);
			} else {
				Logger.add("Mismatch parameter number: "+command);
			}
		
		/*
		 * list parancs meghivasa
		 */
		} else if (params[0].equals("list")) {

			// Minden kilistazasa
			if (params.length == 1) {
				Commands.list("all");
			
			// Egy mezo tartalmanak kilistazasa
			} else if (params.length == 2) {
				Commands.list(params[1]);
			
			// Hibas parameterezes
			} else {
				Logger.add("Mismatch parameter number: "+command);
			}
			
		/*
		 * set parancs meghivasa
		 */
		} else if (params[0].equals("set")) {

			if (params.length == 4) {
				Commands.set(params[1], params[2], params[3]);
			} else {
				Logger.add("Mismatch parameter number: "+command);
			}
			
		} else {
			Logger.add("Unknown command: "+command);
		}
	}
	
	/**
	 * Create parancs megvalositasa.
	 * Elemek letrehozasa.
	 * @param object
	 * @param name
	 */
	public static void create(String object, String name) {
		create(object, name, "");
	}
	
	/**
	 * Create parancs megvalositasa.
	 * Elemek letrehozasa.
	 * @param object
	 * @param name
	 * @param glade
	 */
	public static void create(String object, String name, String glade) {
		
		Object obj = null;
		
		// Hangyak letrehozasa
		if (object.equals("Ant")) {
			obj = new Ant();
			
		// Hangyaszsun letrehozasa
		} else if (object.equals("Anteater")) {	
			obj = new Anteater();	
		
		// Hangyafiu letrehozasa
		} else if (object.equals("AntHill")) {	
			obj = new AntHill();	
		
		// Hangyaolo letrehozasa
		} else if (object.equals("AntKillerSpray")) {	
			obj = new AntKillerSpray();	
		
		// Hangyaleso letrehozasa
		} else if (object.equals("AntLion")) {	
			obj = new AntLion();	
			
		// Hangyaszag letrehozasa
		} else if (object.equals("AntOdor")) {	
			obj = new AntOdor();	

		// Hangyaszagsemlegesito spray letrehozasa
		} else if (object.equals("AntOdorNeutralizerSpray")) {	
			obj = new AntOdorNeutralizerSpray();	
		
		// Palya vege letrehozasa
		} else if (object.equals("DeadEnd")) {	
			obj = new DeadEnd();	

		// Palya vege letrehozasa
		} else if (object.equals("DeadEnd")) {	
			obj = new DeadEnd();	
		
		// Mezok letrehozasa
		} else if (object.equals("Field")) {
			// TODO: lehet inkabb ki kene keresni, ahelyett, hogy feltetlezzuk a nevet
			Glade gla = (Glade) objectsByKey.get("glade");
			obj = new Field(gla);
		
		// Kaja letrehozasa
		} else if (object.equals("Food")) {	
			obj = new Food();
		
		// Kajaszag letrehozasa
		} else if (object.equals("FoodOdor")) {	
			obj = new FoodOdor();
		
		// Tisztas letrehozasa
		} else if (object.equals("Glade")) {	
			obj = new Glade();
		
		// Faronk letrehozasa
		} else if (object.equals("Log")) {	
			obj = new Log();
		
		// Mereg letrehozasa
		} else if (object.equals("Poison")) {	
			obj = new Object();
		
		// Kavics letrehozasa
		} else if (object.equals("Stone")) {	
			obj = new Stone();
		
		// Tocsa letrehozasa
		} else if (object.equals("Water")) {	
			obj = new Water();
		
		}
		
		// Letrehozz objektumok eltarolasa
		if (obj != null) {
			addObject(obj, object, name);
		} else {
			Logger.add("Unknown object: "+object);
		}

	}
	
	/**
	 * Add parancs megvalositasa.
	 * Elemek hozzarendelese egy mezohoz.
	 * @param object
	 * @param field
	 */
	public static void add(String object, String field) {

		// Masodik parameter tipusanak lekerdezese
		if (getObjectType(field).equals("Field")) {
			
			// Entitasok lekerdezese es castolasa 
			Field fie  = (Field) objectsByKey.get(field);
			Entity obj = (Entity) objectsByKey.get(object);
			fie.addEntity(obj);
			
			Logger.add(object+" has been added to "+field+".");
		
		// Csak a mezohoz lehet elemet hozzarendelni
		} else {
			Logger.add("Wrong parameters: add "+object+" "+field);
		}
	}
	
	/**
	 * Egy elem ertekenek megvaltoztatasa.
	 * Set parancs megvalositasa.
	 * @param object
	 * @param parameter
	 * @param value
	 */
	public static void set(String object, String parameter, String value) {
		
	}
	
	/**
	 * Aktualis helyzet kilistazasa
	 * List parancs megvalositasa
	 * @param mode
	 */
	public static void list(String mode) {
		
		// A glade valamint az osszes mezo objektumainak kilistazasa
		if (mode.equals("all")) {
			// TODO: itt is ki ki kene keresni a nevet
			Glade gla = (Glade) objectsByKey.get("glade");
			
			Logger.add("List all:");
			// Aktiv objektumok kilistazasa
			Logger.add("\tList glade: "+ getActiveObjectsName(gla.getActiveObjects()));
			
			for (Entry<String, Object> obj : objectsByKey.entrySet()) {
				
				String key = obj.getKey();
				// Mezok kikeresese
				if (getObjectType(key).equals("Field")) {
					Field fie = (Field) obj.getValue();
					ArrayList<Entity> entities = fie.getEntities();
					
					Logger.add("\tList "+obj.getKey()+": "+getEnititiesName(entities));
				}
			}
			
		// Egy megadott mezo tartalmanak kilistazasa
		} else {
			
			Field fie = (Field) objectsByKey.get(mode);
			
			if (fie != null) {
				ArrayList<Entity> entities = fie.getEntities();
				Logger.add("List "+mode+": "+getEnititiesName(entities));
			
			// Nem letezo mezore valo hivatkozas
			} else {
				Logger.add("Listing error. Unknown filed name: "+mode);
			}
		}
	}
	
	/**
	 * Az entitasoknak kilistazza a nevet.
	 * @param entities
	 */
	private static String getEnititiesName(ArrayList<Entity> entities) {
		
		StringBuilder sb = new StringBuilder();
		
		// Entitasokhoz kikeresi a nevuket
		for (Entity entity : entities) {
			String key = objectsByValue.get(entity);
			
			// Vesszok betuzdelese
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(key);
		}
	
		return sb.toString();
	}
	
	/**
	 * Aktiv objektumok nevenek kilistazasa.
	 * @param entities
	 */
	private static String getActiveObjectsName(ArrayList<Active> entities) {
		
		StringBuilder sb = new StringBuilder();
		
		// Entitasokhoz kikeresi a nevuket
		for (Active entity : entities) {
			String key = objectsByValue.get(entity);
			
			// Vesszok betuzdelese
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(key);
		}
	
		return sb.toString();
	}
	
	/**
	 * Visszaadja az objektumok egyszerusitett nevet.
	 * @param key
	 * @return
	 */
	private static String getObjectType(String key) {
		return objectsByKey.get(key).getClass().getSimpleName();
	}
	
	/**
	 * Objektumok eltarolasa egy listaban, valamin uzenet visszajelzese.
	 * @param obj
	 * @param object
	 * @param name
	 */
	private static void addObject(Object obj, String object, String name) {
		objectsByKey.put(name, obj);
		objectsByValue.put(obj, name);
		Logger.add(object+" has been created with name "+name+".");
	}
}
