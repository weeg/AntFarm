package view;

import java.awt.Graphics2D;

import modell.Drawable;
import modell.Entity;

public class View {
	
	protected boolean changed = true;
	protected Drawable modell;
	
	public void change() {
		this.changed = true;
	}
	
	/**
	 * TODO: lehet inkabb egy listaba kellene belerakni a valtozott elemeket
	 * @return
	 */
	public boolean isChanged() {
		return changed;
	}
	
	public void redraw(Graphics2D g) {
		this.changed = false;
	}
	
	public void setModell(Drawable modell) {
		this.modell = modell;
	}
}
