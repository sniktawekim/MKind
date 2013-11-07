package MKind;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ipress implements KeyListener {

    boolean space, left, right, up, down;

    Ipress() {
        space = false;
        left = false;
        right = false;
        up = false;
        down = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
            directional(e.getKeyCode());
        }
        if (e.getKeyCode() == 32) {
            space = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
            directionalRelease(e.getKeyCode());
        }
        if (e.getKeyCode() == 32) {
            space = false;
        }
    }

    private void directional(int dir) {
        if (dir == 37) {
            left = true;
        }
        if (dir == 38) {
            up = true;
        }
        if (dir == 39) {
            right = true;
        }
        if (dir == 40) {
            down = true;
        }
    }

    public boolean getLeft() {
        if (left) {
            return true;
        } else {
            return false;

        }
    }

    public boolean getRight() {
        if (right) {
            return true;
        } else {
            return false;

        }
    }

    public boolean getUp() {
        if (up) {
            return true;
        } else {
            return false;

        }
    }

    public boolean getDown() {
        if (down) {
            return true;
        } else {
            return false;

        }
    }

    public boolean getSpace() {
        return space;
    }

    private void directionalRelease(int dir) {
        if (dir == 37) {
            left = false;
        }
        if (dir == 38) {
            up = false;
        }
        if (dir == 39) {
            right = false;
        }
        if (dir == 40) {
            down = false;
        }
    }

}
