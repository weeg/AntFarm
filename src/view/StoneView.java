package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Field;
import modell.Stone;
import modell.Water;

public class StoneView extends View {
	public void redraw(Graphics2D g) {
		
		Stone stone = (Stone)modell;
		Field field = stone.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(Color.GRAY);
		
		double size = 10; 
		g.fill(new Ellipse2D.Double(fieldView.position.x - size / 2, fieldView.position.y - size / 2, size, size));
		
		this.changed = false;
	}		
}
