package modell;

public abstract class Spray {
	
	// A meg rendelekezesre allo fujasok szama
	private int quantity;
	
	// A hatosugar merete
	private int radius;
	
	// A parameterkent atadott mezo kore fujas
	public abstract void use(Field center);
}
