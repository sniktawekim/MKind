package MKind;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ipress implements KeyListener {

    boolean left, right, up, down, fire, switchLeft, switchRight;
    int kUp = 87, kLeft = 65, kDown = 83, kRight = 68, kFire = 74, kSwitchLeft = 85, kSwitchRight = 73;

    //keycodes:
    //87 = w
    //65 = a
    //83 = s
    //68 = d
    //74=j
    //107 = numpad +
    //109 = numpad - 
    Ipress() {
        fire = false;
        left = false;
        right = false;
        up = false;
        down = false;
        fire = false;
        switchLeft = false;
        switchRight = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == kUp || e.getKeyCode() == kDown || e.getKeyCode() == kLeft || e.getKeyCode() == kRight) {
            directional(e.getKeyCode());
        }
        if (e.getKeyCode() == kFire) {
            fire = true;
        }
        if (e.getKeyCode() == kSwitchLeft) {
            switchLeft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 87 || e.getKeyCode() == 65 || e.getKeyCode() == 83 || e.getKeyCode() == 68) {
            directionalRelease(e.getKeyCode());
        }
        if (e.getKeyCode() == kFire) {
            fire = false;
        }

    }

    private void directional(int dir) {
        if (dir == kLeft) {
            left = true;
        }
        if (dir == kUp) {
            up = true;
        }
        if (dir == kRight) {
            right = true;
        }
        if (dir == kDown) {
            down = true;
        }
    }

    public boolean getKeyPressed(String actionName) {
        switch (actionName) {
            case "fire":
                return fire;
            case "left":
                return left;
            case "right":
                return right;
            case "up":
                return up;
            case "down":
                return down;
            case "switch left":
                if (switchLeft) {
                    switchLeft = false;
                    return true;
                }
            case "switch right":
                return switchRight;
        }

        return false;
    }

    public boolean getFire() {
        return fire;
    }

    private void directionalRelease(int dir) {
        if (dir == kLeft) {
            left = false;
        }
        if (dir == kUp) {
            up = false;
        }
        if (dir == kRight) {
            right = false;
        }
        if (dir == kDown) {
            down = false;
        }
    }

}
