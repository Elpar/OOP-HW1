
package homework1;

import java.awt.*;

public class LocationChangingNumberedOval extends LocationChangingOval {
    static int totalNumOfNumberedOvals;
    private Integer selfId;

    LocationChangingNumberedOval(Point location, Color color, Dimension dimension) {
        super(location, color, dimension);
        this.incrementNumOfNumberedOvals();
        selfId = totalNumOfNumberedOvals;
        checkRep();
    }

    private void incrementNumOfNumberedOvals(){
    	LocationChangingNumberedOval.totalNumOfNumberedOvals ++;
    }

    public static void zeroNumberOfNumberedOvals(){
    	LocationChangingNumberedOval.totalNumOfNumberedOvals = 0;
    }

    public static int getNumOfNumberedLocationChangingOvals(){
    	return totalNumOfNumberedOvals;
    }

    public void draw(Graphics g){
        this.checkRep();
        g.setColor(super.getColor());
        g.fillOval((int)super.getLocation().getX(), (int)super.getLocation().getY(), (int)super.getWidth(), super.getHeight());
        g.setColor(java.awt.Color.black);
        g.drawString( selfId.toString(), (int)super.getLocation().getX(), (int)super.getLocation().getY());
    }

    private void checkRep(){
    	assert selfId.intValue() > 0 : "Oval's ID number can never be <= 0";
    }
}
