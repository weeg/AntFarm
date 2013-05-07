package view;

import java.util.ArrayList;

public class GladeView extends View {
	
	// TODO: Valszeg csak a mezoket kellene eltarolni, vagy azokat meg kulon
	private ArrayList<View> views = new ArrayList();
	
	public void addView(View view) {
		views.add(view);
	}
	
	public ArrayList<View> getViews() {
		return views;
	}
}
