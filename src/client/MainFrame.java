package client;

import java.awt.GridLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import model.ModelBuilder;

public class MainFrame extends JFrame implements MouseWheelListener {
    
    public DiagramPanel panelA;
    
    public DiagramPanel panelB;
    
    public MainFrame() {
        super("MainFrame");
        
        setLayout(new GridLayout(1, 2));
        
        panelA = new DiagramPanel();
        add(panelA);
        panelA.molecule = ModelBuilder.linearMolecule(100);
        
        panelB = new DiagramPanel(); 
        add(panelB);
        panelB.molecule = ModelBuilder.linearMolecule(10);
        
        pack();
        setVisible(true);
        
        addMouseWheelListener(this);
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() > 0) {
            panelA.setZoom(panelA.getZoom() * 0.9);
            panelB.setZoom(panelB.getZoom() * 0.9);
        } else {
            panelA.setZoom(panelA.getZoom() * 1.1);
            panelB.setZoom(panelB.getZoom() * 1.1);
        }
        System.out.println("zoom A now " + panelA.getZoom());
        System.out.println("zoom B now " + panelB.getZoom());
        panelA.repaint();
        panelB.repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }

   
}
