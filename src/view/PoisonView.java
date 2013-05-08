package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.Drawable;
import modell.Poison;
import modell.Field;

public class PoisonView implements View {

	private Poison poison;
	
	public void redraw(Graphics2D g) {
		Field field = poison.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(20,20,20,50));
		
		double size = 26; 
		g.fill(new Ellipse2D.Double(fieldView.getPosition().x - size / 2, 
				fieldView.getPosition().y - size / 2, size, size));
	}
	
	public void change() {		
		poison.getPosition().getView().change();
	}

	public void setModel(Drawable model) {
		poison = (Poison)model;
	}
}
