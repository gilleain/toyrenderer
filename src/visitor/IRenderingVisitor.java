package visitor;

import element.IRenderingElement;

public interface IRenderingVisitor {
    
    public void visit(IRenderingElement element);

}
