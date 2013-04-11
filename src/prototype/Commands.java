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
import modell.Direction;
import modell.Entity;
import modell.Field;
import modell.Food;
import modell.FoodOdor;
import modell.Glade;
import modell.Log;
import modell.Odor;
import modell.Spray;
import modell.Stone;
import modell.Water;

public class Commands {
	
	/**
	 * A peldanyokat (referenciait es neveit) tarolo tombok. Kulcs es ertek alapjan is lehessen bennuk keresni.
	 */
	private static Map<String, Object> objectsByKey   = new LinkedHashMap<String, Object>();
	private static Map<Object, String> objectsByValue = new LinkedHashMap<Object, String>();
	private static String currentCommand = "";
	private static int currentCommandLine = 0;
	
	public static void parseCommand(String command, int line) {
		String[] params = command.split(" ");
		
		// Hibak kiiratasahoz eltarolja a aktualis parancsot es a sorat.
		currentCommand     = command;
		currentCommandLine = line;
		
		/*
		 * create parancs meghivasa
		 */
		if (params[0].equals("create")) {
			
			if (params.length == 4) {
				Commands.create(params[1], params[2], params[3]);
			} else if (params.length == 3) {
				Commands.create(params[1], params[2]);
			} else {
				Logger.add("Error at line "+currentCommandLine+"! Mismatch parameter number: "+currentCommand);
			}
		
		/*
		 * add parancs meghivasa
		 */
		} else if (params[0].equals("add")) {

			if (params.length == 3) {
				Commands.add(params[1], params[2]);
			} else {
				Logger.add("Error at line "+currentCommandLine+"! Mismatch parameter number: "+currentCommand);
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
				Logger.add("Error at line "+currentCommandLine+"! Mismatch parameter number: "+currentCommand);
			}
			
		/*
		 * set parancs meghivasa
		 */
		} else if (params[0].equals("set")) {

			if (params.length == 4) {
				Commands.set(params[1], params[2], params[3]);
			} else {
				Logger.add("Error at line "+currentCommandLine+"! Mismatch parameter number: "+currentCommand);
			}
			
		/*
		 * animate parancs meghivasa
		 */
		} else if (params[0].equals("animate")) {

			if (params.length == 2) {
				Commands.animate(params[1]);
			} else {
				Logger.add("Error at line "+currentCommandLine+"! Mismatch parameter number: "+currentCommand);
			}
			
		/*
		 * tick parancs meghivasa
		 */
		} else if (params[0].equals("tick")) {

			if (params.length == 1) {
				Commands.tick();
			} else {
				Logger.add("Error at line "+currentCommandLine+"! Mismatch parameter number: "+currentCommand);
			}
			
		/*
		 * addOdor parancs meghivasa
		 */
		} else if (params[0].equals("addOdor")) {

			if (params.length == 3) {
				Commands.addOdor(params[1], params[2]);
			} else {
				Logger.add("Error at line "+currentCommandLine+"! Mismatch parameter number: "+currentCommand);
			}
			
		/*
		 * spray parancs meghivasa
		 */
		} else if (params[0].equals("spray")) {

			if (params.length == 3) {
				Commands.spray(params[1], params[2]);
			} else {
				Logger.add("Error at line "+currentCommandLine+"! Mismatch parameter number: "+currentCommand);
			}
			
		} else {
			Logger.add("Error at line "+currentCommandLine+"! Unknown command: "+currentCommand);
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
			Glade gla = getGlade();
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
			Logger.add("Error at line "+currentCommandLine+"! Unknown object "+object+": "+currentCommand);
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
			Field fie  = (Field) getObject(field);
			Entity obj = (Entity) getObject(object);
			fie.addEntity(obj);
			
			Logger.add(object+" has been added to "+field+".");
		
		// Csak a mezohoz lehet elemet hozzarendelni
		} else {
			Logger.add("Error at line "+currentCommandLine+"! Wrong parameters: "+currentCommand);
		}
	}
	
	/**
	 * Egy elem ertekenek megvaltoztatasa.
	 * set parancs megvalositasa.
	 * @param object
	 * @param parameter
	 * @param value
	 */
	public static void set(String object, String parameter, String value) {
		
		String type = getObjectType(object);
		Object obj  = null;
		
		if (type.equals("Anteater")) {
			obj = (Anteater) getObject(object);
			
			if (parameter.equals("direction")) {
				((Anteater) obj).setDirection(Direction.valueOf(value));
			}
		}
		
		if (obj != null) {
			Logger.add(object+"’s "+parameter+" parameter set to "+value+".");
		} else {
			Logger.add("Error at line "+currentCommandLine+"! "+object+" doesn't have "+parameter+" parameter: "+currentCommand);
		}
	}
	
	/**
	 * Active objektumok animalasa.
	 * animate parancs megvalositasa.
	 * @param object
	 */
	public static void animate(String object) {
		
		Object obj = getObject(object);
		
		if (hasInterface(obj, "Active")) {
			Active active = (Active) obj;
			// Meg ne hivja meg.
			//active.animate();
			
			String key = getKey(obj);
			Logger.add(key+" has been animated.");
		}
	}
	
