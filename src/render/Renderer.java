package render;

import element.Diagram;
import generator.IGenerator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.List;

import model.Atom;
import model.Molecule;

public class Renderer {
    
    public FontManager fontManager;
    
    public RendererModel model;
    
    public List<IGenerator> generators;
    
    public Renderer(List<IGenerator> generators, FontManager fontManager) {
        this.fontManager = fontManager;
        this.generators = generators;
        this.model = new RendererModel();
    }
    
    public void paint(Graphics2D g, Molecule molecule, Rectangle2D canvas) {
        Diagram diagram = new Diagram(model);
        Rectangle2D bounds = getBounds(molecule);
        AffineTransform transform = createTransform(canvas, bounds);
        for (IGenerator generator : generators) {
            diagram.root.add(generator.generate(molecule, transform));
        }
        diagram.paint(g, canvas);
    }
    
    private Rectangle2D getBounds(Molecule molecule) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = 0.0;
        double maxY = 0.0;
        for (Atom atom : molecule.atoms) {
            if (atom.x < minX) minX = atom.x;
            if (atom.y < minY) minY = atom.y;
            if (atom.x > maxX) maxX = atom.x;
            if (atom.y > maxY) maxY = atom.y;
        }
        double w = Math.abs(maxX - minX);
        double h = Math.abs(maxY - minY);
        return new Rectangle2D.Double(minX, minY, w, h); 
    }
    
    private AffineTransform createTransform(Rectangle2D canvas, Rectangle2D bounds) {
        
        double boundsX = bounds.getCenterX();
        double boundsY = bounds.getCenterY();
        double drawX = canvas.getCenterX();
        double drawY = canvas.getCenterY();
        
        model.scale = Math.min(canvas.getWidth()  / bounds.getWidth(),
                               canvas.getHeight() / bounds.getHeight());
//        System.out.println("setting scale " + model.scale);
        
        AffineTransform transform = new AffineTransform();
        transform.translate(drawX, drawY);
        transform.scale(model.scale, model.scale);
        transform.scale(model.zoom, model.zoom);
        transform.translate(-boundsX, -boundsY);
        return transform;
    }

}
