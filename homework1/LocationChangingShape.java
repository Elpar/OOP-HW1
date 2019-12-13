
package homework1;

import java.awt.*;
import java.awt.Shape;
import java.util.Random;


/**
 * A LocationChangingShape is a Shape that can change its location using its step()
 * method. A LocationChangingShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends homework1.Shape implements homework1.Animatable {
    private int velocityX_;
    private int velocityY_;
    private Random randInt;
    private int maxInitVelocity;
    private int minInitVelocity;


	// Abstraction Function:
    // if velocityX_ = 0 the shape stays in its horizontalplace, if velocityX_ > 0 shape moves right, else shape moves left.
    // if velocityY_ = 0 the shape stays in its vertical place, if velocityX_ > 0 shape moves up, else shape moves down.
	
	// Representation Invariant:
    // velocityX_ and velocityY_ should always be initialized <= 5 or >= -5
	
	/**
	 * @effects Initializes this with a a given location and color. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	LocationChangingShape(Point location, Color color) {
        super(location,color);
        this.maxInitVelocity = 5;
        this.minInitVelocity = -5;
        this.randInt = new Random();
        this.velocityX_ = randInt.nextInt(((this.maxInitVelocity - this.minInitVelocity) + 1)
                                            +this.minInitVelocity);
        this.velocityY_ = randInt.nextInt(((this.maxInitVelocity - this.minInitVelocity) + 1)
                                            +this.minInitVelocity);
        //checkRep();
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
        //checkRep();
        return this.velocityX_;
    	
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
        //checkRep();
    	return this.velocityY_;
    }


    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     * 			vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
        //checkRep();
    	this.velocityX_ = velocityX;
        this.velocityY_ = velocityY;
        //checkRep();
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
        //checkRep();
        Rectangle newProposedRect = new Rectangle((int)super.getLocation().getX() + this.getVelocityX(), (int)super.getLocation().getY() + (int)this.getVelocityY(), (int)this.getBounds().getWidth(), (int)this.getBounds().getHeight());
    	if(!bound.contains(this.getBounds()) || !bound.contains(newProposedRect)){
                newProposedRect.setLocation((int)super.getLocation().getX() + (int)this.getVelocityX(), (int)super.getLocation().getY());
                if(!bound.contains(newProposedRect)){
                    this.setVelocity(-this.getVelocityX(), this.getVelocityY());
                }
                newProposedRect.setLocation((int)super.getLocation().getX(), (int)super.getLocation().getY() + velocityY_);
                if(!bound.contains(newProposedRect)){
                    this.setVelocity(this.getVelocityX(), -this.getVelocityY());
                }
            } 
            Point newLocation = new Point((int)super.getLocation().getX() + velocityX_, (int)super.getLocation().getY() + velocityY_);
            super.setLocation(newLocation);
        //checkRep();
    }

    /**
     * Checks to see if the Representation Invariant is being violated.
     * @throws AssertionError if the Representation Invariant is being violated.
     */
    //private void checkRep(){
     //   assert this.getBounds().getWidth() >=0 :
     //       "bounding rectangle should allways have non negative width value";
     //   if(this.getBounds().getWidth() >= 0){
     //       assert this.getBounds().getHeight() >=0 :
     //           "bounding rectangle should allways have non negative height value";
     //   }

    //}
}
