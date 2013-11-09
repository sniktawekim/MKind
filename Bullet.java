package MKind;

import java.awt.Color;

/**
 * @author MWatkins
 */
public class Bullet extends OnScreenObject {

    static final int px = 1292;
    static final int py = 770;
    boolean playerBullet = true;

    Bullet(int startLocX, int startLocY, int xsize, int ysize) {
        super(startLocX, startLocY, xsize, ysize, px, 0, py, 0);
        setGraphic("pics/Bullets/eBullet1.png");
    }

    @Override
    protected void checkEdge() {
        if (getXMax() < containerXMin || xmin > containerXMax || getYMax() < 0 || ymin > containerYMax) {
            setVisible(false);
        }
    }

    public void setPlayerBullet(boolean isPlayerBullet) {
        playerBullet = isPlayerBullet;
        canCollideWithEnemy = playerBullet;
        canCollideWithPlayer = !playerBullet;
        if (isPlayerBullet) {
            setGraphic("pics/Bullets/pBullet1.png");
        }
    }

}
