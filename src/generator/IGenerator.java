package generator;

import java.awt.geom.AffineTransform;

import element.IRenderingElement;

public interface IGenerator<T> {
    
    public IRenderingElement generate(T obj);
    
    public IRenderingElement generate(T obj, AffineTransform transform);

}
