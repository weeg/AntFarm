package design;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A menu kirajzolasaert felelos panel.
 */
@SuppressWarnings("serial")
public class MenuPanel extends JPanel {
	
	/**
	 *  A fokeret.
	 */
	private Frame frame;
	/**
	 * Tarolja, hogy jatek kozben lett-e megjelenitve a menu.
	 */
	private boolean fromGame;
	
	public MenuPanel(Frame f, boolean fg) {
		
		frame = f;
		fromGame = fg;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.showGame(false);
			}
		});
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(0, 150)));
		add(startButton);
		
		// Ha a jatek mar fut 
		if (fromGame) {
			JButton continueButton = new JButton("Continue");
			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.showGame(true);
				}
			});
			continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(Box.createRigidArea(new Dimension(0, 50)));
			add(continueButton);
		}
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(0, 50)));
		add(quitButton);
		
	}
}
