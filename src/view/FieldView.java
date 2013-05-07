package view;

import java.awt.Point;
import java.util.ArrayList;

import modell.Field;



public class FieldView extends View {

	private Field modell;
	private ArrayList<View> views = new ArrayList();
	protected Point point;
	
	// Egy mezo szlessege es magassaga pixelben
	protected int width  = 10;
	protected int height = 10;
	
	/**
	 * Mezo kozeppontjanak megadasa
	 * @param point
	 */
	public void setPosition(Point point) {
		this.point = point;
	}
	
	public Point getPosition() {
		return point;
	}
	
	public void setModell(Field modell) {
		this.modell = modell;
	}
}
