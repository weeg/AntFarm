package modell;

import skeleton.Logger;

public class Odor {
	
	/**
	 * A szag intenzitása.
	 */
	protected int intensity;
	
	/**
	 * A mező, amihez tartozik.
	 */
	protected Field position;
	
	/**
	 * Visszaadja a szag intenzitását.
	 * @return intezitás.
	 */
	public int getIntensity() {
		Logger.enter(this, "getIntensity");
		Logger.exit(this);
		return intensity;
	}
	
	/**
	 * Visszadja a poziciót.
	 * @return a pozició.
	 */
	public Field getPosition() {
		return position;
	}
	
	/**
	 * Beállítja az új poziciót.
	 * @param position Az új pozició.
	 */
	public void setPosition(Field position) {
		this.position = position;
	}
	
	/**
	 * Az intezitás beállítása.
	 * @param i Az intezitás értéke.
	 */
	public void setIntensity(int i) {
		Logger.enter(this, "setIntensity", "i:int");
		this.intensity = i;
		Logger.exit(this);
	}
	/**
	 * Semlegesítés, a különböző leszármazottak fogják implementálni.
	 */
	public void neutralize() {
		
	}

}
