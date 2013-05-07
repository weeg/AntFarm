package view;

import java.awt.Color;
import java.awt.Graphics2D;

import modell.AntHill;
import modell.Field;

public class AntHillView extends View {	
	
	public void redraw(Graphics2D g) {
					
		AntHill antHill = (AntHill)modell;
		Field field = antHill.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(Color.BLACK);
		g.fillRect(fieldView.position.x, fieldView.position.y, 10, 10);
		System.out.println(fieldView.position.x + ", " + fieldView.position.y);
		
		this.changed = false;
	}
}
