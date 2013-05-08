package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Drawable;
import modell.Field;
import modell.Stone;

public class StoneView implements View {
	
	private Stone stone;
	
	public void redraw(Graphics2D g) {
		Field field = stone.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(200, 200, 200));
		
		double size = 10; 
		g.fill(new Ellipse2D.Double(fieldView.getPosition().x - size / 2, 
				fieldView.getPosition().y - size / 2, size, size));
	}
	
	public void change() {
		Field field = stone.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		fieldView.change();
	}

	public void setModel(Drawable model) {
		stone = (Stone)model;
	}
}
