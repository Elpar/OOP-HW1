
package homework1;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LocationChangingOval extends homework1.LocationChangingShape {
    Dimension dim;

    LocationChangingOval(Point location, Color color, Dimension dimension) {
        super(location, color);
        this.dim = dimension;
    }

    public int getHeight(){
        this.checkRep();
        return (int)this.dim.getHeight();
    }

    public int getWidth(){
        this.checkRep();
        return (int)this.dim.getWidth();
    }

    @Override
    public void setSize(Dimension dimension) throws homework1.ImpossibleSizeException {
        this.checkRep();
        if(dimension.getHeight() < 0 || dimension.getWidth() < 0){
            throw new homework1.ImpossibleSizeException(dimension);
        }
        this.dim = dimension;
        this.checkRep();
    }

    @Override
    public Rectangle getBounds() {
        checkRep();
        return new Rectangle(super.getLocation(),this.dim);
    }

    @Override
    public void draw(Graphics g) {
        this.checkRep();
        g.setColor(super.getColor());
        g.fillOval((int)super.getLocation().getX(), (int)super.getLocation().getY(), this.getWidth(), this.getHeight());
    }

    private void checkRep(){
        assert this.dim != null : "Dimension of a rectangle can never be null";
        assert this.dim.getHeight() >= 0 : "Height of a rectangle can never be < 0";
        assert this.dim.getWidth() >= 0 : "Width of a rectangle can never be < 0";
    }

}
