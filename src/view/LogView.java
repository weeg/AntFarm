package view;

import java.awt.Color;
import java.awt.Graphics2D;

import modell.Drawable;
import modell.Field;
import modell.Log;

public class LogView implements View {
	
	private Log log;
	
	public void redraw(Graphics2D g) {
		

		Field field = log.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(140, 70, 20));
		
		Drawer.drawField(g, fieldView.getCoord().x, fieldView.getCoord().y, 
				fieldView.getSize(), true);
	}

	public void change() { }

	public void setModel(Drawable model) {
		log = (Log)model;
	}	
}
