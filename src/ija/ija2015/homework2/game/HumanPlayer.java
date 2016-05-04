/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2.game;
import ija.ija2015.homework2.board.Board;
import static ija.ija2015.homework2.game.Player.Ai.human;
import java.io.Serializable;

/**
 *
 * @author Dalibor Jelinek
 */
public class HumanPlayer extends Player implements Serializable{

    public HumanPlayer(boolean isWhite) {
        super(isWhite);
    }
    

    @Override
    public boolean putDisk(Board board) {
        return false;
    }

    @Override
    public Ai getInteligence() {
        return human;
    }
    
    
}
