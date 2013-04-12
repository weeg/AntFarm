package modell;

public class Stone extends Barricade {
	private Direction direction;
	public Stone() {}
	
	/**
	 * Visszadja a ko iranyat.
	 * @return Az irany.
	 */
	public Direction getDirection() {
		return direction;
	}
}
