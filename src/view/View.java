package view;

import java.awt.Graphics2D;

import modell.Drawable;

public interface View {
	
	public void change();	
	
	public void redraw(Graphics2D g);
	
	public void setModel(Drawable model);
}
