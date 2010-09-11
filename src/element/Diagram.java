package element;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import render.RendererModel;
import visitor.BoundsCalculationVisitor;
import visitor.DirectDrawVisitor;
import visitor.IRenderingVisitor;
import visitor.OnePassVisitor;

public class Diagram implements IRenderingElement {
    
    public AffineTransform transform;
    
    public ElementGroup root;
    
    public RendererModel model;
    
    public Diagram(RendererModel model) {
        this.model = model;
        root = new ElementGroup();
    }
    
    @Override
    public void accept(IRenderingVisitor visitor) {
        root.accept(visitor);
    }

    public void paint(Graphics2D g, Rectangle2D canvas) {
        if (root.children.size() == 0) return;
        setupTransform(canvas);
//        System.out.println(transform);
//        root.accept(new OnePassVisitor(g, transform));
        root.accept(new DirectDrawVisitor(g, transform));
    }
    
    private void setupTransform(Rectangle2D canvas) {
        BoundsCalculationVisitor boundsVisitor = new BoundsCalculationVisitor(); 
        root.accept(boundsVisitor);
        Rectangle2D bounds = boundsVisitor.getBounds();
        double boundsX = bounds.getCenterX();
        double boundsY = bounds.getCenterY();
        double drawX = canvas.getCenterX();
        double drawY = canvas.getCenterY();
        
        model.scale = Math.min(canvas.getWidth()  / bounds.getWidth(),
                               canvas.getHeight() / bounds.getHeight());
//        System.out.println("setting scale " + model.scale);
        
        transform = new AffineTransform();
        transform.translate(drawX, drawY);
        transform.scale(model.scale, model.scale);
        transform.scale(model.zoom, model.zoom);
        transform.translate(-boundsX, -boundsY);
    }

}
