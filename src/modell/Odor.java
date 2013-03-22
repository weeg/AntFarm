package modell;

import skeleton.Logger;

public abstract class Odor {
	
	/**
	 * A szag intenzitasa.
	 */
	protected int intensity;
	
	/**
	 * A mezo, amihez tartozik.
	 */
	protected Field position;
	
	/**
	 * Visszaadja a szag intenzitasat.
	 * @return intezitás.
	 */
	public int getIntensity() {
		Logger.enter(this, "getIntensity");
		Logger.exit(this);
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
	 * Beállítja az uj poziciot.
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
		Logger.enter(this, "setIntensity", "i:int");
		this.intensity = i;
		Logger.exit(this);
	}
	/**
	 * Semlegesites, a kulonbozo leszarmazottak fogjak implementalni. 
	 */
	public abstract boolean neutralize();

}
