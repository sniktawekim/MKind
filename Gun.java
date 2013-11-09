package MKind;

import java.applet.Applet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author MWatkins
 */
public class Gun {

    Sound pBulletSound = new Sound("sounds/pBullet.wav");
    Sound eBulletSound = new Sound("sounds/eBullet.wav");
    Sound beamSound = new Sound("sounds/pBeam.wav");
    boolean firesLeft;//does it fire left or right
    boolean enemyBullets;//should the bullets kill the player
    int[] fireLocations = new int[16];
    int bulletXSize = 5;
    int bulletYSize = 5;
    int lastShot;//last time this gun fired
    int lastPlay;//last time fire noise happened
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
        if (playerGun) {
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
    public void setFBeam(boolean set) {
        beam = set;
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

    ArrayList<OnScreenObject> fire() throws UnsupportedAudioFileException, LineUnavailableException {
        ArrayList<OnScreenObject> toFire = new ArrayList<>();
        Bullet toAdd;
        if (firesLeft) {
            toAdd = new Bullet(fireLocations[6], fireLocations[7] - ((int) bulletXSize / 2), bulletXSize, bulletYSize);
            int rise = 0;
            if ((int) (Math.random() * 10) == 1 && (int) (Math.random() * 10) == 1) {
                rise = 2;
            } else if ((int) (Math.random() * 10) == 1 && (int) (Math.random() * 10) == 1) {
                rise = -2;
            }
            toAdd.setMovement(rise, -3);
        } else {
            toAdd = new Bullet(fireLocations[8], fireLocations[9] - ((int) bulletXSize / 2), bulletXSize, bulletYSize);
            toAdd.setMovement(0, 5);
        }
        toAdd.setPlayerBullet(!enemyBullets);

        if (beam) {
            toAdd.setGraphic("pics/Bullets/pBeam.png");
            toAdd.setXMin(fireLocations[8] - 25);
            toAdd.setYMin(fireLocations[9] - 12);
            toAdd.setXSize(5);
            toAdd.setYSize(25);
            toAdd.setMovement(0, 25);
            playSound();
            toFire.add(toAdd);
        } else if (MKind.counter1 - lastShot > 7) {
            playSound();
            toFire.add(toAdd);
            lastShot = MKind.counter1;

        }

        return toFire;
    }

    void toggleUpgrade() throws LineUnavailableException {
        beam = !beam;
    }

    private void playSound() {
        if (MKind.counter1 - lastPlay > 1) {
            if (!enemyBullets && !beam) {
                pBulletSound = new Sound("sounds/pBullet.wav");
                pBulletSound.play();
            } else if (beam) {
                beamSound = new Sound("sounds/pBeam.wav");
                beamSound.play();
            } else {
                if(MKind.counter2){
                eBulletSound = new Sound("sounds/eBullet.wav");
                eBulletSound.play();
                }
            }
            lastPlay = MKind.counter1;
        }
        
    }

}
