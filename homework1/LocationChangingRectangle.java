
package homework1;

import java.awt.*;

public class LocationChangingRectangle extends homework1.LocationChangingShape {
    private Dimension dim;

    LocationChangingRectangle(Point location, Color color, Dimension dimension) {
        super(location, color);
        dim = dimension;
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
        return;
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
        g.fillRect((int)super.getLocation().getX(), (int)super.getLocation().getY(), this.getWidth(), this.getHeight());
    }

    private void checkRep(){
        assert this.dim != null : "Dimension of a rectangle can never be null";
        assert this.dim.getHeight() >= 0 : "Height of a rectangle can never be < 0";
        assert this.dim.getWidth() >= 0 : "Width of a rectangle can never be < 0";
    }
}
