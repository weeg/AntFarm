package design;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Az alap frame a grafikus felulethez.
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private GamePanel gamePanel;
	private MenuPanel menuPanel;
	
	public Frame() {
		super("Ant Farm");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setPreferredSize(new Dimension(640, 740));
	    setResizable(false);

	    pack();
	    setVisible(true);
	}
	
	/**
	 * A menu megjelentitese.
	 * @param fromGame A menu mar jatek kozben jelenik meg, vagy nem.
	 */
	public void showMenu(boolean fromGame) {
		if (gamePanel != null) {
			remove(gamePanel);
		}
		menuPanel = new MenuPanel(this, fromGame);
		add(menuPanel, BorderLayout.CENTER);
		validate();
	}
	
	/**
	 * A jatekter megjelenítese.
	 * @param continueGame A jatek koraban el volt inditva, vagy sem.
	 */
	public void showGame(boolean continueGame) {
		if (menuPanel != null) {
			remove(menuPanel);			
		}
		if (!continueGame) {
			gamePanel = new GamePanel(this);
		}
	    add(gamePanel, BorderLayout.CENTER);
	    validate();
	    
	    gamePanel.setFocusable(true);
	    gamePanel.requestFocusInWindow();	    
	    
	    if (continueGame) {
	    	gamePanel.resume();
	    } else {
	    	gamePanel.play();
	    }

	}
}
