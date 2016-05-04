package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.board.Field;
import java.io.Serializable;

/**
 *
 * @author Dalibor Jelinek
 */
public class AiPlayer extends Player implements Serializable{

    private final Ai aiType;

    public AiPlayer(boolean isWhite, Ai in) {
        super(isWhite);
        aiType = in;
    }
    

    
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
                Game gameTmp = new Game(game);
                tmp = ArtificialIntelligence.minMaxAI(game.getBoard(), this, 0, gameTmp, this.white, true);
                System.out.println("puvodni deska");
                game.getBoard().toString();
                if(tmp != null)
                    if (canPutDisk(tmp))
                        return putDisk(tmp);
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
