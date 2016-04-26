/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework1.board;

/**
 *
 * @author Honza
 */
public class BorderField implements Field{

    @Override
    public void addNextField(Direction dirs, Field field) {
        return;
    }

    @Override
    public Field nextField(Direction dirs) {
        return null;
    }

    @Override
    public boolean putDisk(Disk disk) {
        return false;
    }

    @Override
    public Disk getDisk() {
        return null;
    }
    
}
