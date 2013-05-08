package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Drawable;
import modell.Field;
import modell.Food;

public class FoodView implements View {
	
	private Food food;
	
	public void redraw(Graphics2D g) {
		Field field = food.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(255, 125, 0));
		
		double size = 15; 
		g.fill(new Ellipse2D.Double(fieldView.getPosition().x - size / 2,
				fieldView.getPosition().y - size / 2, size, size));
	}

	public void change() {
		Field field = food.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		fieldView.change();
	}
	
	public void setModel(Drawable model) {
		food = (Food)model;
	}
}
