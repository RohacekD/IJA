/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2.game;
import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.board.Disk;
import ija.ija2015.homework2.board.Field;
import java.util.ArrayList;

/**
 *
 * @author Honza
 */
public class Player {
    private boolean white;
    private Disk[] pool;
    private int takenFromPool;
    private int countOfDisk;
    private boolean initialized;
    private ArrayList<Disk> toTurnOver;
    private boolean alreadyChecked;
    
    public Player (boolean isWhite) {
        this.white = isWhite;
        pool = null;
        takenFromPool = 0;
        countOfDisk = 0;
        toTurnOver = new ArrayList<>();
        initialized = false;
        alreadyChecked = false;
    }
    
    public boolean isWhite() {
        return this.white;
    }
    
    public boolean canPutDisk(Field field) {
        Field.Direction chosenWay;
        Field tmp2;
        boolean result = false;
        int removeFromIndex=0;

        
        if (initialized == false) {
            if (field.isEmpty()==true)  {
                if (takenFromPool < countOfDisk)
                    return true;
            }
        }
        else {
            if (field.isEmpty()) {
                for (Field.Direction dir : Field.Direction.values()) {
                    if (field.nextField(dir).isEmpty() || field.nextField(dir).getDisk() == null
                            || field.nextField(dir).getDisk().isWhite() == this.white) {
                        continue;
                    }
                    else {
                        chosenWay = dir;
                        boolean result_tmp = false;
                        tmp2 = field.nextField(chosenWay);
                        toTurnOver.add(tmp2.getDisk());

                        while(!tmp2.nextField(chosenWay).isEmpty() && tmp2.nextField(chosenWay).canPutDisk(null) != false) {
                            if (tmp2.nextField(chosenWay).getDisk().isWhite() == this.white) {
                                result = true;
                                removeFromIndex = toTurnOver.size();
                                break;
                            }
                            else {
                                tmp2 = tmp2.nextField(chosenWay);
                                toTurnOver.add(tmp2.getDisk());
                            }
                        }
                        if (result_tmp == false)
                            toTurnOver.subList(removeFromIndex, toTurnOver.size()).clear();
                    }
                }
            }
        }
        alreadyChecked = true;
        return result;
    }
    
    public boolean emptyPool() {
        if (this.pool == null) {
            return true;
        }
        return false;
    }
    
    public boolean putDisk(Field field) {
        if (alreadyChecked == true) {
            field.putDisk(pool[takenFromPool]);
            takenFromPool++;
            if (toTurnOver != null && !toTurnOver.isEmpty()) {
                for (Disk tmp : toTurnOver) {
                    tmp.turn();
                }
                toTurnOver.clear();
            }
            return true;
        }else if (canPutDisk(field)) {
            field.putDisk(pool[takenFromPool]);
            takenFromPool++;
            if (toTurnOver != null && !toTurnOver.isEmpty()) {
                for (Disk tmp : toTurnOver) {
                    tmp.turn();
                }
                toTurnOver.clear();
            }
            return true;
        }        
        return false;
    }
    
    public void init(Board board) {
        int countOfDisks = board.getRules().numberDisks();
        int firstWhite = board.getSize()/2;
        Field tmp;
        this.countOfDisk = countOfDisks;
        this.pool = new Disk[countOfDisks];
        
        
        for (int i = 0; i < countOfDisks; i++) {
            if (white) {
                this.pool[i] = new Disk(true);
            }
            else {
                this.pool[i] = new Disk(false);
            }
        }
        
        if (white) {
            tmp = board.getField(firstWhite, firstWhite);
            putDisk(tmp);
            tmp = board.getField(firstWhite+1, firstWhite+1);
            putDisk(tmp);
        }
        else {
            tmp = board.getField(firstWhite, firstWhite+1);
            putDisk(tmp);
            tmp = board.getField(firstWhite+1, firstWhite);
            putDisk(tmp);
        }
        
        initialized = true;
    }

    @Override
    public String toString() {
        return this.white == true ? "white" : "black";
    }
    
    
}
