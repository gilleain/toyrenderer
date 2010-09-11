package visitor;

import java.awt.geom.Rectangle2D;

import element.Connection;
import element.ElementGroup;
import element.IRenderingElement;
import element.TextBox;

public class BoundsCalculationVisitor implements IRenderingVisitor {
    
    public double minX = Double.MAX_VALUE;
    public double minY = Double.MAX_VALUE;
    public double maxX = 0.0;
    public double maxY = 0.0;
    
    public BoundsCalculationVisitor() {
        minX = Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        maxX = 0.0;
        maxY = 0.0;
    }
    
    public Rectangle2D getBounds() {
        double w = Math.abs(maxX - minX);
        double h = Math.abs(maxY - minY);
        return new Rectangle2D.Double(minX, minY, w, h); 
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
    
    public void visit(Connection connection) {
       
    }
    
    public void visit(TextBox box) {
        if (box.centerX < minX) minX = box.centerX;
        if (box.centerY < minY) minY = box.centerY;
        if (box.centerX > maxX) maxX = box.centerX;
        if (box.centerY > maxY) maxY = box.centerY; 
    }


}
