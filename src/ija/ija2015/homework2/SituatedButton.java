package ija.ija2015.homework2;

import javax.swing.JButton;

/**
 * Jbutton, který zná svou pozici v poli tlačetek grafického rozhraní.
 *
 * @author xjelin42, xpavlu08
 */
public class SituatedButton extends JButton {

    private final int x;
    private final int y;

    public SituatedButton(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getXpos() {
        return x;
    }

    public int getYpos() {
        return y;
    }

}
