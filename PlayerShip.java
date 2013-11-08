package MKind;

import java.awt.Color;

/**
 * a specific ship class designated for the human player
 *
 * @author MWatkins
 */
public class PlayerShip extends Ship {

    int speed = 3;

    PlayerShip(int x, int y, int size, int conxMax, int conxMin, int conyMax, int conyMin) {
        super(x, y, size, size, conxMax, conxMin, conyMax, conyMin);
        makeGraphic();
        color = Color.blue;//default color if there is no grahpic.
        myGun = new Gun(true, true, 1, fLocations);
        canCollideWithEnemy = true;

    }

    @Override
    /**
     * if the player hits the edge, this method will stop its movement and keep
     * him at the edge until he move away from the edge.
     */
    protected void checkEdge() {
        if ((xmin < containerXMin + 1 || getXMax() > containerXMax - 1) && run != 0) {
            if (xmin < containerXMin + 1) {
                if (run < 0) {
                    run = 0;
                }
            } else {
                if (run > 0) {
                    run = 0;
                }
            }
        }
        if ((ymin < 1 || getYMax() > containerYMax - 1) && rise != 0) {
            if (ymin < containerYMin + 1) {
                if (rise < 0) {
                    rise = 0;
                }
            } else {
                if (rise > 0) {
                    rise = 0;
                }
            }

        }
    }

    /**
     * This method associates a graphic with the ship
     *
     * @param structure this int determines which "type" a ship is
     * @param colorScheme this int determines which skin of that type it will
     * use
     */
    private void setSkin(int structure, int colorScheme) {
        setGraphic("pics/PlayerShips/inLevelShip/type" + structure
                + "/pShip" + colorScheme + ".png");
    }

    /**
     * helper method for creating the ship graphic. current chooses a random one
     * out of the available.
     */
    private void makeGraphic() {
        int type = -1;
        int skin = -1;
        //setting type between 1 and 6
        while (type < 1 || type > 6) {
            type = (int) (Math.random() * 10);
        }
        //setting sking between 1 and 5
        while (skin < 1 || skin > 5) {
            skin = (int) (Math.random() * 10);
        }
        setSkin(type, skin);
    }

    public Gun getGun() {
        return myGun;
    }

    public int getSpeed() {
        return speed;
    }

    public void move(String direction) {
        switch (direction) {
            case "left":
                setRun(-speed);
                break;
            case "right":
                setRun(speed);
                break;
            case "up":
                setRise(-speed);
                break;
            case "down":
                setRise(speed);
                break;
        }
    }

}
