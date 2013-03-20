package modell;

import java.util.ArrayList;

public class Ant extends Entity implements Active {

	// Az irany, amerre a hangya megy
	private Direction direction;
	
	// Azok a mezok, amiken vegigment a hangya
	private ArrayList<Field> memory;
	
	// Van-e a hangyanal elelem
	private boolean hasFood;
	
	// Meg van-e mergezve
	private boolean poisened;
	
	// Akadalyba utkozott-e
	private boolean blocked;
	
	// Meg hany korig fog elni (ha mergezett)
	private int TTL;
	
	// Visszaadja, hogy a hangyanal van-e elelem
	public boolean hasFood() {
		return hasFood;
	}
	
	// A hangya megolese
	public void kill() {
		
	}
	
	// A hangya blokkolasa
	public void block() {
		
	}
	
	// A hangya eszik
	public void eat() {
		
	}		
		
	public void animate(Glade glade) {
		// TODO Auto-generated method stub
		
	}
	
	public void collide(Anteater anteater) {
		
	}

	
}
