package modell;

public abstract class Odor {
	
	/** A szag intenzitasa */
	protected int intensity;
	
	/** A mezo, amihez tartozik */
	protected Field position;
	
	/**
	 * Visszaadja a szag intenzitasat.
	 * @return intezitas.
	 */
	public int getIntensity() {
		return intensity;
	}
	
	/**
	 * Visszadja a poziciot.
	 * @return a pozicio.
	 */
	public Field getPosition() {
		return position;
	}
	
	/**
	 * Beallitja az uj poziciot.
	 * @param position Az uj pozicio.
	 */
	public void setPosition(Field position) {
		this.position = position;
	}
	
	/**
	 * Az intezitas beallitasa.
	 * @param i Az intezitas erteke.
	 */
	public void setIntensity(int i) {
		this.intensity = i;
	}
	/**
	 * Semlegesites, a kulonbozo leszarmazottak fogjak implementalni. 
	 */
	public abstract void neutralize();

}
