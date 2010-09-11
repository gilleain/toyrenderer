package visitor;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import element.Connection;
import element.ElementGroup;
import element.IRenderingElement;
import element.TextBox;

public class OnePassVisitor implements IRenderingVisitor {
    
    public Graphics2D g;
    
    public AffineTransform transform;
    
    public OnePassVisitor(Graphics2D g, AffineTransform transform) {
        this.g = g;
        this.transform = transform;
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

    private Point2D transformPoint(double centerX, double centerY) {
        Point2D ptSrc = new Point2D.Double(centerX, centerY);
        return transform.transform(ptSrc, null);
    }
    
    public void visit(Connection connection) {
        double x1 = connection.x1;
        double y1 = connection.y1;
        double x2 = connection.x2;
        double y2 = connection.y2;
        Point2D pA = transformPoint(x1, y1);
        Point2D pB = transformPoint(x2, y2);
        g.drawLine(
                (int)pA.getX(), (int)pA.getY(), (int)pB.getX(), (int)pB.getY());
    }
    
    public void visit(TextBox box) {
        FontMetrics fm = g.getFontMetrics();
        Point2D p = transformPoint(box.centerX, box.centerY);
        
        Rectangle2D textBounds = fm.getStringBounds(box.text, g);
        int boundsX = (int)(p.getX() - (textBounds.getWidth() / 2.0));
        int boundsY = (int)(p.getY() - (textBounds.getHeight() / 2.0));
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
