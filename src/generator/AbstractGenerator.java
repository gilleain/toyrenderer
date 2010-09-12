package generator;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class AbstractGenerator {
    
    public Point2D transformPoint(
            double centerX, double centerY, AffineTransform transform) {
        Point2D ptSrc = new Point2D.Double(centerX, centerY);
        return transform.transform(ptSrc, null);
    }

}
