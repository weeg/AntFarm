package modell;

public class Anteater extends Entity implements Active {
	
	// A hangyaszsun iranya
	private Direction direction;
	
	// Az aktualis palyaralepes soran mar megevett hangyak szama
	private int eatenAnts;
	
	// Visszamarado ido, amig pihen az ujabb palyara lepes elott
	private int timeToRest;
	
	// Pihen-e a hangyaszsun
	private boolean isResting;
	
	// Lekuzdhetetlen akadalyba utkozott-e
	private boolean blocked;
	
	// Visszaadja, hogy ehes-e a hangyaszsun
	public boolean isHungry() {
		return true;
	}
	
	// Pihenni kuldi a hangyaszsunt
	public void goRest() {
		
	}
	
	// Utkozes egy hangyaval
	public void collide(Ant ant) {
		
	}

	// Megevett hangyak szamanak novelese
	public void increaseEatenAnts() {
		
	}
	
	// A hangyaszsun blokkolasa
	public void block() {
		
	}
	
	public void animate(Glade glade) {
		
	}

	
}
