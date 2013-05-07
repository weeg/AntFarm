package view;

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
	
	public Field getField(int x, int y) {
		Glade glade = (Glade)modell;		
		FieldView nearest = null;
		double nearest_length = 2000.0;
		for (Field field : glade.getFields()) {
			FieldView fv = (FieldView)(field.getView());
			int x1 = (fv.position.x - x);
			int y1 = (fv.position.y - y); 
			double len = Math.sqrt(x1*x1 + y1*y1); 
			if (len < nearest_length) {
				nearest = fv;
				nearest_length = len;
			}
		}
		return (Field)nearest.modell;
	}
}
