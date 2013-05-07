package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.AntHill;
import modell.Field;

public class AntHillView extends View {	
	
	public void redraw(Graphics2D g) {
					
		AntHill antHill = (AntHill)modell;
		Field field = antHill.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(Color.BLACK);
		
		double size = 10; 
		g.fill(new Ellipse2D.Double(fieldView.position.x - size / 2, fieldView.position.y - size / 2, size, size));
		
		this.changed = false;
	}
}
