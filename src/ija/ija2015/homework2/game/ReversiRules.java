package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.BoardField;
import ija.ija2015.homework2.board.Rules;
import ija.ija2015.homework2.board.Field;

/**
 * Třída implementující rozhraní Rules. Reprezentuje pravidla inicializace hry
 * Reversi.
 *
 * @author xpavlu08, xjelin42
 */
public class ReversiRules implements Rules {

    private final int size;

    /**
     * Inicializace pravidel.
     *
     * @param size Velikost hrací desky.
     */
    public ReversiRules(int size) {
        this.size = size;
    }

    /**
     * Vytvoří odpovídající pole na zadaných indexech.
     *
     * @param row Řádek desky.
     * @param col Sloupec desky.
     * @return Vytvořené pole.
     */
    @Override
    public Field createField(int row, int col) {
        Field f = new BoardField(row, col);
        return f;
    }

    /**
     * Vrací velikost desky.
     *
     * @return Velikost desky.
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Vrací počet kamenů jednotlivých hráčů.
     *
     * @return Počet kamenů.
     */
    @Override
    public int numberDisks() {
        return size * size / 2;
    }

}
