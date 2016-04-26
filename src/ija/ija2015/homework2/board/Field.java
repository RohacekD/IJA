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
public interface Field {
    void addNextField(Field.Direction dirs, Field field);
    Field nextField(Field.Direction dirs);
    boolean putDisk(Disk disk);
    boolean canPutDisk(Disk disk);
    Disk getDisk();
    boolean isEmpty();
    
    public static enum Direction {
        D,
        L,
        LD,
        LU,
        R,
        RD,
        RU,
        U;
    }
}
