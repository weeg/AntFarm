package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import modell.Entity;
import modell.Field;



public class FieldView extends View {

	private ArrayList<View> views = new ArrayList();
	
	/** A mezo koordinataja */
	protected Point coord;
	
	/** A mezo pozicioja a tisztason */
	protected Point position = new Point();
	
	// Egy mezo merete pixelben
	protected int size = 15;
	
	//TODO remove!!!
	public boolean sel = false;
	
	/**
	 * Mezo kozeppontjanak megadasa
	 * @param point
	 */
	public void setCoord(Point point) {
		this.coord = point;
		
		double m = size * Math.sqrt(3) / 2.0;
		position.x = (int)(size / 2 + coord.x * 3 * size / 2);
		position.y = (int)(coord.y * 2 * m + (coord.x % 2) * m);
	}
	
	public Point getCoord() {
		return coord;
	}
	
	public void setModell(Field modell) {
		this.modell = modell;
	}
	
	public void redraw(Graphics2D g) {
		if (changed) {
			double xstart = -size / 2 + coord.x * 3 * size / 2;    		
			int xpoints[] = { (int)(xstart), 
							  (int)(xstart + size / 2), 
							  (int)(xstart + 3 * size / 2), 
							  (int)(xstart + 2 * size), 
							  (int)(xstart + 3 * size / 2), 
							  (int)(xstart + size / 2), 
							  (int)(xstart) };
			double m = size * Math.sqrt(3) / 2.0;
			double ystart = coord.y * 2 * m + (coord.x % 2) * m;
	    	int ypoints[] = { (int)(ystart), 
	    					  (int)(ystart + m), 
	    					  (int)(ystart + m), 
	    					  (int)(ystart), 
	    					  (int)(ystart - m), 
	    					  (int)(ystart - m), 
	    					  (int)(ystart) };
	    	//TODO: Remove!!!
	    	if (!sel) {
	    		g.setColor(new Color(34, 139, 34));
	    	} else {
	    		g.setColor(new Color(255, 139, 34));
	    	}
	    	g.fillPolygon(xpoints, ypoints, 6);
	    	
	    	g.setColor(new Color(60, 179, 113));
	    	g.drawPolygon(xpoints, ypoints, 6);
	    	    
	    	Field field = (Field)modell;
	    	for (Entity entity : field.getEntities()) {
	    		if (entity.getView() != null) {
	    			entity.getView().redraw(g);
	    		}
	    	}
		}
		
		changed = false;
	}
}
