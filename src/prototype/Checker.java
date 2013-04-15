package prototype;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Checker {
	
	public static void check(String fileName) throws Throwable {		
		
		boolean different = false;
		BufferedReader br_out = null;
		BufferedReader br_expected_out = null;
		
		try {			
			br_out = new BufferedReader(new FileReader(Logger.getResultFileName(fileName, "_out")));
			br_expected_out = new BufferedReader(new FileReader(Logger.getResultFileName(fileName, "_expected_out")));
	    	    	
	        String line_out = br_out.readLine();
	        String line_expected_out = br_expected_out.readLine();
	        
	        int line = 1;
	        while (line_out != null && line_expected_out != null) {	        	
	        	
	        	if (!line_out.equals(line_expected_out)) {
	        		System.out.println("\tDifference detected in line " + line);
	        		System.out.println("\t\tTest result:     " + line_out);
	        		System.out.println("\t\tExpected result: " + line_expected_out);
	        		
	        		different = true;
	        	}
	        	
	        	line_out = br_out.readLine();
	        	line_expected_out = br_expected_out.readLine();
	        	
	        	line++;
	        }
	        
	        if (!different) {
	        	System.out.println("The text result equals the expected result.");
	        }
		} catch (FileNotFoundException e) {
	        System.out.println("A file was not found: " + e.getMessage());
		} catch (IOException e) {
	        System.out.println(e.getMessage());
	    } finally {
	    	if (br_out != null) {
	    		br_out.close();
	    	}
	        if (br_expected_out != null) {
	        	br_expected_out.close();
	        }	        
	    }
	}
}
