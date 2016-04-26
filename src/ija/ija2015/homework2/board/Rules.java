/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2.board;

/**
 *
 * @author Honza
 */

public interface Rules {
    Field createField(int row, int col);
    int getSize();
    int numberDisks();
}
