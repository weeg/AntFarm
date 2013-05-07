package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.AntLion;
import modell.Field;


public class AntLionView extends View {
	
	public void redraw(Graphics2D g) {
		
		AntLion antLion = (AntLion)modell;
		Field field = antLion.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(Color.CYAN);
		
		int size = 10; 
		g.fill(new Ellipse2D.Double(fieldView.position.x - size / 2, fieldView.position.y - size / 2, size, size));
		
		this.changed = false;
	}
}
