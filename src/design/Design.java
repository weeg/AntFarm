package design;

public class Design {

	/**
	 * A grafikus felulet main fuggvenye.
	 * @param args a szokasos argumentumok.
	 */
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Frame frame = new Frame();
                frame.showMenu(false);
            }
        });		
		
	}
}
