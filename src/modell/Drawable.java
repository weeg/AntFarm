package modell;

import view.View;
/**
 * Inteface a rajzolhato elemeknek.
 */
public interface Drawable {
	/**
	 * A renderelo nezet beallitása.
	 * @param view A renderelo nezet.
	 */
	public void setView(View view);
	/**
	 * Visszaadja a renderelo nezetet.
	 * @return A renderelo nezet. 
	 */
	public View getView();
}
