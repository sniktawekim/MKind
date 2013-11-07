package MKind;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class interactivePanel extends JPanel {

    ArrayList<OnScreenObject> objects;//contains all objects to be displayed
    IClick myClick;//mouse listener, useful for menu options
    Ipress myType;//keyboard listener, for playing the game
    PlayerShip currentShip;
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
        checkPress();

    }

    public boolean update() {
        if (!gameOver) {
            checkDeadShip();//also sets current ship
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
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        try {
            graphic = new ImageIcon(this.getClass().getResource("pics/Backgrounds/testBg.png"));
            gr = graphic.getImage();
            g.drawImage(gr, 0, 0, this);
        } catch (Exception e) {
            System.out.println("BG Error: " + e);
        }

    }

    private void paintObjects(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            OnScreenObject current = objects.get(i);
            g.setColor(current.getColor());
            if (current.getGraphic() != null) {
                g.drawImage(current.getGraphic(), current.getXMin(), current.getYMin(), this);
            } else {
                g.fillOval(current.getXMin(), current.getYMin(), current.getXSize(), current.getYSize());
            }
        }
    }

    public void addObject(OnScreenObject toAdd) {
        objects.add(toAdd);
    }

    private void checkPress() {
        if (myType.getDown()) {
            objects.get(0).setRise(3);
        } else if (myType.getUp()) {
            objects.get(0).setRise(-3);
        } else {
            objects.get(0).setRise(0);
        }
        if (myType.getLeft()) {
            objects.get(0).setRun(-3);
        } else if (myType.getRight()) {
            objects.get(0).setRun(3);
        } else {
            objects.get(0).setRun(0);
        }
        if (myType.getSpace()) {
            fire();
        }
    }

    private void fire() {
        addObjects(currentShip.fire());

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

    private void checkDeadShip() {
        if (objects.size() > 0) {
            try {
                currentShip = (PlayerShip) objects.get(0);
            } catch (Exception e) {
                gameOver = true;
            }
        }
        if (objects.size() == 1) {
            createEnemyShips();
        }
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
        if ((int) (Math.random() * 10) == 1 && (int) (Math.random() * 10) == 1 && (int) (Math.random() * 10) > 7) {
            try {
                addObjects(((EnemyShip) current).fire());
            } catch (Exception e) {
            }
        }
    }

    public void createEnemyShips() {//adding 3 enemies to the canvas with each skin.
        for (int i = 0; i < 10; i++) {
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
