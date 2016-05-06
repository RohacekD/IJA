/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Disk;
import ija.ija2015.homework2.board.Field;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Honza
 */
public class Undo implements Serializable{
    
    ArrayList<Field>[] fields;
    ArrayList<Disk>[] turnedDisks;
    int putHere;
    
    @SuppressWarnings("unchecked")
    public Undo() {
        fields = (ArrayList<Field>[])new ArrayList[3];
        turnedDisks = (ArrayList<Disk>[]) new ArrayList[3];
        putHere = 0;
        
        for (int i = 0; i < 3; i++) {
            fields[i] = new ArrayList<>();
            turnedDisks[i] = new ArrayList<>();
        }
    }
    
    
    public boolean canUndo() {
        boolean isPossible = false;
        for (int i = 0; i < 3; i++) {
            if (fields[i].size() != 0) isPossible = true;
        }
        return isPossible;
    }
    
    public void addToUndoLists(Field field, ArrayList<Disk> list) {
        if (!this.fields[putHere].isEmpty()) this.fields[putHere].clear();
        if (!this.turnedDisks[putHere].isEmpty()) this.turnedDisks[putHere].clear();
        
        this.fields[putHere].add(field);
        this.turnedDisks[putHere].addAll(list);
        
        this.putHere = (this.putHere + 1) % 3;
    }
    
    
    void setToPrevious() {
        this.putHere--;
        if (this.putHere == -1)
            this.putHere = 2;
    }
    
    public boolean undo(int pos, boolean color, Disk[] pool) {
        setToPrevious();
        
        if (canUndo()) {
            for (Disk turnMe : this.turnedDisks[this.putHere]) {
                turnMe.turn();
            }
            this.turnedDisks[putHere].clear();
            
            for (Field putThatDiskAway : this.fields[this.putHere]) {
                putThatDiskAway.setDiskToNull();
            }
            this.fields[putHere].clear();
            
            pool[pos] = new Disk(color);
            return true;
        }
        else return false;
    }
}
