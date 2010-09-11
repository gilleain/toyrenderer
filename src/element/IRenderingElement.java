package element;

import visitor.IRenderingVisitor;

public interface IRenderingElement {
    
    public void accept(IRenderingVisitor visitor);

}
