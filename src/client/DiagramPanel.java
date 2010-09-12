package client;

import generator.AtomGenerator;
import generator.BondGenerator;
import generator.IGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import model.Molecule;

import render.FontManager;
import render.Renderer;

public class DiagramPanel extends JPanel implements MouseListener { 

    public Renderer renderer;
    
    public Molecule molecule;
    
    public List<IGenerator> generators;
   
    public DiagramPanel() {
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        
        generators = new ArrayList<IGenerator>();
        generators.add(new BondGenerator());
        generators.add(new AtomGenerator());
        renderer = new Renderer(generators, new FontManager());
        setPreferredSize(new Dimension(500,300));
        setBackground(Color.WHITE);
        addMouseListener(this);
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
            Rectangle bounds = getBounds();
            Graphics2D g2 = (Graphics2D)g; 
            AffineTransform original = g2.getTransform();
            renderer.paint(g2, molecule, 
                    new Rectangle(0, 0, bounds.width, bounds.height));
            String details = String.format(
                    "Zoom = %2.2f, Scale = %2.2f",
                    renderer.model.zoom, renderer.model.scale);
            g2.setTransform(original);
            g2.setColor(Color.BLACK);
            System.out.println(details);
            g2.drawString(details, 10, bounds.height - 20);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        renderer.drawCenter = new Point2D.Double(p.x, p.y);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
