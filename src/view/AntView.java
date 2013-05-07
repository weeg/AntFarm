package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Ant;
import modell.AntHill;
import modell.Field;

public class AntView extends View {

	public void redraw(Graphics2D g) {
		
		Ant ant = (Ant)modell;
		Field field = ant.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		if (ant.hasFood()) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
		
		double size = 5; 
		g.fill(new Ellipse2D.Double(fieldView.position.x - size / 2, fieldView.position.y - size / 2, size, size));
		
		this.changed = false;
	}
	
	public void change() {
		Ant ant = (Ant)modell;
		Field field = ant.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		fieldView.change();
	}
}
