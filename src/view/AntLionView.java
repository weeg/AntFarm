package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import modell.AntLion;
import modell.Drawable;
import modell.Field;


public class AntLionView implements View {
	
	private AntLion antLion;
	
	public void redraw(Graphics2D g) {
		Field field = antLion.getPosition();
		FieldView fieldView = (FieldView)field.getView();
		
		g.setColor(new Color(165, 42, 42));
		
		int size = 10;
		g.setStroke(new BasicStroke(3));
		g.drawLine(fieldView.getPosition().x - size / 2, fieldView.getPosition().y - size / 2,
				fieldView.getPosition().x + size / 2, fieldView.getPosition().y + size / 2);
		g.drawLine(fieldView.getPosition().x - size / 2, fieldView.getPosition().y + size / 2,
				fieldView.getPosition().x + size / 2, fieldView.getPosition().y - size / 2);
		g.setStroke(new BasicStroke(1));
	}

	public void change() { }

	public void setModel(Drawable model) {
		antLion = (AntLion)model;
	}
}
