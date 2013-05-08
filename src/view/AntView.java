package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Ant;
import modell.Drawable;
import modell.Field;

public class AntView implements View {

	private Ant ant;
	
	public void redraw(Graphics2D g) {
		Field field = ant.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(Color.BLACK);
		if (ant.hasFood()) {
			g.setColor(Color.RED);
		} 
		if (ant.isPoisoned()) {
			g.setColor(new Color(181,230,39));
		}
		
		double size = 5; 
		g.fill(new Ellipse2D.Double(fieldView.getPosition().x - size / 2, 
				fieldView.getPosition().y - size / 2, size, size));
	}
	
	public void change() {		
		Field field = ant.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		fieldView.change();
	}

	public void setModel(Drawable model) {
		ant = (Ant)model;
	}
}
