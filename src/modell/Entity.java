package modell;

public abstract class Entity {
	
	// A mezo, amin az entitas van
	private Field position;
	
	public Field getPosition() {
		return position;
	}
	
	public void setPosition(Field position) {
		this.position = position;
	}
	
	// Utkozes egy hangyaval
	public void collide(Ant ant) {		
		// A leszarmazottak fogjak implementalni szukseg eseten
	}
	
	// Utkozes egy hangyaszsunnel
	public void collide(Anteater anteater) {
		// A leszarmazottak fogjak implementalni szukseg eseten
	}
	
	

}
