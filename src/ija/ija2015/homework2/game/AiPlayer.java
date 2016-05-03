package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.board.Field;

/**
 *
 * @author Dalibor Jelinek
 */
public class AiPlayer extends Player {

    private final Ai aiType;

    public AiPlayer(boolean isWhite, Ai in) {
        super(isWhite);
        aiType = in;
    }
    
    @Override
    public boolean putDisk(Board board){
        Field tmp;
        switch (aiType){
            case rand:
                tmp = ArtificialIntelligence.randomAI(board, this);
                if(tmp != null) 
                    if (canPutDisk(tmp))
                    return putDisk(tmp);
                else return false;
            case minMax:
                tmp = ArtificialIntelligence.minMaxAI(board, this);
                if(tmp != null) return putDisk(tmp);
                else return false;
            default:
                return false;
        }


    }

    @Override
    public Ai getInteligence() {
       return aiType;
    }
    

}
