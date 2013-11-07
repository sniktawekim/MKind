package MKind;

import java.util.ArrayList;

/**
 *
 * @author MWatkins
 */
public class Gun {

    boolean firesLeft;//does it fire left or right
    boolean enemyBullets;//should the bullets kill the player
    int[] fireLocations = new int[16];
    int bulletXSize = 5;
    int bulletYSize = 5;
    int lastShot;//last time this gun fired
    boolean spread, beam, wide, burst, clear, sidebeams, rear, rearbeam;
    /* Coord locations in array for each fire location (x,y):
     (10,11) (12,13) (14,15)
     (6,7)           (8,9)
     (0,1)   (2,3)   (4,5)
     */

    Gun(boolean playerGun, boolean fireRight, int bulletsPerFire, int[] locations) {
        firesLeft = !fireRight;
        enemyBullets = !playerGun;
        fireLocations = locations;
        if(playerGun){
            bulletXSize = 10;
            bulletYSize = 10;
        }
        
    }

    /**
     * this method upgrades the gun to fire in upper and lower right diagonals
     */
    public void addSpread() {
        spread = true;
    }

    /**
     * this method upgrades the front middle fire to be a beam instead of bullet
     */
    public void addFBeam() {
        beam = true;
    }

    /**
     * this method adds bullet fire to upper middle and lower middle locations
     */
    public void addWide() {
        wide = true;
    }

    /**
     * this method adds bullet fire to rear location
     */
    public void addRear() {
        rear = true;
    }

    /**
     * this method upgrades upper middle and lower middle firing to beam
     */
    public void addSideBeams() {
        sidebeams = true;
    }

    /**
     * this method upgrades rear firing to beam
     */
    public void addRearBeam() {
        rearbeam = true;
    }

    /**
     * This method adds bullet fire locations in upper rear and lower rear
     */
    public void addBurst() {
        burst = true;
    }

    /**
     * this adds an AOE damage weapon to the ship
     */
    public void addClear() {
        clear = true;
    }

    ArrayList<OnScreenObject> fire() {
        int currentTime = MKind.counter1;
        ArrayList<OnScreenObject> toFire = new ArrayList<>();
        Bullet toAdd;
        if (firesLeft) {
            toAdd = new Bullet(fireLocations[6], fireLocations[7]-((int)bulletXSize/2), bulletXSize, bulletYSize);
            int rise = 0;
            if ((int) (Math.random() * 10) == 1 && (int) (Math.random() * 10) == 1) {
                rise = 2;
            } else if ((int) (Math.random() * 10) == 1 && (int) (Math.random() * 10) == 1) {
                rise = -2;
            }
            toAdd.setMovement(rise, -3);
        } else {
            toAdd = new Bullet(fireLocations[8], fireLocations[9]-((int)bulletXSize/2), bulletXSize, bulletYSize);
            toAdd.setMovement(0, 5);
        }
        toAdd.setPlayerBullet(!enemyBullets);

        if (currentTime - lastShot > 7||beam) {
            toFire.add(toAdd);
            lastShot = currentTime;
        }

        return toFire;
    }

}
