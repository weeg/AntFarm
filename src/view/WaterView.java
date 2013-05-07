package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Field;
import modell.Water;

public class WaterView extends View {	
	
	public void redraw(Graphics2D g) {
		
		Water water = (Water)modell;
		Field field = water.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(Color.BLUE);
		
		double size = 15; 
		g.fill(new Ellipse2D.Double(fieldView.position.x - size / 2, fieldView.position.y - size / 2, size, size));
		
		this.changed = false;
	}	
}
