package MKind;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class interactivePanel extends JPanel {

    ArrayList<OnScreenObject> objects;//contains all objects to be displayed
    IClick myClick;//mouse listener, useful for menu options
    Ipress myType;//keyboard listener, for playing the game
    PlayerShip currentShip;
    int currentShipint = 0;
    private ImageIcon graphic;
    private Image gr;
    boolean gameOver = false;
    final int canvasWidth = 1292;
    final int canvasHeight = 770;

    interactivePanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        objects = new ArrayList<>();
        myClick = new IClick();
        myType = new Ipress();
        this.addMouseListener(myClick);
        this.addKeyListener(myType);

    }

    @Override
    public void paintComponent(Graphics g) {
        paintBackground(g);
        paintObjects(g);
        checkClick();
        try {
            checkPress();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(interactivePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(interactivePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean update() {
        if (!gameOver) {
            if(checkDeadShip())//also sets current ship
                return false;
            updateObjects();//also removes invisible
            checkCollisions();
            return true;

        } else {
            return false;
        }

    } // end method update

    private void checkClick() {
        if (!myClick.getClicked()) {
            return;
        }
        for (int i = 0; i < objects.size(); i++) {
            OnScreenObject current = objects.get(i);
            if (current.isWithin(myClick.getX(), myClick.getY())) {
                System.out.println("CLICKED");
            }
        }
    }

    private void paintBackground(Graphics g) {
        try {//try to paint background image
            graphic = new ImageIcon(this.getClass().getResource("pics/Backgrounds/testBg.png"));
            gr = graphic.getImage();
            g.drawImage(gr, 0, 0, this);
        } catch (Exception e) {//if it fails, paint a black rectangle
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            System.out.println("BG Error: " + e);
        }

    }

    private void paintObjects(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            OnScreenObject current = objects.get(i);
            g.setColor(current.getColor());
            if (current.getGraphic() != null) {//try to paint the object's image
                g.drawImage(current.getGraphic(), current.getXMin(), current.getYMin(), this);
            } else {//if it fails to load the image, paint it as a default color
                g.fillOval(current.getXMin(), current.getYMin(), current.getXSize(), current.getYSize());
            }
        }
    }

    public void addObject(OnScreenObject toAdd) {
        objects.add(toAdd);
    }

    private void checkPress() throws UnsupportedAudioFileException, LineUnavailableException {
        if (!gameOver) {
            if (myType.getKeyPressed("down")) {//if they pressed down
                currentShip.move("down");//move ship down
            } else if (myType.getKeyPressed("up")) {//etc
                currentShip.move("up");
            } else {//if neither
                currentShip.setRise(0);//make ship not move in either direction
            }

            if (myType.getKeyPressed("left")) {
                currentShip.move("left");
            } else if (myType.getKeyPressed("right")) {
                currentShip.move("right");
            } else {
                currentShip.setRun(0);
            }
            if (myType.getKeyPressed("fire")) {
                addObjects(currentShip.fire());
            }
            if (myType.getKeyPressed("switch left")) {
            //eventually this will switch the current ship you are using
                //for now, it toggles beam mode.
                currentShip.getGun().toggleUpgrade();
            }
            /*
             if(myType.getKeyPressed("switch right")){
             handle right switch
             }
             */
        }
    }

    private void checkCollisions() {
        for (int i = 0; i < objects.size() - 1; i++) {
            for (int j = 0; j < objects.size(); j++) {
                if (j != i) {
                    OnScreenObject current = objects.get(i);
                    OnScreenObject next = objects.get(j);
                    if (current.checkCollide(next)) {
                        if (((current.canCollideWithEnemy() && next.canCollideWithPlayer())
                                || (current.canCollideWithPlayer() && next.canCollideWithEnemy())) && (current.canCollideWithBullet() || next.canCollideWithBullet())) {
                            objects.remove(i);
                            if (i < j) {
                                objects.remove(j - 1);
                            } else {
                                objects.remove(j);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkDeadShip() {
        if (objects.size() > 0) {
            try {
                currentShip = (PlayerShip) objects.get(currentShipint);
            } catch (Exception e) {
                gameOver = true;
                return true;
            }
        }
        if (objects.size() == 1) {
            createEnemyShips();
        }
        return false;
    }

    private void updateObjects() {
        for (int i = 0; i < objects.size(); i++) {
            OnScreenObject current = objects.get(i);
            current.update();
            if (!current.getVisible()) {
                objects.remove(i);
            }
            enemyFire(current);
        }
    }

    public void addObjects(ArrayList<OnScreenObject> toAdd) {
        for (int i = 0; i < toAdd.size(); i++) {
            addObject(toAdd.get(i));
        }
    }

    private void enemyFire(OnScreenObject current) {
        if ((int) (Math.random() * 10) == 1 && (int) (Math.random() * 10) == 1) {
            try {
                addObjects(((EnemyShip) current).fire());
            } catch (Exception e) {
            }
        }
    }

    public void createEnemyShips() {//adding 3 enemies to the canvas with each skin.
        for (int i = 0; i < 50; i++) {
            int xstart = -1;
            int ystart = -1;
            while (xstart > canvasWidth - 25 || xstart < 201) {
                xstart = (int) (Math.random() * 1000);
            }
            while (ystart > canvasHeight - 25 || ystart < 0) {
                ystart = (int) (Math.random() * 1000);
            }
            EnemyShip eShip = new EnemyShip(xstart, ystart, 25, canvasWidth, 200, canvasHeight, 0);
            eShip.setMovement(1, -1);
            addObject(eShip);
        }
    }
}
