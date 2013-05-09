package design;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import modell.Glade;
import modell.Field;

import modell.Spray;
import view.FieldView;
import view.GladeView;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private Glade glade;
	
	private Timer timer;
	
	private Frame frame;
	
	private double time = 0;
	private int bestTime = 0;
	private static int delay = 512;
	private static int originalDelay = 512;
	private JLabel timeLabel;
	private JLabel speedLabel;
	private JLabel bestTimeLabel;
	
	private JLabel odorSprayLabel;
	private JLabel killerSprayLabel;
	
	public GamePanel(Frame f) {
		this.frame = f;
		
		setLayout(null);
		
		timeLabel = new JLabel("Time: " + time);
		timeLabel.setBounds(5, 0, 300, 30);
		timeLabel.setForeground(Color.WHITE);
		add(timeLabel);
		
		speedLabel = new JLabel("");
		speedLabel.setBounds(frame.getWidth() - 160, 0, 300, 30);
		speedLabel.setForeground(Color.WHITE);
		add(speedLabel);
		
		bestTimeLabel = new JLabel("Best time: " + loadBestTime());
		bestTimeLabel.setBounds(5, 20, 300, 30);
		bestTimeLabel.setForeground(Color.WHITE);
		add(bestTimeLabel);
		
		odorSprayLabel = new JLabel("");
		odorSprayLabel.setBounds(frame.getWidth() - 160, frame.getHeight() - 90, 300, 30);
		odorSprayLabel.setForeground(Color.WHITE);
		add(odorSprayLabel);
		
		killerSprayLabel = new JLabel("");
		killerSprayLabel.setBounds(frame.getWidth() - 160, frame.getHeight() - 60, 300, 30);
		killerSprayLabel.setForeground(Color.WHITE);
		add(killerSprayLabel);
		
        addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent key) {
				if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
					timer.stop();
					frame.showMenu(true);
				} else if (key.getKeyCode() == KeyEvent.VK_UP) {
					if (delay > 2) {
						delay /= 2;
						timer.setDelay(delay);
					}
				} else if (key.getKeyCode() == KeyEvent.VK_DOWN) {
					if (delay < 1024) {	
						delay *= 2;
						timer.setDelay(delay);
					}
				}
			}
		});
        
        addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent click) {
				Point p = click.getPoint();
				Field center = null;
				for (Field field : glade.getFields()) {
					FieldView fieldView = (FieldView)field.getView();
					if (fieldView.getPosition().distance(p) < fieldView.getSize()) {
						center = field;
						break;
					}
				}
				if (center != null) {
					Spray spray = null;
					if (click.getButton() == MouseEvent.BUTTON1) {
						spray = glade.getAntKillerSpray();
					} else if (click.getButton() == MouseEvent.BUTTON3) {
						spray = glade.getAntOdorNeutralizerSpray();
					}
					if (spray != null) {
						spray.use(center);
						glade.getView().redraw((Graphics2D)getGraphics());
						setLabels();
					}
				}											
			}
		});
        
        // Timer
        ActionListener tickListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	
            	time = time + delay * getSpeed() / 1000.0;
            	setLabels();
            	
                glade.tick();
                glade.getView().redraw((Graphics2D)getGraphics());
                if (glade.IsGameOver()) {
                	JOptionPane.showMessageDialog(frame, "The game is over.");
                	timer.stop();
                	if (loadBestTime() < (int)time) {
                		saveTime();
                	}
                	frame.showMenu(false);
                }
            }
        };
        timer = new Timer(delay, tickListener);
        
	}
    
    public void play() {
    	glade = new Glade();
    	GladeView gladeView = new GladeView();
        glade.setView(gladeView);       
        glade.start();
        
        setLabels();
        
        timer.start();
        
    }  
    
    public void resume() {
    	GladeView gladeView = (GladeView)glade.getView();
    	gladeView.redrawAll((Graphics2D)getGraphics());    
        
    	timer.start();
    }  
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
        GladeView gladeView = (GladeView)glade.getView();
        gladeView.redrawAll((Graphics2D)g);
    } 
    
    private void setLabels() {
    	timeLabel.setText("Time: " + (int)time);
    	bestTimeLabel.setText("Best time: " + bestTime);
    	
    	if (glade.getAntOdorNeutralizerSpray() != null) {
    		odorSprayLabel.setText("Odor neutralizer spray: " + 
        			glade.getAntOdorNeutralizerSpray().getQuantity());
    	}
    	
    	if (glade.getAntKillerSpray() != null) {
    		killerSprayLabel.setText("Ant killer spray: " + 
        			glade.getAntKillerSpray().getQuantity());
    	}
    	
    	int speed = getSpeed();	
    	if (speed == 1) {
    		speedLabel.setText("");
    	} else {
    		speedLabel.setText("Speed: "+speed+"x");
    	}
    	
    	odorSprayLabel.repaint();
    	killerSprayLabel.repaint();
    	bestTimeLabel.repaint();
    	timeLabel.repaint();
    	speedLabel.repaint();
    }
    
    private int getSpeed() {
    	return delay > originalDelay ? (delay / originalDelay) * -1 : (originalDelay / delay);	
    }
    
    private void saveTime() {
    	FileWriter fw;
		try {
			fw = new FileWriter("time.txt");
	    	BufferedWriter out = new BufferedWriter(fw);
	    	out.write(new Integer((int) time).toString());
	    	out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private int loadBestTime() {
    	int best = 0;
    	Scanner scanner;
		try {
			scanner = new Scanner(new File("time.txt"));
			best = scanner.nextInt();
			scanner.close();
		} catch (FileNotFoundException e) {
			
		}
		bestTime = best;
    	return best;
    }
}