	/**
	 * Ido leptetese.
	 * tick parancs megvalositasa.
	 */
	private static void tick() {
		
		Glade glade = getGlade();
		glade.tick();
		
		Logger.add("Tick.");
	}
	
	/**
	 * Szag hozzaadasa egy mezohoz.
	 * addOdor parancs megvalositasa.
	 * @param object
	 * @param field
	 */
	private static void addOdor(String object, String field) {
		Object obj   = getObject(object);
		Object fie   = getObject(field);
		
		// A mezo nem talalhato
		if (fie == null) {
			Logger.add("Error at line "+currentCommandLine+"! Field with name "+field+" cannot be found: "+currentCommand);
		
		// A megadaott neve, nem egy Field
		} else if (!getObjectType(fie).equals("Field")) {
			Logger.add("Error at line "+currentCommandLine+"! The type of "+field+" is not Field: "+currentCommand);
		
		// Field
		} else {
			
			// Nem szagot probal hozzaadni
			if (!isExtending(obj, "Odor")) {
				Logger.add("Error at line "+currentCommandLine+"! The type of "+object+" is not an Odor: "+currentCommand);
			
			// Szag hozzaadasa a mezohoz.
			} else {
				Odor odor = (Odor) obj;
				((Field) fie).addOdor(odor);
				Logger.add(object+" has been added to "+field+".");
			}
		}
	}
	
	/**
	 * A megadott spray hasznalata
	 * spray parancs megvalositasa.
	 * @param type
	 * @param field
	 */
	private static void spray(String type, String field) {
		Object obj = getObject(type);
		Object fie = getObject(field);
		
		// A mezo nem talalhato
		if (fie == null) {
			Logger.add("Error at line "+currentCommandLine+"! Field with name "+field+" cannot be found: "+currentCommand);
		
		// A megadaott neve, nem egy Field
		} else if (!getObjectType(fie).equals("Field")) {
			Logger.add("Error at line "+currentCommandLine+"! The type of "+field+" is not Field: "+currentCommand);
		
		// Field
		} else {
			
			// Nem spay-jel probalkozik
			if (!isExtending(obj, "Spray")) {
				Logger.add("Error at line "+currentCommandLine+"! The type of "+type+" is not a Sprayr: "+currentCommand);
			
			// Spray hasznalata.
			} else {
				Spray spray = (Spray) obj;
				spray.use(((Field) fie));
				
				Logger.add(type+" has been used with center "+field+". "+spray.getQuantity()+" blows left.");
			}
		}
	}
	
	/**
	 * Aktualis helyzet kilistazasa
	 * List parancs megvalositasa
	 * @param mode
	 */
	private static void list(String mode) {
		
		// A glade valamint az osszes mezo objektumainak kilistazasa
		if (mode.equals("all")) {

			Glade gla = getGlade();
			
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
			
			Field fie = (Field) getObject(mode);
			
			if (fie != null) {
				ArrayList<Entity> entities = fie.getEntities();
				Logger.add("List "+mode+": "+getEnititiesName(entities));
			
			// Nem letezo mezore valo hivatkozas
			} else {
				Logger.add("Error at line "+currentCommandLine+"! Unknown "+mode+" filed: "+currentCommand);
			}
		}
	}
	
	/**
	 * Kikeresi, hogy az adott objektumnak van-e megadott tipusu interface-e.
	 * @param obj
	 * @param type
	 */
	private static boolean hasInterface(Object obj, String type) {
		
		Class[] interfaces = obj.getClass().getInterfaces();
		
		for (int i = 0; i < interfaces.length; i++) {
			if (interfaces[i].getSimpleName().equals(type)) {
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean isExtending(Object obj, String type) {
		
		if (obj.getClass().getSuperclass().getSimpleName().equals(type)) {
			return true;
		}
				
		return false;
	}
	
	/**
	 * Kikeresi a palyat az objektum listabol.
	 * @return
	 */
	private static Glade getGlade() {
		
		for (Entry<String, Object> obj : objectsByKey.entrySet()) {
			
			String key = obj.getKey();
			// Glade kikeresese
			if (getObjectType(key).equals("Glade")) {
				return (Glade) obj.getValue();
			}
		}
		
		return null;
	}
	
	/**
	 * Az entitasoknak kilistazza a nevet.
	 * @param entities
	 */
	private static String getEnititiesName(ArrayList<Entity> entities) {
		
		StringBuilder sb = new StringBuilder();
		
		// Entitasokhoz kikeresi a nevuket
		for (Entity entity : entities) {
			String key = getKey(entity);
			
			// Vesszok betuzdelese
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(key);
		}
	
		return sb.toString();
	}
	
	private static Object getObject(String key) {
		return objectsByKey.get(key);
	}
	
	private static String getKey(Object object) {
		return objectsByValue.get(object);
	}
	
	/**
	 * Aktiv objektumok nevenek kilistazasa.
	 * @param entities
	 */
	private static String getActiveObjectsName(ArrayList<Active> entities) {
		
		StringBuilder sb = new StringBuilder();
		
		// Entitasokhoz kikeresi a nevuket
		for (Active entity : entities) {
			String key = getKey(entity);
			
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
		return getObject(key).getClass().getSimpleName();
	}
	
	private static String getObjectType(Object obj) {
		return obj.getClass().getSimpleName();
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
