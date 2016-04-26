package ija.ija2015.homework2.board;

/**
 * Třída reprezentující hrací desku. Deska má velikost (rozměr) N a rozlišuje
 * aktivní a neaktivní pole. Aktivní pole jsou umístěna na řádcích a sloupcích,
 * které jsou číslovány od 1 do N. Neaktivní (okrajová) pole jsou na sloupcích a
 * řádcích indexovaných hodnotami 0 a N+1.
 *
 * @author xpavlu08, xjelin42
 */
public class Board {

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
     * Generuje textový formát hrací desky.
     *
     * @return Textový formát hrací desky.
     */
    @Override
    public String toString() {
        String result = "";
        System.out.println("Ahoj");
        int size = this.getSize();
        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                if (i == 0 || i == size + 1) {
                    result += (j + "\t");
                } else if (j == 0 || j == size + 1) {
                    result += (i + "\t");
                } else if (this.getField(i, j).isEmpty()) {
                    result += "X\t";
                } else if (this.getField(i, j).getDisk().isWhite()) {
                    result += "W\t";
                } else {
                    result += "B\t";
                }
            }
            result += '\n';
        }
        System.out.println(result);
        return result;
    }

}
