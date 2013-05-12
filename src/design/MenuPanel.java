package design;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	
	/**
	 * Hatterkep
	 */
	private Image bg = new ImageIcon("src/resources/hatter.jpg").getImage();
	
	/**
	 * Szines gombok letrehozasa
	 */
	class ImageButton extends JButton {

	    Image image = null;
	    ImageObserver imageObserver;
	    
	    // Szinek definialasa
	    Color backgroundColor = new Color(9, 61, 23);
		Color fontColor       = new Color(159, 175, 7);
		Color borderColor     = new Color(96, 107, 4);
		Color focusColor      = new Color(15, 102, 39);
		Font font             = new Font("monospaced", Font.BOLD, 30);
	    
	    
	    ImageButton(String title, String fileName) {
            super(title);
            
            if (!fileName.equals("")) {
	            ImageIcon icon = new ImageIcon(fileName);
	            image          = icon.getImage();
	            imageObserver = icon.getImageObserver();
            }
            
            setBorder(BorderFactory.createLineBorder(borderColor, 4));
    		setBackground(backgroundColor);
    		setForeground(fontColor); 
    		setFont(font);
    		setFocusPainted(false);
    		
            this.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(focusColor);
                }
                
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(backgroundColor);
                }
            });
        }
	    
    	public void paint( Graphics g ) {
            super.paint( g );
            
            if (image != null) {
            	g.drawImage(image,  0 , 0 , getWidth() , getHeight() , imageObserver);
            }
        }
    }
	
	public MenuPanel(Frame f, boolean fg) {
		
		frame = f;
		fromGame = fg;
		
		setLayout(null);
		
		JButton startButton = new ImageButton("Start","");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.showGame(false);
			}
		});

	    startButton.setBounds(220, 150, 200, 50);
		add(startButton);
		
		// Ha a jatek mar fut 
		if (fromGame) {
			ImageButton  continueButton = new ImageButton("Continue", "");
			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.showGame(true);
				}
			});
			continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			continueButton.setBounds(220, 270, 200, 50);
			add(continueButton);
		}
		
		ImageButton  quitButton = new ImageButton ("Quit", "");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.setBounds(220, 210, 200, 50);
		add(quitButton);
		
	}
	
	
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
