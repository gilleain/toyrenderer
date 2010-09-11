package visitor;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import element.Connection;
import element.ElementGroup;
import element.IRenderingElement;
import element.TextBox;

public class DirectDrawVisitor implements IRenderingVisitor {
    
    public Graphics2D g;
    
    public AffineTransform transform;
    
    public DirectDrawVisitor(Graphics2D g, AffineTransform transform) {
        this.g = g;
        this.transform = transform;
        g.setTransform(transform);
    }

    @Override
    public void visit(IRenderingElement element) {
        if (element instanceof ElementGroup) {
            visit((ElementGroup) element);
        } else if (element instanceof TextBox) {
            visit((TextBox) element);
        } else if (element instanceof Connection) {
            visit((Connection) element);
        }
    }
    
    public void visit(ElementGroup group) {
        group.accept(this);
    }
    
    public void visit(Connection connection) {
        double x1 = connection.x1;
        double y1 = connection.y1;
        double x2 = connection.x2;
        double y2 = connection.y2;
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }
    
    public void visit(TextBox box) {
        FontMetrics fm = g.getFontMetrics();
        
        Rectangle2D textBounds = fm.getStringBounds(box.text, g);
        int boundsX = (int)(box.centerX - (textBounds.getWidth() / 2.0));
        int boundsY = (int)(box.centerY - (textBounds.getHeight() / 2.0));
        int boundsWidth = (int)textBounds.getWidth();
        int boundsHeight = (int)textBounds.getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(boundsX, boundsY, boundsWidth, boundsHeight);
        g.setColor(Color.BLACK);
        g.drawRect(boundsX, boundsY, boundsWidth, boundsHeight);
        
        int baseX = boundsX;
        int baseY = boundsY + fm.getAscent();
        g.drawString(box.text, baseX, baseY);
    }

}
