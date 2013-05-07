package design;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

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
        frame.setPreferredSize(new Dimension(500, 500));
        
        final Glade glade = new Glade();
        glade.start();
        glade.setView(new GladeView());
        
        final JPanel canvas = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Graphics2D g2 = (Graphics2D)g;
                glade.getView().redraw(g2);
             }
        };
        
        // Timer 
        int delay = 1000;
        ActionListener tickListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                glade.tick();
                glade.getView().redraw((Graphics2D)canvas.getGraphics());
            }
        };
        new Timer(delay, tickListener).start();
        
        //canvas.repaint();
        frame.add(canvas, BorderLayout.CENTER);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
