package view;

import java.awt.Color;
import java.awt.Graphics2D;

import modell.Drawable;
import modell.Field;
import modell.Water;

public class WaterView implements View {	
	
	private Water water;
	
	public void redraw(Graphics2D g) {
		Field field = water.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(30, 144, 255));
		
		Drawer.drawField(g, fieldView.getCoord().x, fieldView.getCoord().y,
				fieldView.getSize(), true);
	}

	public void change() { }

	public void setModel(Drawable model) {
		water = (Water)model;
	}
	
	
}
