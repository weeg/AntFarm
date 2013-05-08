package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.AntHill;
import modell.Drawable;
import modell.Field;

public class AntHillView implements View {	
	
	private AntHill antHill;
	
	public void redraw(Graphics2D g) {
					
		Field field = antHill.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(Color.BLACK);
		
		double size = 10; 
		g.fill(new Ellipse2D.Double(fieldView.getPosition().x - size / 2, 
				fieldView.getPosition().y - size / 2, size, size));

	}

	public void change() { }

	public void setModel(Drawable model) {
		antHill = (AntHill)model;
	}
}
