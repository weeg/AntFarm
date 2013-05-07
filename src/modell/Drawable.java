package modell;

import view.View;

public interface Drawable {
	public void attach(View view);
	
	public void detach(View view);
	
	public View getView();
}
