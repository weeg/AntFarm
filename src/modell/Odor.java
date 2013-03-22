package modell;

import skeleton.Logger;

public abstract class Odor {
	
	/**
	 * A szag intenzitása.
	 */
	protected int intensity;
	
	/**
	 * A mezõ, amihez tartozik.
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
<<<<<<< HEAD
	 * Semlegesítés, a különbözõ leszármazottak fogják implementálni.
=======
	 * SemlegesÃ­tÃ©s, a kÃ¼lÃ¶nbÃ¶zÅ‘ leszÃ¡rmazottak fogjÃ¡k implementÃ¡lni.
	 * 
>>>>>>> neutralize nem jÃ³
	 */
	public abstract boolean neutralize();

}
