package ija.ija2015.homework2.board;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Třída reprezentující hrací desku. Deska má velikost (rozměr) N a rozlišuje
 * aktivní a neaktivní pole. Aktivní pole jsou umístěna na řádcích a sloupcích,
 * které jsou číslovány od 1 do N. Neaktivní (okrajová) pole jsou na sloupcích a
 * řádcích indexovaných hodnotami 0 a N+1.
 *
 * @author xpavlu08, xjelin42
 */
public class Board implements Serializable {

    private final Field[][] desk;
    private final Rules rules;

    /**
     * Inicializuje desku.
     *
     * @param rules
     */
    public Board(Rules rules) {
        this.rules = rules;
        int size = rules.getSize();
        this.desk = new Field[size + 2][size + 2];

        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                if (i == 0 || j == 0 || i == size + 1 || j == size + 1) {
                    desk[i][j] = new BorderField();
                } else {
                    desk[i][j] = rules.createField(i, j);
                }
            }
        }

        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                desk[i][j].addNextField(Field.Direction.D, desk[i + 1][j]);
                desk[i][j].addNextField(Field.Direction.L, desk[i][j - 1]);
                desk[i][j].addNextField(Field.Direction.LD, desk[i + 1][j - 1]);
                desk[i][j].addNextField(Field.Direction.LU, desk[i - 1][j - 1]);
                desk[i][j].addNextField(Field.Direction.R, desk[i][j + 1]);
                desk[i][j].addNextField(Field.Direction.RD, desk[i + 1][j + 1]);
                desk[i][j].addNextField(Field.Direction.RU, desk[i - 1][j + 1]);
                desk[i][j].addNextField(Field.Direction.U, desk[i - 1][j]);
            }
        }
    }
    
    
    /**
     * Copy konstruktor desky, jenž provádí hlubokou kopii. Využívá se při
     * simulaci pohzbů u minimaxu.
     *
     * @param board deska, která se kopíruje
     */
    public Board(Board board) {
        this.desk = new Field[board.getSize() + 2][board.getSize() + 2];
        this.rules = board.rules;

        for (int i = 0; i < board.getSize() + 2; i++) {
            for (int j = 0; j < board.getSize() + 2; j++) {
                if (i == 0 || j == 0 || i == board.getSize() + 1 || j == board.getSize() + 1) {
                    this.desk[i][j] = new BorderField();
                } else {
                    this.desk[i][j] = new BoardField((BoardField) board.getField(i, j));
                }
            }
        }

        for (int i = 1; i < board.getSize() + 1; i++) {
            for (int j = 1; j < board.getSize() + 1; j++) {
                this.desk[i][j].addNextField(Field.Direction.D, desk[i + 1][j]);
                this.desk[i][j].addNextField(Field.Direction.L, desk[i][j - 1]);
                this.desk[i][j].addNextField(Field.Direction.LD, desk[i + 1][j - 1]);
                this.desk[i][j].addNextField(Field.Direction.LU, desk[i - 1][j - 1]);
                this.desk[i][j].addNextField(Field.Direction.R, desk[i][j + 1]);
                this.desk[i][j].addNextField(Field.Direction.RD, desk[i + 1][j + 1]);
                this.desk[i][j].addNextField(Field.Direction.RU, desk[i - 1][j + 1]);
                this.desk[i][j].addNextField(Field.Direction.U, desk[i - 1][j]);
            }
        }
    }

    
    /**
     * Vrací velikost (rozměr) desky.
     *
     * @return Velikost desky.
     */
    public int getSize() {
        return this.rules.getSize();
    }

    public Field getField(int row, int col) {
        return this.desk[row][col];
    }

    
    /**
     * Vrací objekt pravidel.
     *
     * @return Pravidla inicializace hry.
     */
    public Rules getRules() {
        return this.rules;
    }
    

    /**
     * Vrací 2D pole herních polí.
     *
     * @return Pole všech herních polí.
     */
    public Field[][] getDesk() {
        return desk;
    }
    

    /**
     * Generuje textový formát hrací desky.
     *
     * @return Textový formát hrací desky.
     */
    @Override
    public String toString() {
        String result = "";
       // System.out.println("Ahoj");
        int size = this.getSize();
        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                if (i == 0 || i == size + 1) {
                    result += (j + " ");
                } else if (j == 0 || j == size + 1) {
                    result += (i + " ");
                } else if (this.getField(i, j).isEmpty()) {
                    result += "- ";
                } else if (this.getField(i, j).getDisk().isWhite()) {
                    result += "☻ ";
                } else {
                    result += "☺ ";
                }
            }
            result += '\n';
        }
        System.out.println(result);
        return result;
    }
    

    /**
     *  Zjistí počet kamenů obou hráčů. 
     *
     * @return Vrátí počet bílých a čených kamenů v dvouprvkovém poli ve tvaru
     * {}
     */
    public int[] score() {
        int w, b;
        w = b = 0;
        for (int i = 1; i < this.getSize() + 1; i++) {
            for (int j = 1; j < this.getSize() + 1; j++) {
                if (!this.desk[i][j].isEmpty()) {
                    if (this.desk[i][j].getDisk().isWhite()) {
                        w++;
                    } else {
                        b++;
                    }
                }
            }

        }
        int tmp[] = {w, b};
        return tmp;
    }
    
    
    /**
     * Metoda projde herní pole a všechny aktuálně položené kameny vloží do seznamu.
     * @return Vrací ArrayList všech kamenů na desce. 
     */
    public ArrayList<Disk> disksOnBoard(){
        ArrayList<Disk> dob = new ArrayList<>();
        for (int i = 1; i < this.getSize() + 1; i++) {
            for (int j = 1; j < this.getSize() + 1; j++) {
                if (!this.desk[i][j].isEmpty()) {
                    dob.add(this.desk[i][j].getDisk());
                }
            }

        }
        return dob;
    }

}
