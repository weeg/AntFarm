package prototype;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {
	
	/*
	 * A kimeneti logokat tarolo tomb
	 */
	private static ArrayList<String> logs = new ArrayList<String>();
	
	private static String fileName = "";
	
	/**
	 * A log listahoz hozzafuz egy ujabb bejegyzest.
	 * @param log
	 */
	public static void add(String log) {
		logs.add(log);
	}
	
	/**
	 * Parancsok beolvasasa egy megadott fajlbol.
	 * @param file
	 * @return A parancsok listaja
	 * @throws IOException
	 */
	public static ArrayList<String> readFile(String file) throws IOException {
		
		fileName = file;
		
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
	public static void printFile() throws IOException {
		
		BufferedWriter out = new BufferedWriter(new FileWriter(getResultFileName()));

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
	private static String getResultFileName() {
		// Kiterjesztes megkeresese
		String extension = "";
		String path      = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		    path      = fileName.substring(0, i);
		}
		
		return path+"_out."+extension;
	}
}
