package client;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import model.Molecule;

public class MainFrame extends JFrame implements MouseWheelListener {
    
    public DiagramPanel panel;
    
    public MainFrame() {
        super("MainFrame");
        
        panel = new DiagramPanel(); 
        add(panel);
        makeModel();
        pack();
        setVisible(true);
        addMouseWheelListener(this);
    }
    
    public void makeModel() {
        panel.molecule = new Molecule();
        panel.molecule.addAtom("A", 1, 3);
        panel.molecule.addAtom("B", 3, 3);
        panel.molecule.addAtom("C", 5, 5);
        panel.molecule.addAtom("D", 1, 2);
        panel.molecule.bond(0, 1);
        panel.molecule.bond(1, 2);
        panel.molecule.bond(2, 3);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() > 0) {
            panel.setZoom(panel.getZoom() * 0.9);
        } else {
            panel.setZoom(panel.getZoom() * 1.1);
        }
        System.out.println("zoom now " + panel.getZoom());
        panel.repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
