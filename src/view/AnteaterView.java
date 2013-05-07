package view;

import java.awt.Color;
import java.awt.Graphics2D;

import modell.Ant;
import modell.Anteater;
import modell.Field;

public class AnteaterView extends View {
	
	public void redraw(Graphics2D g) {
	
		Anteater anteater = (Anteater)modell;
		Field field = anteater.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(Color.CYAN);
		
		int size = 16; 
		g.fillRect(fieldView.position.x - size / 2, fieldView.position.y - size / 2, size, size);
		
		this.changed = false;
	}
	
	public void change() {

		Anteater anteater = (Anteater)modell;
		Field field = anteater.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		fieldView.change();
	}
}
