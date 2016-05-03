package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.board.Disk;
import ija.ija2015.homework2.board.Field;
import java.util.ArrayList;

/**
 * Reprezentuje hráče hry. Hráč má bílou nebo černou barvu. Po vytvoření
 * reprezentuje volného hráče. Součástí hráče je sada kamenů, které má k
 * dispozici pro vkládání na desku. Volný hráč musí být inicializován v rámci
 * hrací desky.
 *
 * @author xpavlu08, xjelin42
 */
public abstract class Player {
    protected ArrayList<Field> legals;
    protected final boolean white;
    protected Disk[] pool;
    protected int takenFromPool;
    protected int countOfDisk;
    protected boolean initialized;
    protected final ArrayList<Disk> toTurnOver;
    protected boolean alreadyChecked;

    /**
     * Inicializuje hráče.
     *
     * @param isWhite Určuje barvu hráče (true = bílý, false = černý)
     */
    public Player(boolean isWhite) {
        this.white = isWhite;
        pool = null;
        takenFromPool = 0;
        countOfDisk = 0;
        toTurnOver = new ArrayList<>();
        initialized = false;
        alreadyChecked = false;
        legals = new ArrayList<>();
    }

    /**
     * Test barvy hráče.
     *
     * @return Zda je hráč bílý.
     */
    public boolean isWhite() {
        return this.white;
    }

    /**
     * Test, zda je možné vložit nový kámen hráče na dané pole. Kámen se sady
     * nevybírá ani nevkládá na pole.
     *
     * @param field Pole, na které se má vkládat kámen.
     * @return Zda je možné kámen vložit.
     */
    public boolean canPutDisk(Field field) {
        Field.Direction chosenWay;
        Field tmp2;
        boolean result = false;
        int removeFromIndex = 0;
        toTurnOver.clear();

        if (initialized == false) {
            if (field.isEmpty() == true) {
                if (takenFromPool < countOfDisk) {
                    return true;
                }
            }
        } else {
            if (field.isEmpty()) {
                for (Field.Direction dir : Field.Direction.values()) {
                    if (field.nextField(dir).isEmpty() || field.nextField(dir).getDisk() == null
                            || field.nextField(dir).getDisk().isWhite() == this.white) {
                    } else {
                        chosenWay = dir;
                        boolean result_tmp = false;
                        tmp2 = field.nextField(chosenWay);
                        toTurnOver.add(tmp2.getDisk());

                        while (!tmp2.nextField(chosenWay).isEmpty() && tmp2.nextField(chosenWay).canPutDisk(null) != false) {
                            if (tmp2.nextField(chosenWay).getDisk().isWhite() == this.white) {
                                result = true;
                                removeFromIndex = toTurnOver.size();
                                break;
                            } else {
                                tmp2 = tmp2.nextField(chosenWay);
                                toTurnOver.add(tmp2.getDisk());
                            }
                        }
                        if (result_tmp == false) {
                            toTurnOver.subList(removeFromIndex, toTurnOver.size()).clear();
                        }
                    }
                }
            }
        }
        alreadyChecked = true;
        return result;
    }
    
    /**
     * Vratí ArrayList všech polí kam tento hráč může táhnout.
     * @param board Hrací deska.
     * @return ArrayList všech polí kam tento hráč může táhnout.
     */
    public ArrayList<Field> getLegals(Board board){
        ArrayList<Field> legals = new ArrayList<>();
        Field fields[][] = board.getDesk();
        for (int i = 0; i < board.getSize() + 1; i++) {
            for (int j = 0; j < board.getSize() + 1; j++) {
                if(this.canPutDisk(fields[i][j])){
                    legals.add(fields[i][j]);
                }
            }
        }
        this.legals = legals;
        return legals;
    }

    /**
     * Test prázdnosti sady kamenů, které má hráč k dispozici.
     *
     * @return Zda je sada prázdná.
     */
    public boolean emptyPool() {
        return this.pool == null;
    }

    /**
     * Vloží nový kámen hráče na dané pole, pokud to pravidla umožňují. Pokud
     * lze vložit, vybere kámen ze sady a vloží na pole.
     *
     * @param field Pole, na které se vkládá kámen.
     * @return Úspěch akce.
     */
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
        } else if (canPutDisk(field)) {
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
    
    public abstract boolean putDisk(Board board);

    /**
     * Inicializace hráče v rámci hrací desky. Vytvoří sadu kamenů o příslušné
     * velikosti a umístí své počáteční kameny na desku.
     *
     * @param board Deska, v rámci které se hráč inicializuje.
     */
    public void init(Board board) {
        int countOfDisks = board.getRules().numberDisks();
        int firstWhite = board.getSize() / 2;
        Field tmp;
        this.countOfDisk = countOfDisks;
        this.pool = new Disk[countOfDisks];

        for (int i = 0; i < countOfDisks; i++) {
            if (white) {
                this.pool[i] = new Disk(true);
            } else {
                this.pool[i] = new Disk(false);
            }
        }

        if (white) {
            tmp = board.getField(firstWhite, firstWhite);
            putDisk(tmp);
            tmp = board.getField(firstWhite + 1, firstWhite + 1);
            putDisk(tmp);
        } else {
            tmp = board.getField(firstWhite, firstWhite + 1);
            putDisk(tmp);
            tmp = board.getField(firstWhite + 1, firstWhite);
            putDisk(tmp);
        }

        initialized = true;
    }
    
    public abstract Ai getInteligence();

    @Override
    public String toString() {
        String tmp = "";
        for (Field i : legals) {
            tmp +=i.toString()+ " ";
        }
        return this.white == true ? "white|" + tmp  : "black|" + tmp; 

    }
    
    public static enum Ai {

        rand, minMax, human;
    }
    

}
