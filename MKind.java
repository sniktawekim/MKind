package MKind;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 * This should be a reusable Main class which constantly repaints a canvas panel
 * for a game. MWatkins Oct 30, 2013
 */
public class MKind {

    static int counter1 = 0;
    static boolean counter2 = false;
    static final int frameWidth = 1300;
    static final int frameHeight = 800;
    static final int canvasWidth = 1292;
    static final int canvasHeight = 770;
    static interactivePanel canvas;
    static JFrame frame;

    public static void pause() {
        try {
            Thread.sleep(12); // wait 5ms
            counter2 = !counter2;
            if (counter2) {
                counter1++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end method pause

    public static void main(String[] args) {
        gameInit();

        while (true) {
            if (canvas.update()) {//if player hasnt died
                pause();
                canvas.repaint();
            } else {//if player has died
                frame.dispose();//kill current frame
                gameInit();//start over
            }
        }

    }

    public static void gameInit() {//code to run at start of game
        makeScreen();//creates canvas/frame, and displays them

        //For testing core mechanics:
        createPlayerShip();
        createEnemyShips();
        //\
    }

    public static void makeScreen() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);//lock game resolution
        frame.setVisible(true);
        canvas = new interactivePanel();
        frame.getContentPane().add(canvas);
        canvas.requestFocusInWindow();

    }

    public static void createPlayerShip() {//adding a player ship to the canvas
        PlayerShip playerShip = new PlayerShip(0, 0, 25, canvasWidth, 0, canvasHeight, 0);
        canvas.addObject(playerShip);
    }

    private static void createEnemyShips() {
        canvas.createEnemyShips();
    }
}
