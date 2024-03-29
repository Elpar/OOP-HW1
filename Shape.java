
package homework1;

import java.awt.*;

/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {

	private Point location;
	private Color color;

	// Abstraction Function:
    // A Shape S is invalid if its location is out of the frame's boundaries or null, or its color is null.

    // Representation Invariant:
    // this.color != null
    // this.location != null
    // this.location.x >= 0 && this.location.y >= 0.

    /**
     * @effects throws alerts on different assertions of the Representation Invariant
     * @throws AssertionError if the Representation Invariant is being violated.
     */
    private void checkRep() {
        assert this.color != null : "Color of Shape cannot be null";
        assert this.location != null : "Location of Shape cannot be null";
        assert this.location.getX() >= 0 : "Location's X value is out of upper left corner's boundaris";
        assert this.location.getY() >= 0 : "Location's Y value is out of upper left corner's boundaris";
    }
	
	/**
	 * @effects Initializes this with a a given location and color.
	 */
    public Shape(Point location, Color color) {
        //checkRep();
        setLocation(location);
    	setColor(color);
    	checkRep();
    }

    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
        checkRep();
        return (Point)this.location;
    }

    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     * 			returns location after call has completed.
     */
    public void setLocation(Point location) {
    	checkRep();
        this.location = (Point)location.clone();
        checkRep();
    }

    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     * 			dimension.
     * 			If this cannot be resized to the specified dimension =>
     * 			this is not modified, throws ImpossibleSizeException
     * 			(the exception suggests an alternative dimension that is
     * 			 supported by this).
     */
    public abstract void setSize(Dimension dimension) throws ImpossibleSizeException;

    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();

    /**
     * @return true if the given point lies inside the bounding rectangle of
     * 		   this and false otherwise.
     */
    public boolean contains(Point point) {
        checkRep();
        return getBounds().contains(point);
    }

    /**
     * @return color of this.
     */
    public Color getColor() {
        checkRep();
        return color;
    }

    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        checkRep();
        this.color = color;
    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);

    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
        checkRep();
        Shape clonedCopy = null;
        try {
            clonedCopy = (Shape)super.clone();
            clonedCopy.setColor(this.color);
            clonedCopy.setLocation(this.location);
        } catch (CloneNotSupportedException e) { //shouldn't get here since Object (super) has clone() function
            e.printStackTrace();
        }
        checkRep();
        return clonedCopy;
    }
}
