package modell;

public enum Direction {
	N, NE, SE, S, SW, NW;
	
	public static Direction parseInt(int i) {
		switch (i) {
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
	
	public static Direction getLeftDirection(Direction d) {
		switch (d) {
		case N:
			return NW;
		case NE:
			return N;
		case SE:
			return NE;
		case S:
			return SE;
		case SW:
			return S;
		case NW:
			return SW;
		default:
			return null;
		}
	}
	
	public static Direction getRightDirection(Direction d) {
		switch (d) {
		case N:
			return NE;
		case NE:
			return SE;
		case SE:
			return S;
		case S:
			return SW;
		case SW:
			return NW;
		case NW:
			return N;
		default:
			return null;
		}
	}
}
