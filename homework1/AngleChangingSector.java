
package homework1;

import java.awt.*;

public class AngleChangingSector extends homework1.Shape implements homework1.Animatable {
    Dimension dim;
    int startAngle;
    int arcAngle;

    public AngleChangingSector(Point location, Color color, Dimension dimension, int startAngle_, int arcAngle_){
        super(location, color);
        this.dim = dimension;
        this.startAngle = startAngle_;
        this.arcAngle = arcAngle_;
        this.checkRep();
    }

    private int getHeight(){
        return (int)this.dim.getHeight();
    }

    private int getWidth(){
        return (int)this.dim.getWidth();
    }

    @Override
    public void step(Rectangle bound) {
        this.startAngle++;
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
        this.checkRep();
        return new Rectangle(super.getLocation(),this.dim);
    }

    @Override
    public void draw(Graphics g) {
        this.checkRep();
        g.setColor(super.getColor());
        g.fillArc((int)super.getLocation().getX(), (int)super.getLocation().getY(), this.getWidth(),
                  this.getHeight(), this.startAngle, this.arcAngle);
    }
    
    private void checkRep(){
        assert this.dim != null : "Dimension of an angle changing sector can never be null";
        assert this.dim.getHeight() >= 0 : "Height of an angle changing sector can never be < 0";
        assert this.dim.getWidth() >= 0 : "Width of an angle changing sector can never be < 0";    }
}
