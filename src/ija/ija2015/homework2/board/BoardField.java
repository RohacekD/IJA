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
public class BoardField implements Field{

    private int row;
    private int cols;
    private Disk disk;
    private Field[] surrounding;
    
    public BoardField(int row, int cols) {
        this.row = row;
        this.cols = cols;
        this.disk = null;
        this.surrounding = new Field[8];

        for (int i = 0; i < 8; i++) {
            this.surrounding[i] = null;
        }
    }

    @Override
    public String toString() {
        String tmp;
        tmp =  "Row:" + this.row + ", column:" + this.cols + ", disk:" + this.disk + "\n";
        for (int i = 0; i < 8; i++) {
            tmp = tmp + i +". " + surrounding[i] + "\n";
        }
        return tmp;
    }
    
    @Override
    public void addNextField(Direction dirs, Field field) {
        switch (dirs)
        {
            case D:
                surrounding[0] = field;
                break;
            
            case L:
                surrounding[1] = field;
                break;
                
            case LD:
                surrounding[2] = field;
                break;
                
            case LU:
                surrounding[3] = field;
                break;
                
            case R:
                surrounding[4] = field;
                break;
                
            case RD:
                surrounding[5] = field;
                break;
                
            case RU:
                surrounding[6] = field;
                break;
                
            case U:
                surrounding[7] = field;
                break;
        }
    }

    @Override
    public Field nextField(Direction dirs) {
        switch (dirs)
        {
            case D:
                return surrounding[0];
            
            case L:
                return surrounding[1];
                
            case LD:
                return surrounding[2];
                
            case LU:
                return surrounding[3];
                
            case R:
                return surrounding[4];
                
            case RD:
                return surrounding[5];
                
            case RU:
                return surrounding[6];
                
            case U:
                return surrounding[7];
        }
        return null;
    }

    @Override
    public boolean putDisk(Disk disk) {
        if (this.disk == null) {
            this.disk = disk;
            return true;
        }
        else return false;
    }

    @Override
    public Disk getDisk() {
        return this.disk;
    }
    
    @Override
    public boolean equals(java.lang.Object obj) {
        if (obj instanceof BoardField) {
            BoardField tmp = (BoardField) obj;
            if (this.row == tmp.row && this.cols == tmp.cols) {
                if (this.disk == null) {
                    return tmp.disk == null;
                }
                else {
                    return this.disk.equals(tmp);
                }
            } else return false;
        } else return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.row;
        hash = 47 * hash + this.cols;
        return hash;
    }

    @Override
    public boolean canPutDisk(Disk disk) {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.disk == null; 
    }

}
