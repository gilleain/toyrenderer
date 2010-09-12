package generator;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import model.Bond;
import model.Molecule;
import element.Connection;
import element.ElementGroup;
import element.IRenderingElement;

public class BondGenerator extends AbstractGenerator implements IGenerator<Molecule> {

    @Override
    public IRenderingElement generate(Molecule molecule) {
        ElementGroup group = new ElementGroup();
        for (Bond bond : molecule.bonds) {
            group.children.add(new Connection(
                    bond.atomA.x, bond.atomA.y, bond.atomB.x, bond.atomB.y));
        }
        return group;
    }

    @Override
    public IRenderingElement generate(Molecule molecule, AffineTransform transform) {
        ElementGroup group = new ElementGroup();
        for (Bond bond : molecule.bonds) {
            Point2D p1 = transformPoint(bond.atomA.x, bond.atomA.y, transform);
            Point2D p2 = transformPoint(bond.atomB.x, bond.atomB.y, transform);
            group.children.add(
                    new Connection(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
        }
        return group;
    }

}
