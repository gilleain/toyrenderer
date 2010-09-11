package model;

public class ModelBuilder {
    
    public static Molecule linearMolecule(double scale) {
        Molecule molecule = new Molecule();
        double x1 = scale;
        double x2 = 3 * scale;
        double y = scale;
        molecule.addAtom("A", x1, y);
        molecule.addAtom("B", x2, y);
        molecule.bond(0, 1);
        
        return molecule;
    }

}
