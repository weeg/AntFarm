package modell;

import java.util.ArrayList;

public class Stone extends Barricade {
	
	private int count;
	
	private boolean blocked;
	private boolean killed;
	
	private Direction direction;
	
	public Stone() {
		count = 0;
		blocked = false;
	}
	
	/**
	 *  A kavics eltuntetese
	 */
	public void kill() {
		position.removeEntity(this);
		killed = true;	
		getView().change();
	}
	
	/**
	 * Visszadja a ko iranyat.
	 * @return Az irany.
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * Ko blokkolasa.
	 */
	public void block() {
		blocked = true;
	}
	
	public int getCount() {
		return count;
	}
	
	/**
	 * Utkozes egy hangyaszsunnel. Eltolja a hangyaszsun.
	 * @param ae A hangyassun, amivel utkozik.
	 */
	public void collide(Anteater ae) {
		count = 1;
		direction = ae.getDirection();
		Field target = position.getNeighbour(direction);
		ArrayList<Entity> copy = new ArrayList<Entity>(target.getEntities());
		for(Entity e : copy) {
			e.collide(this);
		}
		
		if (!killed) {
			if (blocked) {
				ae.block();
			} else {
				getView().change();
				position.removeEntity(this);
				target.addEntity(this);
				getView().change();
			}
		}		
	}
	/**
	 * Utkozes egy kaviccsal.
	 * @param stone A kavics, amivel utkozik.
	 */
	public void collide(Stone stone) {
		count = stone.getCount() + 1;
		if (count == 3) {
			stone.block();
			blocked = true;
		} else {
			direction = stone.getDirection();
			Field target = position.getNeighbour(direction);
			ArrayList<Entity> copy = new ArrayList<Entity>(target.getEntities());
			for(Entity e : copy) {
				e.collide(this);
			}
			if (blocked) {
				stone.block();
			} else {
				position.removeEntity(this);
				target.addEntity(this);
			}
		}
	}
}
