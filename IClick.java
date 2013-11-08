package MKind;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IClick implements MouseListener {

    int x;
    int y;
    boolean clicked;
    final int xOffset = -8;
    final int yOffset = -30;

    IClick() {
        clicked = false;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
        x = e.getXOnScreen() + xOffset;
        y = e.getYOnScreen() + yOffset;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean getClicked() {
        if (clicked) {
            clicked = false;
            return true;
        } else {
            return false;

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
