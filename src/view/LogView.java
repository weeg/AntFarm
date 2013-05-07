package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Field;
import modell.Log;

public class LogView extends View {
	public void redraw(Graphics2D g) {
		
		Log	log = (Log)modell;
		Field field = log.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(140, 70, 20));
		
		double size = 10; 
		g.fill(new Ellipse2D.Double(fieldView.position.x - size / 2, fieldView.position.y - size / 2, size, size));
		
		this.changed = false;
	}	
}
