package generator;

import model.Bond;
import model.Molecule;
import element.Connection;
import element.ElementGroup;
import element.IRenderingElement;

public class BondGenerator implements IGenerator<Molecule> {

    @Override
    public IRenderingElement generate(Molecule molecule) {
        ElementGroup group = new ElementGroup();
        for (Bond bond : molecule.bonds) {
            group.children.add(new Connection(
                    bond.atomA.x, bond.atomA.y, bond.atomB.x, bond.atomB.y));
        }
        return group;
    }

}
