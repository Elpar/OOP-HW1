
package homework1;

import java.awt.*;
import java.awt.Shape;
import .java.util.Random;


/**
 * A LocationChangingShape is a Shape that can change its location using its step()
 * method. A LocationChangingShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends homework1.Shape implements Animatable {
    private int velocityX_;
    private int velocityY_;
    private Random randInt;
    private int maxVelocity;
    private int minVelocity;
    private int height;
    private int width;

	// Abstraction Function:
    // if velocityX_ = 0 the shape stays in its horizontalplace, if velocityX_ > 0 shape moves right, else shape moves left.
    // if velocityY_ = 0 the shape stays in its vertical place, if velocityX_ > 0 shape moves up, else shape moves down.
	
	// Representation Invariant:
    // velocityX_ and velocityY_ can never be more than 5 or less than -5
    //height and width whould allways be >= 0

	
	/**
	 * @effects Initializes this with a a given location and color. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	LocationChangingShape(Point location, Color color, int height_, int width_) {
        super(location,color);
        this.maxVelocity = 5;
        this.minVelocity = -5;
        this.randInt = new Random();
        this.velocityX_ = randInt.nextInt(((maxVelocity - minVelocity) + 1)+minVelocity);
        this.velocityY_ = randInt.nextInt(((maxVelocity - minVelocity) + 1)+minVelocity);
        this.height = height_;
        this.width = width_;
        checkRep();
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
        checkRep();
        return this.velocityX_;
    	
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
        checkRep();
    	return this.velocityY_;
    }


    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     * 			vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
        checkRep();
    	this.velocityX_ = velocityX;
        this.velocityY_ = velocityY;
        checkRep();    	
    }


    /**
     * @modifies this
     * @effects Let p = location
     * 				v = (vx, vy) = velocity
     * 				r = the bounding rectangle of this
     *         	If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     * 				If adding v to p would move r horizontally farther away
     * 				from the center of bound,
     * 					vx = -vx
     * 				If adding v to p would move r vertically farther away
     * 				from the center of bound,
     * 					vy = -vy
     *          }
     * 			p = p + v
     */
    public void step(Rectangle bound) {
        checkRep();
    	if(!bound.contains(super.Point.getX(), super.Point.getY(), this.width, this.height) || 
            !bound.contains(super.Point.getX() + velocityX_, super.Point.getY() + velocityY_, this.width, this.height){
                if(!bound.contains(super.Point.getX() + velocityX_, super.Point.getY(), this.width, this.height){
                    this.velocityX_ = -this.velocityX_;
                }
                if(!bound.contains(super.getLocation().getX(), super.getLocation().getY() + velocityY_, this.width, this.height){
                    this.velocityY_ = -this.velocityY_;
                }
            } 
            super.setLocation(super.getLocation().getX() + velocityX_, super.getLocation().getY() + velocityY_);
        checkRep();
    }

    /**
     * Checks to see if the Representation Invariant is being violated.
     * @throws AssertionError if the Representation Invariant is being violated.
     */
    private void checkRep(){
        assert this.velocityX_ >= -5 :
            "horizontal velocity cannot be less than -5";
        if(this.velocityX_ > -5){
            assert this.velocityX_ <=5 : 
                "horizontal velocity cannot be more than 5";
        }

        assert this.velocityY_ >= -5 :
            "vertical velocity cannot be less than -5";
        if(this.velocityY_ > -5){
            assert this.velocityY_ <=5 : 
                "vertical velocity cannot be more than 5";
        }
        assert this.width >=0 :
            "bounding rectangle should allways have non negative width value";
        if(this.width >= 0){
            assert this.height >=0 :
                "bounding rectangle should allways have non negative height value";
        }

    }
}
