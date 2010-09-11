package model;

public class Atom {
    
    public double x;
    
    public double y;
    
    public String elementSymbol;

    public Atom(double x, double y, String elementSymbol) {
        this.x = x;
        this.y = y;
        this.elementSymbol = elementSymbol;
    }
    
    public String toString() {
        return String.format("%s[%2.2f, %2.2f]", elementSymbol, x, y);
    }

}
