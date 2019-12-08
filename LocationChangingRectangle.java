
package homework1;

import java.awt.*;

public class LocationChangingRectangle extends homework1.LocationChangingShape {
    private Dimension dimension_;

    LocationChangingRectangle(Point location, Color color) {
        super(location, color);
    }

    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        if(super.getBounds().contains(super.getLocation().getX(), super.getLocation().getY(), this.dimension_.getWidth(), this.dimension_.getHeight())){
            throw new ImpossibleSizeException();
            return;
        }
        this.dimension_ = dimension;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void draw(Graphics g) {

    }
}
