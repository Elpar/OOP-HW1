
package homework1;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LocationChangingOval extends homework1.LocationChangingShape {


    LocationChangingOval(Point location, Color color) {
        super(location, color);
    }

    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void draw(Graphics g) {

    }

}
