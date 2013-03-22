package modell;

import skeleton.Logger;

public abstract class Spray {
	
	/**
	 * A rendelekezésre álló fújások száma.
	 */
	private int quantity;
	
	/**
	 * A hatósugár mérete.
	 */
	protected int radius;
	
	protected Glade glade;
	
	/**
	 * A paraméterként átadott mezõ köré fújás.
	 * @param center
	 */
	public abstract void use(Field center);
	
	/**
	 * Visszaadja a még rendelekezésre álló fújások számát.
	 * @return A fújások száma.
	 */
	public int getQuantity() {
		Logger.enter(this, "getQuantity");
		Logger.exit(this);
		return quantity;
	}

	/**
	 * Beállítja fújások számát.
	 * @param quantity A fújások száma.
	 */
	public void setQuantity(int quantity) {
		Logger.enter(this, "setQuantity", "quantity:int");
		this.quantity = quantity;
		Logger.exit(this);
	}

	/**
	 * Visszaadja a hatósugár mértetét.
	 * @return A hatósugár
	 */
	public int getRadius() {
		Logger.enter(this, "getRadius");
		Logger.exit(this);
		return radius;
	}

	/**
	 * Beállítja a hatósugarat.
	 * @param radius A hatósugár mérete.
	 */
	public void setRadius(int radius) {
		Logger.enter(this, "setRadius", "radius:int");
		this.radius = radius;
		Logger.exit(this);
	}
}
