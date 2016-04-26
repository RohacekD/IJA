/*
 * IJA 2015/2016
 * Ukol 1
 */
package ija.ija2015.homework1;

import ija.ija2015.homework1.board.Board;
import ija.ija2015.homework1.board.BoardField;
import ija.ija2015.homework1.board.Disk;
import ija.ija2015.homework1.board.Field;


/**
 *
 * @author koci
 */
public class Homework1 {

    /**
     * Spusti testy.
     */
    public static void main(String[] args) {
        boolean assertOn = false;
        assert assertOn = true;

        if (! assertOn) {
            System.out.println(">! Spustte s prepinacem -ea");
            return ;
        }

        testBoard();
        testDisks();
        testEquals();
        System.out.println("OK");
    }

    /**
     * Test vytvoreni a prace s hraci deskou (vcetne poli).
     */
    private static void testBoard() {
        Board board = new Board(8);

        assert board.getSize() == 8 : "Velikost desky je 8";

        Field f1 = board.getField(0, 0);
        Field f2 = board.getField(1, 5);

        Field n1 = f1.nextField(Field.Direction.RU);
        assert n1 == null       : "Okrajove pole nesmi mit okoli";

        Field n2 = f2.nextField(Field.Direction.LD);
        Field f3 = board.getField(2, 4);
        assert n2.equals(f3)    : "Spravne okoli pole [1,5] ve smeru LD je pole [2,4]";        
    }

    /**
     * Test vytvoreni a prace s kameny (vkladani na pole, otaceni).
     */
    private static void testDisks() {
        Board board = new Board(8);

        assert board.getSize() == 8 : "Velikost desky je 8";

        Field f1 = board.getField(4, 5);
        Field f2 = board.getField(4, 6);

        Disk d1 = new Disk(true);
        Disk d2 = new Disk(false);

        assert f1.putDisk(d1)       : "Kamen je mozne vlozit na pole [4,5]";
        assert ! f1.putDisk(d2)     : "Kamen neni mozne vlozit znovu na pole [4,5]";
        assert f2.putDisk(d2)       : "Kamen je mozne vlozit na pole [4,6]";

        assert f1.nextField(Field.Direction.R).getDisk().equals(d2) : 
                "Kamen vlozeny vpravo od [4,5] je shodny s kamenem na poli [4,6]";

        assert ! f2.getDisk().isWhite()   : "Kamen na pozici [4,6] ma byt cerny";
        f2.getDisk().turn();
        assert f2.getDisk().isWhite()     : "Kamen na pozici [4,6] ma byt bily";
        assert f1.nextField(Field.Direction.R).getDisk().equals(f2.getDisk()) : 
                "Kamen vlozeny vpravo od [4,5] je shodny s kamenem na poli [4,6]";
    }

    /**
     * Testovani shody objektu.
     */
    private static void testEquals() {
        Board board = new Board(8);

        Field f1 = board.getField(4, 5);
        assert f1.equals(new BoardField(4, 5)) : "Pole na stejne pozici jsou shodne";

        Disk d1 = new Disk(true);
        f1.putDisk(d1);
        f1.getDisk().turn();
        assert f1.getDisk().equals(new Disk(false)) : "Cerne kameny jsou shodne";
    }

}
