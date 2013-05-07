package design;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	public void showMenu(boolean fromGame) {
		if (gamePanel != null) {
			remove(gamePanel);
		}
		menuPanel = new MenuPanel(this, fromGame);
		add(menuPanel, BorderLayout.CENTER);
		validate();
	}
	
	public void showGame(boolean continueGame) {
		if (menuPanel != null) {
			remove(menuPanel);
		}
		if (!continueGame) {
			gamePanel = new GamePanel(this);
		}
	    add(gamePanel, BorderLayout.CENTER);
	    gamePanel.setFocusable(true);
	    gamePanel.requestFocusInWindow();
	    validate();
	    if (continueGame) {
	    	gamePanel.resume();
	    } else {
	    	gamePanel.play();
	    }
	}

}
