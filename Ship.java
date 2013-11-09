package MKind;

import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class is used to define what a ship needs in order to function with the
 * game
 *
 * @author MWatkins
 */
public abstract class Ship extends OnScreenObject {

    Gun myGun;
    int[] fLocations = new int[16];//the dimensions of each location the ship can fire from

    public Ship(int x, int y, int sizeX, int sizeY, int cxMax, int cxMin, int cyMax, int cyMin) {
        super(x, y, sizeX, sizeY, cxMax, cxMin, cyMax, cyMin);
        canCollideWithBullet = true;
        setupLocations();
    }

    public void setupLocations() {
        fLocations[0] = xmin;
        fLocations[1] = getYMax();
        fLocations[2] = xmin + (xsize / 2);
        fLocations[3] = getYMax();
        fLocations[4] = getXMax();
        fLocations[5] = getYMax();
        fLocations[6] = xmin;
        fLocations[7] = ymin + (ysize / 2);
        fLocations[8] = getXMax();
        fLocations[9] = ymin + (ysize / 2);
        fLocations[10] = xmin;
        fLocations[11] = ymin;
        fLocations[12] = xmin + (xsize / 2);
        fLocations[13] = ymin;
        fLocations[14] = getXMax();
        fLocations[15] = ymin;
    }

    public ArrayList<OnScreenObject> fire() throws UnsupportedAudioFileException, LineUnavailableException {
        return myGun.fire();
    }

}
