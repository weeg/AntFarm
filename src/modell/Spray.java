package modell;

import skeleton.Logger;

public abstract class Spray {
	
	/**
	 * A rendelekezesre allo fujasok szama.
	 */
	private int quantity;
	
	/**
	 * A hatosugar merete.
	 */
	protected int radius;
	
	/**
	 * A tisztas objektum.
	 */
	protected Glade glade;
	
	/**
	 * A parameterkent atadott mezo kore fujas.
	 * @param center A kozeppontban levo mezo.
	 */
	public abstract void use(Field center);
	
	/**
	 * Visszaadja a meg rendelekezesre allo fujasok szaaát.
	 * @return A fujasok szama.
	 */
	public int getQuantity() {
		Logger.enter(this, "getQuantity");
		Logger.exit(this);
		return quantity;
	}

	/**
	 * Beallitja fujasok szamat.
	 * @param quantity A fujasok szama.
	 */
	public void setQuantity(int quantity) {
		Logger.enter(this, "setQuantity", "quantity:int");
		this.quantity = quantity;
		Logger.exit(this);
	}

	/**
	 * Visszaadja a hatosugar mertetet.
	 * @return A hatósugár
	 */
	public int getRadius() {
		Logger.enter(this, "getRadius");
		Logger.exit(this);
		return radius;
	}

	/**
	 * Beallitja a hatosugarat.
	 * @param radius A hatosugar merete.
	 */
	public void setRadius(int radius) {
		Logger.enter(this, "setRadius", "radius:int");
		this.radius = radius;
		Logger.exit(this);
	}
}
