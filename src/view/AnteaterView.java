package view;

import java.awt.Color;
import java.awt.Graphics2D;

import modell.Anteater;
import modell.Drawable;
import modell.Field;

public class AnteaterView implements View {
	
	private Anteater anteater;
	
	public void redraw(Graphics2D g) {
	
		Field field = anteater.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(165, 42, 42));
		
		int size = 16; 
		g.fillRect(fieldView.getPosition().x - size / 2, 
				fieldView.getPosition().y - size / 2, size, size);
	}
	
	public void change() {
		Field field = anteater.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		fieldView.change();
	}

	public void setModel(Drawable model) {
		anteater = (Anteater)model;
	}
}
