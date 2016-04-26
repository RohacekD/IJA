/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2.game;
import ija.ija2015.homework2.board.BoardField;
import ija.ija2015.homework2.board.Rules;
import ija.ija2015.homework2.board.Field;



/**
 *
 * @author Honza
 */
public class ReversiRules implements Rules{
    
    private int size;
    
    public ReversiRules(int size) {
        this.size = size;
    }
    
    @Override
    public Field createField(int row, int col) {
        Field f = new BoardField(row, col);
        return f;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberDisks() {
        return size*size/2;
    }
    
}
