package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Field;
import java.io.Serializable;

/**
 * Třída reprezentující hráče s umělou inteligencí. Oproti běžnému hráči
 * disponuje možností položit disk pose se znalostí celého hracího pole.
 *
 * @author xjelin42, xpavlu08
 */
public class AiPlayer extends Player implements Serializable{

    private final Ai aiType;
    /**
     * Konstruktor nastaví barvu hráče a typ jeho "inteligence".
     *
     * @param isWhite Barva hráče.
     * @param in Typ inteligence.
     */
    public AiPlayer(boolean isWhite, Ai in) {
        super(isWhite);
        aiType = in;
    }
    

    /**
     * Metoda převezme instanci hry a použije zvolený algoritmus k vyhodnocení
     * pozice disku. Pokud byla pozice nalezena disk je umístěn.
     *
     * @param game Hra
     * @return True pokud byl disk umístěn. Jinak false.
     */
    @Override
    public boolean putDisk(Game game){
        Field tmp;
        switch (aiType){
            case rand:
                tmp = ArtificialIntelligence.randomAI(game.getBoard(), this);
                if(tmp != null) 
                    if (canPutDisk(tmp))
                        return putDisk(tmp);
                else return false;
            case minMax:
                boolean playerColor = game.currentPlayer().white;
                tmp = ArtificialIntelligence.minMaxAI(game.getBoard(), this, 0, game, this.white);
                game.setPlayerWithColor(playerColor);
                if(tmp != null)
                    if (canPutDisk(tmp))
                        return putDisk(tmp);
                else return false;
            default:
                return false;
        }


    }
    
    /**
     * @return vrací typ inteligence
     */
    @Override
    public Ai getInteligence() {
       return aiType;
    }


}
