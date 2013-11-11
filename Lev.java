package MKind;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author MWatkins
 */
public class Lev {

    String title;
    private Image backgroundImage;
    private ImageIcon graphic;
    int backgroundWidth;
    int speed;
    int currentLoc=0;
    PlayerShip currentShip;
    ArrayList<EnemyShip> enemies;
    ArrayList<Obstacle> obstacles;

    /**
     * creates a new level, and will repaint a new level
     * currently in creation
     * @param bgImage is the String path to the level image
     * @param bgWidth is the pixel width of the bgImage
     * @param scrollSpeed is the pixel per refresh speed of scrolling
     */

  Lev(String bgImage, int bgWidth, int scrollSpeed) {
        setBGI(bgImage);
        backgroundWidth = bgWidth;
        speed = scrollSpeed;
    }

    public Image getGraphic() {
        return backgroundImage;
    }

    /**
     * sets the background image
     *
     * @param setto filepath to find the background image in.
     */
    private void setBGI(String setto) {
        try {
            graphic = new ImageIcon(this.getClass().getResource(setto));
            backgroundImage = graphic.getImage();
        } catch (Exception e) {
            System.out.println("Level setBGI caught: ");
            e.printStackTrace();
        }
    }
    public void refresh(Graphics g){
        try {//try to paint background image
            int bgWidthLimit = -2600+MKind.canvasWidth;
            if (currentLoc> bgWidthLimit) {
                currentLoc--;
            }
            graphic = new ImageIcon(this.getClass().getResource("pics/Backgrounds/testBg.png"));
           backgroundImage = graphic.getImage();
            g.drawImage(backgroundImage, currentLoc, 0, (ImageObserver) this);
        } catch (Exception e) {//if it fails, paint a blue rectangle
            g.setColor(Color.blue);
            g.fillRect(0, 0, MKind.canvasWidth, MKind.canvasHeight);
            System.out.println("BG Error: " + e);
        }

    }
}
