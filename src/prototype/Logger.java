package prototype;

import java.util.ArrayList;

public class Logger {
	
	/*
	 * A kimeneti logokat tarolo tomb
	 */
	private static ArrayList<String> logs = new ArrayList<String>();
	
	/**
	 * A log listahoz hozzafuz egy ujabb bejegyzest.
	 * @param log
	 */
	public static void add(String log) {
		logs.add(log);
	}
	
	/**
	 * Log kiirasa a konzolra
	 */
	public static void printConsole() {
		for (String log : logs) {
			System.out.println(log);
		}
	}
}
