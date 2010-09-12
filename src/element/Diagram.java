package element;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import render.RendererModel;
import visitor.IRenderingVisitor;
import visitor.ZoomingDrawVisitor;

public class Diagram implements IRenderingElement {
    
    public ElementGroup root;
    
    public RendererModel model;
    
    public Point2D drawCenter;
    
    public Diagram(RendererModel model, Point2D drawCenter) {
        this.model = model;
        root = new ElementGroup();
        this.drawCenter = drawCenter; 
    }
    
    @Override
    public void accept(IRenderingVisitor visitor) {
        root.accept(visitor);
    }

    public void paint(Graphics2D g, Rectangle2D canvas) {
        if (root.children.size() == 0) return;
        root.accept(new ZoomingDrawVisitor(
                g, model.zoom, drawCenter.getX(), drawCenter.getY()));
    }

}
