package view;

import java.awt.Graphics2D;
import java.util.ArrayList;

import modell.Drawable;
import modell.Field;
import modell.Glade;

public class GladeView implements View {
	
	private Glade glade;
	
	private ArrayList<FieldView> changedFields = new ArrayList<FieldView>();
	
	public void addChangedFieldView(FieldView fieldView) {
		if (!changedFields.contains(fieldView)) {
			changedFields.add(fieldView);
		}
	}
	
	public void redrawAll(Graphics2D g) {
		for (Field field : glade.getFields()) {			
			field.getView().redraw(g);
		}
		changedFields.clear();	
	}
	
	public void redraw(Graphics2D g) {
		for (FieldView fieldView : changedFields) {
			fieldView.redraw(g);
		}		
		changedFields.clear();	
	}

	public void change() { }

	public void setModel(Drawable model) {
		glade = (Glade)model;
	}
}
