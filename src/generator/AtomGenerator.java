package generator;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import element.ElementGroup;
import element.IRenderingElement;
import element.TextBox;
import model.Atom;
import model.Molecule;

public class AtomGenerator extends AbstractGenerator implements IGenerator<Molecule> {
    
    @Override
    public IRenderingElement generate(Molecule molecule) {
        ElementGroup group = new ElementGroup();
        for (Atom atom : molecule.atoms) {
            group.children.add(new TextBox(atom.elementSymbol, atom.x, atom.y));
        }
        return group;
    }

    @Override
    public IRenderingElement generate(Molecule molecule, AffineTransform transform) {
        ElementGroup group = new ElementGroup();
        for (Atom atom : molecule.atoms) {
            Point2D p = transformPoint(atom.x, atom.y, transform);
            group.children.add(new TextBox(atom.elementSymbol, p.getX(), p.getY()));
        }
        return group;
    }

}
