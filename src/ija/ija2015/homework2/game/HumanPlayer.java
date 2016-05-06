package ija.ija2015.homework2.game;

import static ija.ija2015.homework2.game.Player.Ai.human;
import java.io.Serializable;

/**
 * Třída reprezentující hráče ovládaného člověkem.
 *
 * @author xjelin42, xpavlu08
 */
public class HumanPlayer extends Player implements Serializable {

    public HumanPlayer(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean putDisk(Game game) {
        return false;
    }

    @Override
    public Player.Ai getInteligence() {
        return human;
    }

}
