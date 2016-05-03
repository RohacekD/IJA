package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.board.Field;
import java.util.ArrayList;
import java.util.Random;

/**
 * Třída obsahuje statické metody, které reprezentují jednotlivé algoritmy umělé inteligence.
 * @author xpavlu08, xjelin42
 */
public class ArtificialIntelligence {
    
    /**
     * Náhodný výběr vhodného pole pro tah.
     * @param board Herní deska.
     * @param plr Hráč který má udělat tah.
     * @return Nalezené pole.
     */
    public static Field randomAI(Board board, Player plr){
        ArrayList<Field> legals = plr.getLegals(board);
        if (legals.isEmpty()) {
            return null;
        } else {
            Random rnd = new Random();
            Field tmp = legals.get(rnd.nextInt(legals.size()));
            return tmp;
        }
    }
    
    /**
     * Výber vhodného pole pomocí metody MinMax
     * @param board Herní deska.
     * @param plr
     * @return Nalezené vhodné pole.
     */
    public static Field minMaxAI(Board board, Player plr){
        
        return null;
    }
    
}
