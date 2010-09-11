package element;

public class TextBox extends LeafElement {
    
    public String text;

    public double centerX;
    
    public double centerY;
    
    public TextBox(String text, double centerX, double centerY) {
        this.text = text;
        this.centerX = centerX;
        this.centerY = centerY;
    }
    
}
