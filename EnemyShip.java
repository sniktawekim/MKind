package MKind;

import java.awt.Color;

/**
 *This specific Ship defines an AI ship
 * @author MWatkins
 */
public class EnemyShip extends Ship{
    
EnemyShip(int x, int y, int size, int conxMax, int conxMin, int conyMax, int conyMin) {
        super(x, y, size, size, conxMax, conxMin, conyMax, conyMin);
        myGun = new Gun(false,false,1,fLocations);
        canCollideWithPlayer = true;
        
        color = Color.red;//default color if there is no graphic
        int skin = -1;
        int type = -1;
        while (skin < 1 || skin > 5) {
            skin = (int) (Math.random() * 10);
        }
        while (type < 1 || type > 2) {
            type = (int) (Math.random() * 10);
        }
        try{
        setGraphic("pics/EnemyShips/25x25/type" + type + "/eShip" + skin + ".png");
        } catch(Exception e){
            System.out.println("Enemy Ship Graphic Error: " + e);
        }
    }
/**
 * this checks if the ship hits the wall, and makes it bounce in
 * the other direction if it does.
 */
    @Override
    protected void checkEdge() {
        
        if ((xmin < containerXMin || getXMax() > containerXMax) && run != 0) {
            run *= -1;
        }
        if ((ymin < 0 || getYMax() > containerYMax) && rise != 0) {
            rise *= -1;
        }
    }

    @Override
    public boolean canCollideWithPlayer() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canCollideWithEnemy() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    
}
