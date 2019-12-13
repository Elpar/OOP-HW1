
package homework1;

import java.awt.*;

public class LocationChangingRoundedRectangle extends LocationChangingRectangle {
	int arcWidth;
	int arcHeight;

    LocationChangingRoundedRectangle(Point location, Color color,Dimension dimension, int arcWidth_, int arcHeight_) {
        super(location, color, dimension);
        this.arcWidth = arcWidth_;
        this.arcHeight = arcHeight_;
    }

    public void draw(Graphics g) {
        this.checkRep();
        g.setColor(super.getColor());
        g.fillRoundRect((int)super.getLocation().getX(), (int)super.getLocation().getY(), super.getWidth(),
                        super.getHeight(), this.arcWidth, this.arcHeight);
    }

    private void checkRep(){
    	assert arcWidth >= 0 : "Arc width of a rounded rectangle can never be < 0";
    	assert arcHeight >= 0 : "Arc height of a rounded rectangle can never be < 0";
    }
    
}
