package design;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.GladeView;
import view.View;
import modell.Glade;

public class Design {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });		
		
	}
	
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Ant Farm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Glade glade = new Glade();
        glade.start();       
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
