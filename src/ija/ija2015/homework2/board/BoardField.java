package ija.ija2015.homework2.board;

import java.io.Serializable;

/**
 * Třída reprezentující aktivní pole na hrací desce.
 *
 * @author xpavlu08, xjelin42
 */
public class BoardField implements Field, Serializable {

    private final int row;
    private final int cols;
    private Disk disk;
    private int rating;
    private final Field[] surrounding;

    /**
     * Inicializuje pole.
     *
     * @param row Počet řádků.
     * @param cols Počet sloupců.
     */
    public BoardField(int row, int cols) {
        this.row = row;
        this.cols = cols;
        this.disk = null;
        this.rating = 0;
        this.surrounding = new Field[8];

        for (int i = 0; i < 8; i++) {
            this.surrounding[i] = null;
        }
    }
    
    
    /**
     * Copy konstruktor jedntotlivých políček na desce. Provádí hlubokou kopii.
     * Využívá ho konstruktor Boardu.
     * @param field Políčko ke zkopírování.
     */
    public BoardField(BoardField field) {
        this.row = field.row;
        this.cols = field.cols;
        this.disk = field.disk == null ? null : new Disk(field.disk);
        this.rating = field.rating;
        this.surrounding = new Field[8];

        for (int i = 0; i < 8; i++) {
            this.surrounding[i] = null;
        }
    }

    
    /**
     * Vygeneruje textovou reprezentaci herního pole.
     *
     * @return Textová reprezentace pole.
     */
    @Override
    public String toString() {
        String tmp;
        tmp = "<" + this.row + "," + this.cols + ", disk:" + this.disk + ">";
        return tmp;
    }

    
    /**
     * Přidá sousední pole field v daném směru dirs.
     *
     * @param dirs Směr ve kterém se přidává pole.
     * @param field Přidávané pole.
     */
    @Override
    public void addNextField(Field.Direction dirs, Field field) {
        surrounding[dirs.ordinal()] = field;
    }

    
    /**
     * Vrátí sousední pole v daném směru dirs.
     *
     * @param dirs Směr ve kterém se přidává pole.
     * @return Sousední pole v daném směru dirs.
     */
    @Override
    public Field nextField(Field.Direction dirs) {
        return surrounding[dirs.ordinal()];
    }

    
    /**
     * Vloží na pole kámen. Jednou vložený kámen již nelze odebrat.
     *
     * @param disk Vkládaný kámen.
     * @return Vrací úspěšnost akce. Pokud je pole již obsazeno, vrací false.
     */
    @Override
    public boolean putDisk(Disk disk) {
        if (this.disk == null) {
            this.disk = disk;
            return true;
        } else {
            return false;
        }
    }

    
    /**
     * Vrací kámen, který je vložen na pole.
     *
     * @return Vložený kámen. Pokud je pole prázdné, vrací null.
     */
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
                } else {
                    return this.disk.equals(tmp);
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.row;
        hash = 47 * hash + this.cols;
        return hash;
    }

    
    /**
     * Test, zda je možné vložit na pole kámen.
     *
     * @param disk Vkládaný kámen.
     * @return Vrací úspěšnost akce.
     */
    @Override
    public boolean canPutDisk(Disk disk) {
        return true;
    }
    

    /**
     * Test, zda je pole prázdné.
     *
     * @return Vrací zda je pole prázdné
     */
    @Override
    public boolean isEmpty() {
        return this.disk == null;
    }

    
    /**
     * Získání informací o ohodnocení daného políčka.
     * Využívá minimax.
     * @return ohodnocení políčka
     */
    @Override
    public int getRating() {
        return this.rating;
    }

    
    /**
     * Nastavení ohodnocení políčka. Využívá minimax.
     * @param value Nová hodnota políčka
     */
    @Override
    public void setRating(int value) {
        this.rating = value;
    }

    
    /**
     * Získá sloupec, na němž se políčko nachází.
     * @return číslo sloupce
     */
    @Override
    public int getColumn() {
        return this.cols;
    }
    
    
    /**
     * Získá řádek, na němž se políčko nachází.
     * @return číslo řádku
     */
    @Override
    public int getRow() {
        return this.row;
    }

    
    /**
     * nastaví na políčku disk na NULL
     */
    @Override
    public void setDiskToNull() {
        this.disk = null;
    }

}
