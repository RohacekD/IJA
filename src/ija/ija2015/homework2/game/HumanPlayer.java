package ija.ija2015.homework2.game;

import static ija.ija2015.homework2.game.Player.Ai.human;
import java.io.Serializable;

/**
 * Třída reprezentující hráče ovládaného člověkem.
 *
 * @author xjelin42, xpavlu08
 */
public class HumanPlayer extends Player implements Serializable {
    
    /**
     * Konstruktor vytvoří hráče typu člověk.
     *
     * @param isWhite Barva hráče.
     */
    public HumanPlayer(boolean isWhite) {
        super(isWhite);
    }
    
    
    /**
     * Metoda vrací vždy false. Tuto metodu implemetuje AiPlayer.
     *
     * @param game Hra, kterou hráč hraje.
     * @return false
     */
    @Override
    public boolean putDisk(Game game) {
        return false;
    }
    
    
    /**
     * Vrací typ inteligence hráče.
     *
     * @return typ inteligence
     */
    @Override
    public Player.Ai getInteligence() {
        return human;
    }

}
