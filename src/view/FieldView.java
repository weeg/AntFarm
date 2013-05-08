package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import modell.Drawable;
import modell.Entity;
import modell.Field;
import modell.Glade;



public class FieldView implements View {
    
	private Field field;
	
	/** A mezo koordinataja */
	private Point coord;
	
	/** A mezo pozicioja a tisztason */
	private Point position = new Point();
	
	/** Egy mezo merete pixelben */
	private int size = 15;
	
	public int getSize() {
		return size;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setCoord(Point point) {
		this.coord = point;
		
		double m = size * Math.sqrt(3) / 2.0;
		position.x = (int)(size / 2 + coord.x * 3 * size / 2);
		position.y = (int)(coord.y * 2 * m + (coord.x % 2) * m);
	}
	
	public Point getCoord() {
		return coord;
	}	
	
	public void redraw(Graphics2D g) {
		int green = 130 + (field.getOdorIntensity() * 4) % 126;
		
		g.setColor(new Color(34, green, 34));
		Drawer.drawField(g, coord.x, coord.y, size, true);

    	for (Entity entity : field.getEntities()) {
    		if (entity.getView() != null) {
    			entity.getView().redraw(g);
    		}
    	}
	}

	public void change() {
		Glade glade = field.getGlade();
		GladeView gladeView = (GladeView)glade.getView();
		gladeView.addChangedFieldView(this);
	}

	public void setModel(Drawable model) {
		field = (Field)model;
	}
}
