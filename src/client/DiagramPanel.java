package client;

import generator.AtomGenerator;
import generator.BondGenerator;
import generator.IGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Molecule;

import render.FontManager;
import render.Renderer;

public class DiagramPanel extends JPanel {

    public Renderer renderer;
    
    public Molecule molecule;
    
    public List<IGenerator> generators;
   
    public DiagramPanel() {
        generators = new ArrayList<IGenerator>();
        generators.add(new BondGenerator());
        generators.add(new AtomGenerator());
        renderer = new Renderer(generators, new FontManager());
        setPreferredSize(new Dimension(500,300));
        setBackground(Color.WHITE);
    }
    
    public void setZoom(double zoom) {
        renderer.model.zoom = zoom;
    }
    
    public double getZoom() {
        return renderer.model.zoom;
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        if (molecule != null) {
            renderer.paint((Graphics2D)g, molecule, getBounds());
        }
    }
    
}
