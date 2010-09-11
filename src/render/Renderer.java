package render;

import element.Diagram;
import generator.IGenerator;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

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
        for (IGenerator generator : generators) {
            diagram.root.add(generator.generate(molecule));
        }
        diagram.paint(g, canvas);
    }

}
