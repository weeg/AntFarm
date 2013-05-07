package view.pc;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import view.FieldView;
import view.Point;

public class PcFieldView extends FieldView {
	
	public void redraw(Graphics2D g) {
		
		int beginX = point.getX() * width;
		int beginY = point.getY() * height;
		
		Line2D line = new Line2D.Double(beginX, beginY, beginX + width, beginY);
        g.setColor(Color.blue);
        g.setStroke(new BasicStroke(1));
        g.draw(line);
        
        this.changed = false;
	}
}
