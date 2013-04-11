package modell;

public abstract class Spray {
	
	/**
	 * A rendelekezesre allo fujasok szama.
	 */
	protected int quantity;
	
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
		return quantity;
	}

	/**
	 * Beallitja fujasok szamat.
	 * @param quantity A fujasok szama.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Visszaadja a hatosugar mertetet.
	 * @return A hatosugar
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Beallitja a hatosugarat.
	 * @param radius A hatosugar merete.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
