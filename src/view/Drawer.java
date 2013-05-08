package view;

import java.awt.Graphics2D;

public class Drawer {

	public static void drawField(Graphics2D g, int x, int y, double size, boolean fill) {
		double xstart = -size / 2 + x * 3 * size / 2;    		
		int xpoints[] = { (int)(xstart), 
						  (int)(xstart + size / 2), 
						  (int)(xstart + 3 * size / 2), 
						  (int)(xstart + 2 * size), 
						  (int)(xstart + 3 * size / 2), 
						  (int)(xstart + size / 2), 
						  (int)(xstart) };
		double m = size * Math.sqrt(3) / 2.0;
		double ystart = y * 2 * m + (x % 2) * m;
    	int ypoints[] = { (int)(ystart), 
    					  (int)(ystart + m), 
    					  (int)(ystart + m), 
    					  (int)(ystart), 
    					  (int)(ystart - m), 
    					  (int)(ystart - m), 
    					  (int)(ystart) };

    	if (fill) {
    		g.fillPolygon(xpoints, ypoints, 6);
    	} else {
    		g.drawPolygon(xpoints, ypoints, 6);
    	} 	
	}
}
