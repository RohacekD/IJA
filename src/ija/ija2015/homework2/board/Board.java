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
public class Board {
    private Field[][] desk;
    private Rules rules;
    
    public Board(Rules rules) {
        this.rules = rules;
        int size = rules.getSize();
        this.desk = new Field[size+2][size+2];
        Field tmp;
        
        for (int i = 0; i < size+2; i++) {
            for (int j = 0; j < size+2; j++) {
                if (i == 0 || j == 0 || i == size+1 || j == size+1) {
                    tmp = new BorderField();
                    desk[i][j] = tmp;
                }
                else {
                    tmp = rules.createField(i, j);
                    desk[i][j] = tmp;
                }
            }
        }
        
        for (int i = 1; i < size+1; i++) {
            for (int j = 1; j < size+1; j++) {
                desk[i][j].addNextField(Field.Direction.D, desk[i+1][j]);
                desk[i][j].addNextField(Field.Direction.L, desk[i][j-1]);
                desk[i][j].addNextField(Field.Direction.LD, desk[i+1][j-1]);
                desk[i][j].addNextField(Field.Direction.LU, desk[i-1][j-1]);
                desk[i][j].addNextField(Field.Direction.R, desk[i][j+1]);
                desk[i][j].addNextField(Field.Direction.RD, desk[i+1][j+1]);
                desk[i][j].addNextField(Field.Direction.RU, desk[i-1][j+1]);
                desk[i][j].addNextField(Field.Direction.U, desk[i-1][j]);
            }
        }
    }
    
    public int getSize() {
        return this.rules.getSize();
    }
    
    public Field getField(int row, int col) {
        return this.desk[row][col];
    }
    
    public Rules getRules() {
        return this.rules;
    }

    @Override
    public String toString() {
        String result = "";
        System.out.println("Ahoj");
        int size = this.getSize();
        for (int i = 0; i < size+2; i++) {
            for (int j = 0; j < size+2; j++) {
                if (i == 0  || i == size+1)
                    result += (j+"\t");
                else if (j == 0 || j == size+1)
                    result +=(i+"\t");
                else if (this.getField(i, j).isEmpty())
                    result += "X\t";
                else if (this.getField(i, j).getDisk().isWhite())
                    result += "W\t";
                else
                    result +="B\t";
            }
            result += '\n';
        }
        System.out.println(result);
        return result;
    }
    
   
}
