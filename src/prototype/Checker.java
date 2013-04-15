package prototype;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Checker {
	
	public static void check(String fileName) throws Throwable {		
		
		BufferedReader br_out = null;
		BufferedReader br_expected_out = null;
		
		try {			
			br_out = new BufferedReader(new FileReader(Logger.getResultFileName(fileName, "_out")));
			br_expected_out = new BufferedReader(new FileReader(Logger.getResultFileName(fileName, "_expected_out")));
	    	    	
	        String line_out = br_out.readLine();
	        String line_expected_out = br_expected_out.readLine();
	        StringBuilder out = new StringBuilder();
	        
	        
	        int line = 1;
	        while (line_out != null && line_expected_out != null) {	        	
	        	
	        	// Eltero sorok kiiaratasa
	        	if (!line_out.equals(line_expected_out)) {

	        		String row = String.format("% 3d", line);
	        		
	        		out.append("\n\t" + row+": " + line_out);
	        		out.append("\n\t" + row+": " + line_expected_out);
	        		out.append("\n");
	        	}
	        	
	        	line_out = br_out.readLine();
	        	line_expected_out = br_expected_out.readLine();
	        	
	        	line++;
	        }
	        
	        // Nincs hiba
	        if (out.length() == 0) {
	        	System.out.println("\n\tThe text result equals the expected result.");
	        
	        // Hiba
	        } else {
	        	System.out.println("\n\tDifference detected at lines (Test/Expected):");
	        	System.out.print(out.toString());
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
