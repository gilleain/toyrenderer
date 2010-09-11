package generator;

import element.ElementGroup;
import element.IRenderingElement;
import element.TextBox;
import model.Atom;
import model.Molecule;

public class AtomGenerator implements IGenerator<Molecule> {

    @Override
    public IRenderingElement generate(Molecule molecule) {
        ElementGroup group = new ElementGroup();
        for (Atom atom : molecule.atoms) {
            group.children.add(new TextBox(atom.elementSymbol, atom.x, atom.y));
        }
        return group;
    }

}
