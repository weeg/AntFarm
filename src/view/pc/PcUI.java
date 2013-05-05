package view.pc;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.GladeView;
import view.Point;
import view.View;

import modell.Direction;
import modell.Field;
import modell.Glade;

public class PcUI extends JFrame {
	
	private Glade glade;
	private PcGladeView gladeView;
	private JPanel canvas;
	
	public PcUI() {
		
		glade     = new Glade();
		gladeView = new PcGladeView();	
		
		// Mezok letrehozasa
		createFields();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		// Rajz felulet
		JPanel canvas = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Graphics2D g2 = (Graphics2D)g;
                
                // Mezok kirajzolasa
                for (View view: gladeView.getViews()) {
                	if (view.isChanged()) {
                		view.redraw(g2);
                	}
                }
             }
        };
        
        //canvas.repaint();
		
        add(canvas, BorderLayout.CENTER);
		
		this.setSize(500, 500);
		setVisible(true);
		
		// Close the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Palya felepitese, a mezok hozzaadasa
	 * A mezok szamozasa cicc-cakkosan tortenik
	 */
	private void createFields() {
		
		// Palya magassaga es szelessege
		int height = 20;
		int width  = 20;

		// Mezok letrehozasa
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				// Uj mezo letrehozasa
				Field field = new Field(glade);
				
				// Uj nezet letrehozasa
				PcFieldView fieldView = new PcFieldView();
				fieldView.setModell(field);
				fieldView.setPosition(new Point(row, column));
				
				// Nezet hozzarendelese a mezohoz
				field.setView(fieldView);
				
				// Tarolasa gladeben
				gladeView.addView(fieldView);
				glade.addField(field);
			}
		}
		
		
		// Szomszedok megadasa
		ArrayList<Field> fields = glade.getFields();
		for (Field field : fields) {
			
			// Aktualis mezo indexe
			int index = fields.indexOf(field);
			
			// Northwest
			try {
				Field nw = fields.get(index - width - 1);
				field.setNeighbour(Direction.NW, nw);
		    // Nem letezo index
			} catch (IndexOutOfBoundsException e) {}
			
			// North
			try {
				Field n = fields.get(index - width);
				field.setNeighbour(Direction.N, n);
			} catch (IndexOutOfBoundsException e) {}
			
			// Northeast
			try {
				Field ne = fields.get(index - width + 1);
				field.setNeighbour(Direction.NE, ne);
			} catch (IndexOutOfBoundsException e) {}
			
			// Southwest
			try {
				Field sw = fields.get(index - 1);
				field.setNeighbour(Direction.SW, sw);
			} catch (IndexOutOfBoundsException e) {}
			
			// South
			try {
				Field s = fields.get(index + width);
				field.setNeighbour(Direction.S, s);
			} catch (IndexOutOfBoundsException e) {}
			
			// Southeast
			try {
				Field se = fields.get(index + 1);
				field.setNeighbour(Direction.SE, se);
			} catch (IndexOutOfBoundsException e) {}
		}
	}
}
