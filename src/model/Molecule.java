package model;

import java.util.ArrayList;
import java.util.List;

public class Molecule {
    
    public List<Atom> atoms;
    
    public List<Bond> bonds;
    
    public Molecule() {
        atoms = new ArrayList<Atom>();
        bonds = new ArrayList<Bond>();
    }
    
    public void addAtom(String elementSymbol, double x, double y) {
        atoms.add(new Atom(x, y, elementSymbol));
    }
    
    public void bond(int indexA, int indexB) {
        bonds.add(new Bond(atoms.get(indexA), atoms.get(indexB)));
    }

}
