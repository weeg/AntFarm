package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import modell.Field;
import modell.Glade;

public class GladeView extends View {
	
	// TODO: Valszeg csak a mezoket kellene eltarolni, vagy azokat meg kulon
	private ArrayList<View> views = new ArrayList();
	
	public void addView(View view) {
		views.add(view);
	}
	
	public ArrayList<View> getViews() {
		return views;
	}
	
	public void redraw(Graphics2D g) {
		
		Glade glade = (Glade)modell;		

		for (Field field : glade.getFields()) {
			field.getView().redraw(g);
		}	
		
		this.changed = false;
	}
}
