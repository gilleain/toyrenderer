package generator;

import element.IRenderingElement;

public interface IGenerator<T> {
    
    public IRenderingElement generate(T obj);

}
