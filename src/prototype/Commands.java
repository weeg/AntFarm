package prototype;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

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
import modell.Poison;
import modell.Spray;
import modell.Stone;
import modell.Water;

public class Commands {
	
	/*
	 * A peldanyokat (referenciait es neveit) tarolo tombok. Kulcs es ertek alapjan is lehessen bennuk keresni.
	 * A LinkedHashMap sorrendhelyesen tarolja az elemeket
	 */
	private static Map<String, Object> objectsByKey   = new LinkedHashMap<String, Object>();
	private static Map<Object, String> objectsByValue = new LinkedHashMap<Object, String>();
	public static String currentCommand  = "";
	public static int currentCommandLine = 0;
	
	/**
	 * Parancs parszolasa
	 * @param command Parancs
	 * @param line Sorszam
	 * @return 0 siker eseten, egyebkent hibakod
	 * @throws Exception 
	 */
	public static void parseCommand(String command, int line) throws Exception {
		
		String[] params = command.split(" ");
		
		// Hibak kiiratasahoz eltarolja a aktualis parancsot es a sorat.
		currentCommand     = command;
		currentCommandLine = line;		
		
		/*
		 * Szeszteset cimenek kiiratasa
		 */
		if (params[0].equals("#")) {
			Logger.setTitle(command);
			return;
		
		/*
		 * create parancs meghivasa
		 */
		} else if (params[0].equals("create")) {
			
			if (params.length == 4) {
				Commands.create(params[1], params[2], params[3]);
				return;
			} else if (params.length == 3) {
				Commands.create(params[1], params[2]);
				return;
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
		
		/*
		 * add parancs meghivasa
		 */
		} else if (params[0].equals("add")) {

			if (params.length == 3) {
				Commands.add(params[1], params[2]);
				return;
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
		
		/*
		 * list parancs meghivasa
		 */
		} else if (params[0].equals("list")) {

			// Minden kilistazasa
			if (params.length == 1) {
				Commands.list("all");
				return;
			
			// Egy mezo tartalmanak kilistazasa
			} else if (params.length == 2) {
				Commands.list(params[1]);
				return;
			
			// Hibas parameterezes
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
			
		/*
		 * set parancs meghivasa
		 */
		} else if (params[0].equals("set")) {

			if (params.length == 4) {
				try {
					Commands.set(params[1], params[2], params[3]);
				} catch (Throwable e) {
					Logger.error("Parameter cannot be set!");
					return;
				}
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
			
		/*
		 * get parancs meghivasa
		 */
		} else if (params[0].equals("get")) {

			if (params.length == 3) {
				try {
					Commands.get(params[1], params[2]);
				} catch (Throwable e) {
					Logger.error("Parameter cannot be get!");
					return;
				}
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
			
		/*
		 * animate parancs meghivasa
		 */
		} else if (params[0].equals("animate")) {

			if (params.length == 2) {
				Commands.animate(params[1]);
				return;
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
			
		/*
		 * tick parancs meghivasa
		 */
		} else if (params[0].equals("tick")) {

			if (params.length == 1) {
				Commands.tick();
				return;
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
			
		/*
		 * addOdor parancs meghivasa
		 */
		} else if (params[0].equals("addOdor")) {

			if (params.length == 3) {
				Commands.addOdor(params[1], params[2]);
				return;
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
			
		/*
		 * spray parancs meghivasa
		 */
		} else if (params[0].equals("spray")) {

			if (params.length == 3) {
				Commands.spray(params[1], params[2]);
				return;
			} else {
				Logger.error("Mismatch parameter number!");
				return;
			}
			
		} else {
			Logger.error("Unknown command!");
			return;
		}
	}
	
	/**
	 * Create parancs megvalositasa.
	 * Elemek letrehozasa.
	 * @param object
	 * @param name
	 * @return 
	 * @throws Exception 
	 */
	public static void create(String object, String name) throws Exception {
		create(object, name, "");
	}
	
	/**
	 * Create parancs megvalositasa.
	 * Elemek letrehozasa.
	 * @param object
	 * @param name
	 * @param glade
	 * @throws Exception 
	 */
	public static void create(String object, String name, String glade) throws Exception {
		
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
			Glade gla = getGlade();
			obj = new AntKillerSpray(gla);	
		
		// Hangyaleso letrehozasa
		} else if (object.equals("AntLion")) {	
			obj = new AntLion();	
			
		// Hangyaszag letrehozasa
		} else if (object.equals("AntOdor")) {	
			obj = new AntOdor();	

		// Hangyaszagsemlegesito spray letrehozasa
		} else if (object.equals("AntOdorNeutralizerSpray")) {
			Glade gla = getGlade();
			obj = new AntOdorNeutralizerSpray(gla);	
		
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
			obj = new Poison();
		
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
			Logger.error("Unknown object "+object+"!");
		}
	}
	
	/**
	 * Add parancs megvalositasa.
	 * Elemek hozzarendelese egy mezohoz.
	 * @param object
	 * @param field
	 * @throws Exception 
	 */
	public static void add(String object, String field) throws Exception {

		// Masodik parameter tipusanak lekerdezese
		if (getObject(field) instanceof Field) {
			
			// Mezo lekerdezese
			Field fie  = (Field) getObject(field);
			
			// Entitasok
			if (getObject(object) instanceof Entity) {
				Entity obj = (Entity) getObject(object);
				
				fie.addEntity(obj);
				obj.setPosition(fie);
				
				Logger.success(object+" has been added to "+field+".");
			
			// Szagok
			// TODO: nem kell, ha csak addOdor parancs meg nem szunik
			} else if (getObject(object) instanceof Odor) {
				
				// Adja hozza a mezot a szaghoz
				Odor odor = (Odor) getObject(object);
				
				fie.addOdor(odor);
				odor.setPosition(fie);
				
				Logger.success(object+" has been added to "+field+".");
			
			} else {
				Logger.error("Wrong parameters!");
				return;
			}
			
			// TODO: ezt lehet mashogy kene megoldani, pl letrehozaskor belso fuggveny.
			// Entitas hozzaadasa a glade-hez.
			if (getObject(object) instanceof Active) {
				
				Glade glade = getGlade();
				glade.addActiveObject((Active) getObject(object));
			
			// Kaja hozzaadasa Glade-hez.
			} else if (getObject(object) instanceof Food) {
				
				Glade glade = getGlade();
				glade.addFood((Food) getObject(object));
				
			// Spray hozzaadasa Glade-hez.
			} else if (getObject(object) instanceof Spray) {
				
				Glade glade = getGlade();
				glade.addSpray((Spray) getObject(object));
			}
		// Csak a mezohoz lehet elemet hozzarendelni
		} else {
			Logger.error("Second parameter in not a Field!");
		}

	}
	
	/**
	 * Egy elem ertekenek megvaltoztatasa.
	 * set parancs megvalositasa.
	 * @param object
	 * @param parameter
	 * @param value
	 * @throws Throwable 
	 */
	public static void set(String object, String parameter, String value) throws Throwable {
		
		String type     = getObjectType(object);
		Object obj      = null;
		boolean success = true;
		
		// Hangyaszsun
		if (type.equals("Anteater")) {
			obj = (Anteater) getObject(object);

			// Menetirany megvaltoztatasa
			if (parameter.equals("direction")) {
				setVariable(obj, "direction", Direction.valueOf(value));
				
			// Megevett hangyak szamanak novelese
			} else if (parameter.equals("eatenAnts")) {
				setVariable(obj, "eatenAnts", Integer.parseInt(value));
			
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
			
		// Hangya	
		} else if (type.equals("Ant")) {
			obj = (Ant) getObject(object);
			
			// Menetirany megvaltoztatasa
			if (parameter.equals("direction")) {
				setVariable(obj, "direction", Direction.valueOf(value));
			
			// Etel hozzaadasa/elvetele
			} else if (parameter.equals("hasFood")) {
				setVariable(obj, "hasFood", value.equals("true") ? true : false);
				
			// Hangya megmergezese
			} else if (parameter.equals("poisened")) {
				setVariable(obj, "poisened", value.equals("true") ? true : false);
			
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
		
		// Mezo	
		} else if (type.equals("Field")) {
			obj = (Field) getObject(object);
			
			// Szomszedok hozzaadasa
			if (parameter.startsWith("neighbour")) {

				String direction = parameter.split("_")[1];
				Field field      = (Field) getObject(value);
				
				// A mezo nem letezik.
				if (field == null) {
					success = false;
				} else {
					((Field) obj).setNeighbour(Direction.valueOf(direction), field);
				}
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
		
		// Hangyaszag / Kajaszag
		} else if (type.equals("AntOdor") || type.equals("FoodOdor")) {
			obj = (Odor) getObject(object);
			
			// Szag intenzitas megadasa
			if (parameter.startsWith("intensity")) {
				((Odor) obj).setIntensity(Integer.parseInt(value));
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
		
		// Food
		} else if (type.equals("Food")) {
			obj = (Food) getObject(object);
			
			// Mennyiseg megvaltoztatasa
			if (parameter.equals("quantity")) {
				setVariable(obj, "quantity", Integer.parseInt(value));
			
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
			
		// Hangyaszag semelegisto / Hangyaolo spray
		} else if (type.equals("AntKillerSpray") || type.equals("AntOdorNeutralizerSpray")) {
			obj = (Spray) getObject(object);
			
			// Mennyiseg megvaltoztatasa
			if (parameter.equals("quantity")) {
				((Spray) obj).setQuantity(Integer.parseInt(value));
			
			// Hatosugar megvaltoztatasa
			} else if (parameter.equals("radius")) {
				((Spray) obj).setRadius(Integer.parseInt(value));
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
		
		// Mereg
		} else if (type.equals("Poison")) {
			obj = (Poison) getObject(object);
			
			// Mereg hatralevo eletenek beallitasa
			if (parameter.equals("TTL")) {
				setVariable(obj, "TTL", Integer.parseInt(value));
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}		
			
		// Ismeretlen elem.
		} else {
			Logger.error(object+" doesn't have "+parameter+" parameter!");
			return;
		}
		
		
		// Hibas erek megadasa
		if (!success) {
			Logger.error(object+" doesn't have "+parameter+" parameter!");
		
		// Sikeres lefutas
		} else {
			Logger.success(object+"'s "+parameter+" parameter set to "+value+".");
		}
	}
	
	
	public static void get(String object, String parameter) throws Throwable {
		
		String type     = getObjectType(object);
		Object obj      = null;
		String value    = "";
		
		// Hangyaszsun
		if (type.equals("Anteater")) {
			obj = (Anteater) getObject(object);

			// Menetirany lekerdezese
			if (parameter.equals("direction")) {
				value = getVariable(obj, "direction").toString();
				
			// Megevett hangyak szama
			} else if (parameter.equals("eatenAnts")) {
				value = getVariable(obj, "eatenAnts").toString();
			
			// Piheno modban van-e
			} else if (parameter.equals("isResting")) {
				value = getVariable(obj, "isResting").toString();	
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
		
		// Hangya
		} else if (type.equals("Ant")) {
			obj = (Ant) getObject(object);
			
			// Menetirany lekerdezese
			if (parameter.equals("direction")) {
				value = getVariable(obj, "direction").toString();
			
			// Etel lekerdezese
			} else if (parameter.equals("hasFood")) {
				value = getVariable(obj, "hasFood").toString();
				
			// Hangya mergezesenek lekerdezese
			} else if (parameter.equals("poisened")) {
				value = getVariable(obj, "poisened").toString();
			
			// Hangya hatralevo eletenek lekerdezese
			} else if (parameter.equals("TTL")) {
				value = getVariable(obj, "TTL").toString();
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
		
		// Hangyaszag / Kajaszag
		} else if (type.equals("AntOdor") || type.equals("FoodOdor")) {
			obj = (Odor) getObject(object);
			
			// Szag intenzitas megadasa
			if (parameter.startsWith("intensity")) {
				value = ""+((Odor) obj).getIntensity();
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}

			
		// Food
		} else if (type.equals("Food")) {
			obj = (Food) getObject(object);
			
			// Mennyiseg lekerdezese
			if (parameter.equals("quantity")) {
				value = getVariable(obj, "quantity").toString();
			
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}
			
		// Hangyaszag semelegisto / Hangyaolo spray
		} else if (type.equals("AntKillerSpray") || type.equals("AntOdorNeutralizerSpray")) {
			obj = (Spray) getObject(object);
			
			// Mennyiseg megvaltoztatasa
			if (parameter.equals("quantity")) {
				value = ""+((Spray) obj).getQuantity();
			
			// Hatosugar lekerdezese
			} else if (parameter.equals("radius")) {
				value = ""+((Spray) obj).getRadius();	
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}	
		
		// Mereg
		} else if (type.equals("Poison")) {
			obj = (Poison) getObject(object);
			
			// Mereg hatralevo eletenek lekerdezese
			if (parameter.equals("TTL")) {
				value = getVariable(obj, "TTL").toString();
				
			// Ismeretlen parameter
			} else {
				Logger.error(object+" doesn't have "+parameter+" parameter!");
				return;
			}	
		/*
		// Mezo
		} else if (type.equals("Field")) {
			obj = (Field) getObject(object);

			// Menetirany lekerdezese
			if (parameter.equals("neighbours")) {
				value = getVariable(obj, "neighbours").toString();
			}
		*/	
		// Ismeretlen elem.
		} else {
			Logger.error("Unknown object "+object+"!");
			return;
		}
		
		
		// Hibas erek megadasa
		if (value.equals("")) {
			Logger.error(object+"'s "+parameter+" parameter value is incorrect!");
		// Sikeres lefutas
		} else {
			Logger.success(object+"'s "+parameter+" parameter is "+value+".");
		}
	}
	
	/**
	 * Active objektumok animalasa.
	 * animate parancs megvalositasa.
	 * @param object
	 * @throws Exception 
	 */
	public static void animate(String object) throws Exception {
		
		Object obj = getObject(object);
		
		if (obj instanceof Active) {
			Active active = (Active) obj;
			// Meg ne hivja meg.
			try {
				active.animate();
			} catch (Exception e) {
				e.printStackTrace();
				Logger.error("An error occured during animation.");
				return;
				
			}
			
			String key = getKey(obj);
			Logger.success(key+" has been animated.");
		}
	}
	
	/**
	 * Ido leptetese.
	 * tick parancs megvalositasa.
	 */
	public static void tick() throws Exception{
		
		Glade glade = getGlade();
		
		if (glade.getFoodQuantity() == 0) {
			Logger.success("Game Over!");
		} else {
			glade.tick();
			
			Logger.success("Tick.");
		}
	}
	
	/**
	 * Szag hozzaadasa egy mezohoz.
	 * addOdor parancs megvalositasa.
	 * @param object
	 * @param field
	 * @throws Exception 
	 */
	public static void addOdor(String object, String field) throws Exception {
		add(object, field);
	}
	
	/**
	 * A megadott spray hasznalata
	 * spray parancs megvalositasa.
	 * @param type
	 * @param field
	 * @throws Exception 
	 */
	public static void spray(String type, String field) throws Exception {
		
		Object obj = getObject(type);
		Object fie = getObject(field);
		
		// A mezo nem talalhato
		if (fie == null) {
			Logger.error("Field with name "+field+" cannot be found!");
			return;
		
		// A megadaott neve, nem egy Field
		} else if (!getObjectType(fie).equals("Field")) {
			Logger.error("The type of "+field+" is not Field!");
			return;
		
		// Field
		} else {
			
			// Nem spay-jel probalkozik
			if (!(obj instanceof Spray)) {
				Logger.error("The type of "+type+" is not a Spray!");
				return;
			
			// Spray hasznalata.
			} else {
				Spray spray = (Spray) obj;
				
				// Csak akkor fujjon, ha meg van is benne.
				if (spray.getQuantity() > 0) {
					
					spray.use(((Field) fie));
					Logger.success(type+" has been used with center "+field+". "+spray.getQuantity()+" blows left.");
				
				// Kifigyott a spray
				} else {
					Logger.success(type+" cannot be used. The spray has run out!");
				}
			}
		}
	}
	
	/**
	 * Aktualis helyzet kilistazasa
	 * List parancs megvalositasa
	 * @param mode
	 * @throws Exception 
	 */
	public static void list(String mode) throws Exception {
		
		// A glade valamint az osszes mezo objektumainak kilistazasa
		if (mode.equals("all")) {

			Glade gla                = getGlade();
			String activeObjectsName = getObjectsName(gla.getActiveObjects());
			
			Logger.success("List all:");
			// Aktiv objektumok kilistazasa
			Logger.success("\tList glade: "+(activeObjectsName.equals("") ? "-" : activeObjectsName));
		
			for (Entry<String, Object> obj : objectsByKey.entrySet()) {
				
				String key = obj.getKey();
				// Mezok kikeresese
				if (getObjectType(key).equals("Field")) {
					
					Field fie                  = (Field) obj.getValue();
					ArrayList<Entity> entities = fie.getEntities();
					ArrayList<Odor> odors      = fie.getOdors();
					
					// Entitasok es szagok osszefesulese
					ArrayList<Object> objects = new ArrayList<Object>();
					objects.addAll(entities);
					objects.addAll(odors);
					
					String objectsName        = getObjectsName(objects);
					
					Logger.success("\tList "+obj.getKey()+": "+(objectsName.equals("") ? "-" : objectsName));
				}
			}
			
		// Egy megadott mezo tartalmanak kilistazasa
		} else if (getObjectType(mode).equals("Field")){
			
			Field fie = (Field) getObject(mode);
			
			if (fie != null) {

				ArrayList<Entity> entities = fie.getEntities();
				ArrayList<Odor> odors      = fie.getOdors();
				
				// Entitasok es szagok osszefesulese
				ArrayList<Object> objects = new ArrayList<Object>();
				objects.addAll(entities);
				objects.addAll(odors);
				
				String objectsName        = getObjectsName(objects);
				
				Logger.success("List "+mode+": "+(objectsName.equals("") ? "-" : objectsName));
			
			// Nem letezo mezore valo hivatkozas
			} else {
				Logger.error("Unknown "+mode+" field!");
				return;
			}
		} else {
			Logger.error("Wrong parameter type!");			
		}
	}
	
	/**
	 * Kikeresi a palyat az objektum listabol.
	 * @return
	 */
	private static Glade getGlade() throws Exception {
		
		for (Entry<String, Object> obj : objectsByKey.entrySet()) {
			
			String key = obj.getKey();
			// Glade kikeresese
			if (getObjectType(key).equals("Glade")) {
				return (Glade) obj.getValue();
			}
		}
		
		Logger.error("You have to create a Glade first!");
		
		return null;
	}
	
	/**
	 * Kikeresi es osszefuzi az objektumok nevet.
	 * @param entities
	 */
	private static String getObjectsName(ArrayList<?> entities) {
		
		StringBuilder sb = new StringBuilder();
		
		// Entitasokhoz kikeresi a nevuket
		for (Object entity : entities) {
			String key = getKey(entity);
			
			// Ha nem talalhato a kulcs, akkor a tipusat adja vissza
			if (key == null) {
				key = getObjectType(entity);
			}
			
			// Vesszok betuzdelese
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(key);
		}
	
		return sb.toString();
	}
	
	/**
	 * Objektum lekerdezese kulcs alapjan.
	 * @param key
	 * @return
	 */
	private static Object getObject(String key) {
		return objectsByKey.get(key);
	}
	
	/**
	 * Kulcs lekerdezese objektum alapjan.
	 * @param object
	 * @return
	 */
	public static String getKey(Object object) {
		return objectsByValue.get(object);
	}
	
	/**
	 * Visszaadja az objektumok egyszerusitett nevet kulcs alapjan.
	 * @param key
	 * @return
	 */
	private static String getObjectType(String key) {
		return getObject(key).getClass().getSimpleName();
	}
	
	/**
	 * Visszaadj az objektumok egyszerusitett nevet referencia alapjan.
	 * @param obj
	 * @return
	 */
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
		Logger.success(object+" has been created with name "+name+".");
	}
	
	/**
	 * Objektumok torlese.
	 */
	public static void clear() {
		objectsByKey.clear();
		objectsByValue.clear();
	}
	
	/**
	 * Egy osztaly privat valtozoit lehet vele lekerdezni.
	 * @param object
	 * @param fieldName
	 * @return
	 * @throws Throwable
	 */
	public static Object getVariable(Object object, String fieldName) throws Throwable {
        
		java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);       
        field.setAccessible(true);
       
        return field.get(object);
    }
	
	/**
	 * Egy osztaly privat valtozoit lehet vele beallitani.
	 * @param object
	 * @param fieldName
	 * @param value
	 * @throws Throwable
	 */
	public static void setVariable(Object object, String fieldName, Object value) throws Throwable {
        
		java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);       
        field.setAccessible(true);
        field.set(object, value);
    }
}
