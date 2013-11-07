package MKind;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * This class defines what every object that appears on the screen will need.
 *
 * @author MWatkins
 */
public abstract class OnScreenObject {

    private ImageIcon graphic;
    Image g;
    protected int xmin;//left bound of object
    protected int ymin;//right bound of object
    protected int xsize;//horizontal size of object
    protected int ysize;//vertical size of object
    protected int rise;//vertical movement per redraw
    protected int run;//horizontal movement per redraw
    protected int containerYMax;//object cannot vertically go beyond
    protected int containerYMin;//object cannot vertically go under
    protected int containerXMax;//object cannot horizontally go beyond
    protected int containerXMin;//object cannot horizontally go under

    protected boolean visible;//toggles existence of object
    protected boolean allowedOffscreen;
    protected boolean isSelected;//if the object was clicked on
    protected boolean canCollideWithEnemy = false;
    protected boolean canCollideWithPlayer = false;
    protected boolean canCollideWithBullet = false;

    protected Color color;//color of object, if not a graphic

    OnScreenObject(int x, int y, int sizeX, int sizeY, int cxMax, int cxMin, int cyMax, int cyMin) {
        xmin = x;
        ymin = y;
        xsize = sizeX;
        ysize = sizeY;
        containerXMax = cxMax;
        containerXMin = cxMin;
        containerYMax = cyMax;
        containerYMin = cyMin;

        //default the object to no movement
        rise = 0;
        run = 0;

        visible = true;
        allowedOffscreen = false;
        isSelected = false;
    }

    public int getXMin() {
        return xmin;
    }

    public int getYMin() {
        return ymin;
    }

    public int getXMax() {
        return xmin + xsize;
    }

    public int getYMax() {
        return ymin + ysize;
    }

    public int getXSize() {
        return xsize;
    }

    public int getYSize() {
        return ysize;
    }

    public Color getColor() {
        return color;
    }

    public boolean getAllowedOffscreen() {
        return allowedOffscreen;
    }

    public boolean getVisible() {
        return visible;
    }

    public int getRun() {
        return run;
    }

    public Image getGraphic() {
        return g;
    }

    /**
     *
     * @param x x coords of point
     * @param y y coords of point
     * @return true if the parameter coords are within this object.
     */
    public boolean isWithin(int x, int y) {
        return x >= xmin && x <= getXMax() && y >= ymin && y <= getYMax();
    }

    public void setXMin(int x) {
        xmin = x;
    }

    public void setYMin(int y) {
        ymin = y;
    }

    public void setXSize(int x) {
        xsize = x;
    }

    public void setYSize(int y) {
        ysize = y;
    }

    /**
     * Defines a picture for this object to display as
     *
     * @param setto is the path of the image to use
     */
    public void setGraphic(String setto) {
        try {
            graphic = new ImageIcon(this.getClass().getResource(setto));
            g = graphic.getImage();
        } catch (Exception e) {
            System.out.println("OnScreenObject setGraphic caught: ");
            e.printStackTrace();
        }
    }

    public void setAllowedOffscreen(boolean allowed) {
        allowedOffscreen = allowed;
    }

    /**
     * This method changes the movement of the object
     *
     * @param riseM set the vertical change of the object
     * @param runM set the horizontal change of the object
     */
    public void setMovement(int riseM, int runM) {
        rise = riseM * -1;//rise means to go up, and negative will update it up
        run = runM;
    }

    /**
     * This method accelerates the object by the parameters
     *
     * @param horizontal if the acceleration should be horizontal or vertical
     * @param amount quantity to increment acceleration by
     */
    public void nudge(boolean horizontal, int amount) {
        if (horizontal) {
            run += amount;
        } else {
            rise += amount;
        }
    }

    public void setVisible(boolean vis) {
        visible = vis;
    }

    public void setColor(Color c) {
        color = c;
    }

    /**
     * Collision detection
     *
     * @param other the object possibly colliding with this one
     * @return true if the two objects have collided
     */
    public boolean checkCollide(OnScreenObject other) {
        if (other.getYMax() < getYMin()) { //if other object is above this
            return false;
        }
        if (other.getYMin() > getYMax()) {//if other object is below this
            return false;
        }
        if (other.getXMax() < getXMin()) {//if other is to the left
            return false;
        }
        if (other.getXMin() > getXMax()) {//if other is to the right
            return false;
        }
        return true;
    }

    /**
     * used to process how the object should change with every screen refresh
     */
    public void update() {
        if (!allowedOffscreen) {
            checkEdge();
        }
        xmin += run;
        ymin += rise;
        try {
            ((Ship) this).setupLocations();
        } catch (Exception e) {

        }

        //tbi - process graphic changes
    }

    protected abstract void checkEdge();//checks and handles cases where object
    //reaches end of screen.

    public boolean canCollideWithPlayer() {
        return canCollideWithPlayer;
    }

    public boolean canCollideWithEnemy() {
        return canCollideWithEnemy;
    }

    public boolean canCollideWithBullet() {
        return canCollideWithBullet;
    }

    int getRise() {
        return rise;
    }

    public void setRise(int amount) {
        rise = amount;
    }

    public void setRun(int amount) {
        run = amount;
    }

}
