package prototype;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Logger {
	
	/*
	 * A kimeneti logokat tarolo tomb
	 */
	private static ArrayList<String> logs = new ArrayList<String>();
	private static String title = "";
	
	/**
	 * Hiba uzenetek hozzaadasa.
	 * @param log
	 * @return hibakod
	 */
	public static int error(String log) {
		add("\tError at line #"+Commands.currentCommandLine+" \""+Commands.currentCommand+"\". "+log);
				
		return 1;
	}
	
	public static int setTitle(String title) {
		Logger.title = title;
		
		return 0;
	}
	
	public static String getTitle() {
		return title;
	}
	
	/**
	 * Teszteredmeny hozzaadasa.
	 * @param log
	 */
	public static void success(String log) {
		add(log);
	}
	
	/**
	 * A log listahoz hozzafuz egy ujabb bejegyzest.
	 * @param log
	 */
	private static void add(String log) {
		// System.out.println(log);
		logs.add(log);
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
	        	commands.add(line);
	            line = br.readLine();
	        }
	        
	        return commands;
	    } finally {
	        br.close();
	    }
	}
	
	/**
	 * Log kiirasa a konzolra.
	 */
	public static void printConsole() {
		for (String log : logs) {
			System.out.println(log);
		}
	}
	
	/**
	 * Log kiiratasa fileba.
	 * @throws IOException
	 */
	public static void printFile(String fileName) throws IOException {
		
		BufferedWriter out = new BufferedWriter(new FileWriter(getResultFileName(fileName, "_out")));

        for (String log : logs) {
        	// Uccso sorba ne szurjon be uj sort
        	if (logs.indexOf(log) != 0) {
				out.newLine();
			}
        	out.write(log);
		}
        
        out.close();
	}

	/**
	 * Visszaadja a kimeneti file nevet a bemeneti file alapjan.
	 * @return
	 */
	public static String getResultFileName(String fileName, String postfix) {
		// Kiterjesztes megkeresese
		String extension = "";
		String path      = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		    path      = fileName.substring(0, i);
		}
		
		return path + postfix + "." + extension;
	}
	
	/**
	 * Torli a logokat
	 */
	public static void clear() {
		logs.clear();
		title = "";
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
		    	System.out.print("Answer: ");
				 String s = bufferedReader.readLine();
				 answer = Integer.parseInt(s);
				 if (answer <= 0 || answer > answers.length) {
					 answer = 0;
					 throw new Exception();					 
				 }
			} catch (Exception e) {
				System.out.println("The given answer is not valid. Please choose a number between 1 and " 
					+ answers.length + ".");
			}
		}
		System.out.println();
		return answer;
	    
	}
}
