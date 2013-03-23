package modell;

public enum Direction {
	N(0), NE(1), SE(2), S(3), SW(4), NW(5);
	
	private final int index;   

	Direction(int index) {
        this.index = index;
    }

    public int index() { 
        return index; 
    }
	
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
