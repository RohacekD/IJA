package ija.ija2015.homework2.board;

/**
 * Třída reprezentující aktivní pole na hrací desce.
 *
 * @author xpavlu08, xjelin42
 */
public class BoardField implements Field {

    private final int row;
    private final int cols;
    private Disk disk;
    private final Field[] surrounding;

    /**
     * Inicializuje pole.
     *
     * @param row
     * @param cols
     */
    public BoardField(int row, int cols) {
        this.row = row;
        this.cols = cols;
        this.disk = null;
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
        tmp = "Row:" + this.row + ", column:" + this.cols + ", disk:" + this.disk + "\n";
        for (int i = 0; i < 8; i++) {
            tmp = tmp + i + ". " + surrounding[i] + "\n";
        }
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

}
