package design;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import modell.Glade;
import modell.Field;
import view.FieldView;
import view.GladeView;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private Glade glade;
	
	private Timer timer;
	
	private Frame frame;
	
	public GamePanel(Frame f) {
		this.frame = f;

        addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) { }
			public void keyReleased(KeyEvent arg0) { }
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
					timer.stop();
					frame.showMenu(true);
				}
			}
		});
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	Field selected = ((GladeView)glade.getView()).getField(e.getX(), e.getY());
            	if (e.getButton() == MouseEvent.BUTTON1) {
                    glade.aks.use(selected);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    glade.aons.use(selected);
                }
            }
        });
        
        // Timer
        ActionListener tickListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                glade.tick();
                glade.getView().redraw((Graphics2D)getGraphics());
                if (glade.IsGameOver()) {
                	timer.stop();
                	frame.showMenu(false);
                }
            }
        };
        timer = new Timer(1000, tickListener);
	}
    
    public void play() {
    	glade = new Glade();
    	glade.start();
        glade.setView(new GladeView());
        
        timer.start();
    }  
    
    public void resume() {
        for (Field field : glade.getFields()) {
			field.getView().change();
		}
        glade.getView().redraw((Graphics2D)getGraphics());    
        
    	timer.start();
    }  
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Field field : glade.getFields()) {
			field.getView().change();
		}
        glade.getView().redraw((Graphics2D)g);    
    } 
    
}
