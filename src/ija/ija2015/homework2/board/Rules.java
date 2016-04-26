package ija.ija2015.homework2.board;

/**
 * Rozhraní reprezentující pravidla inicializace hry.
 *
 * @author xpavlu08, xjelin42
 */
public interface Rules {

    /**
     * Vytvoří odpovídající pole na zadaných indexech.
     *
     * @param row Řádek desky.
     * @param col Sloupec desky.
     * @return Vytvořené pole.
     */
    Field createField(int row, int col);

    /**
     * Vrací velikost desky.
     *
     * @return Velikost desky.
     */
    int getSize();

    /**
     * Vrací počet kamenů jednotlivých hráčů.
     *
     * @return Počet kamenů.
     */
    int numberDisks();
}
