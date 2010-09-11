package element;

import java.util.ArrayList;
import java.util.List;

import visitor.IRenderingVisitor;

public class ElementGroup implements IRenderingElement {
    
    public List<IRenderingElement> children;
    
    public ElementGroup() {
        children = new ArrayList<IRenderingElement>();
    }
    
    public void add(IRenderingElement element) {
        children.add(element);
    }
    

    @Override
    public void accept(IRenderingVisitor visitor) {
        for (IRenderingElement child : children) {
            child.accept(visitor);
        }
    }
    
}
