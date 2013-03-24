package modell;

/**
 * Az irány enum. Ez segit a koordinacioban.
 */
public enum Direction {
	N(0), NE(1), SE(2), S(3), SW(4), NW(5);
	
	/**
	 * Egy szamertek a 6 iranyhoz (0-tol 5-ig).
	 */
	private final int index;   
	
	/**
	 * A Direction konstruktora.
	 * @param index Az irany szama.
	 */
	Direction(int index) {
        this.index = index;
    }
	
	/**
	 * Visszaadja az irany szamat.
	 * @return Az irany szama.
	 */
    public int index() { 
        return index; 
    }
	
    /**
     * A forgatatast vegzo metodus.
     * @param i A forgatas merteke, ha negativ akkor az oramutato jarassaval ellentetes a forgatas
     * @return Az uj irany.
     */
	public Direction turn(int i) {
		int idx = (i + this.index) % 6;
		if (idx < 0) {
		    idx += 6;
		}
		switch (idx) {
			case 0:
				return N;			
			case 1:
				return NE;
			case 2:
				return SE;
			case 3:
				return S;
			case 4:
				return SW;
			case 5:
				return NW;
			default:
				return N;
		}
	}
}
