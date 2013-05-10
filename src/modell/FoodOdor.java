package modell;

/**
 * Az élelemszag objektum.
 */
public class FoodOdor extends Odor {
	
	public FoodOdor() {	}
	/**
	 * Törli magát a mezõrõl.
	 */
	public void evaporate() {
		position.removeOdor(this);
		position.getView().change();
	}
	
	/**
	 * A semlegesito metodus mivel a szag nem semlegesitheto nem csinal semmit.
	 */
	public void neutralize() {	}
}
