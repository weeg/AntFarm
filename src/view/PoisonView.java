package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Field;
import modell.Poison;

public class PoisonView extends View {
	public void redraw(Graphics2D g) {
		
		Poison poison = (Poison)modell;
		Field field = poison.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(181,230,39,50));
		
		double size = 25; 
		g.fill(new Ellipse2D.Double(fieldView.position.x - size / 2, fieldView.position.y - size / 2, size, size));
		
		this.changed = false;
	}
	
	public void change() {
		Poison poison = (Poison)modell;
		Field field = poison.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		fieldView.change();
	}
}