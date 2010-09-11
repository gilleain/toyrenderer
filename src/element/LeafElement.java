package element;

import visitor.IRenderingVisitor;

public abstract class LeafElement implements IRenderingElement {

    @Override
    public void accept(IRenderingVisitor visitor) {
        visitor.visit(this);
    }

}
