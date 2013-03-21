package modell;

public abstract class Spray {
	
	// A meg rendelekezesre allo fujasok szama
	private int quantity;
	
	// A hatosugar merete
	private int radius;
	
	// A parameterkent atadott mezo kore fujas
	public abstract void use(Field center);
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
