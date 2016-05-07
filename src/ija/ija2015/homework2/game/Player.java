package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.board.Disk;
import ija.ija2015.homework2.board.Field;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Reprezentuje hráče hry. Hráč má bílou nebo černou barvu. Po vytvoření
 * reprezentuje volného hráče. Součástí hráče je sada kamenů, které má k
 * dispozici pro vkládání na desku. Volný hráč musí být inicializován v rámci
 * hrací desky.
 *
 * @author xpavlu08, xjelin42
 */
public abstract class Player implements Serializable{
    protected ArrayList<Field> legals;
    protected final boolean white;
    protected Disk[] pool;
    protected int takenFromPool;
    protected int countOfDisk;
    protected final ArrayList<Disk> toTurnOver;
    protected Undo undo;

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
        legals = new ArrayList<>();
        undo = new Undo(4);
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
        Field.Direction chosenWay; //smer, v nemz se bude tahnout
        Field fieldInDir; //policko ve smeru, jimz pokracuji
        boolean result = false; //promenna, ve ktere bude vysledek funkce
        int removeFromIndex = 0; //index v arraylistu, od nejz se vymazou disky
        
        toTurnOver.clear(); //na zacatku vymazi obsah arraylistu
        
        //mohu vkladat jen na prazdne policko
        if (field.isEmpty()) {
            //prochazim pres vsechny smery
            for (Field.Direction dir : Field.Direction.values()) {
                if (field.nextField(dir).isEmpty() || field.nextField(dir).getDisk() == null
                    || field.nextField(dir).getDisk().isWhite() == this.white 
                    || field.nextField(dir).getDisk().isFreeze()) {
                    //nedelam nic - nelze pokracovat timto smerem
                }
                else { /* nasel jsem smer, jimz mohu pokracovat */
                    chosenWay = dir;
                    fieldInDir = field.nextField(chosenWay);
                    toTurnOver.add(fieldInDir.getDisk()); //pridame policko do seznamu, v nemz jsou disky na otoceni
                    
                    //pokracuji, dokud nenarazim na stenu, prazdne policko, nebo zmrzly disk
                    while (!fieldInDir.nextField(chosenWay).isEmpty() &&
                            fieldInDir.nextField(chosenWay).canPutDisk(null) != false &&
                           !fieldInDir.nextField(chosenWay).getDisk().isFreeze()) {
                        
                        //narazil jsem na disk stejne barvy - mam kompletni sezanm disku k otoceni
                        if (fieldInDir.nextField(chosenWay).getDisk().isWhite() == this.white) {
                            result = true; //nasel jsem minimalne jednu sadu disku k otoceni -> uspech
                            removeFromIndex = toTurnOver.size(); /* posunu index, od nejz by se mazaly disky 
                                                                    pridane do seznamu otacenych disku */
                            break;
                        }
                        else { //disk je opacne barvy, pridam ho do seznamu disku k otoceni a pokracuji
                            fieldInDir = fieldInDir.nextField(chosenWay);
                            toTurnOver.add(fieldInDir.getDisk());
                        }
                    }
                    /* pokud je to mozne, oriznu seznam
                       situace, kdy jsem nasel smer, ve kterem jsou souperovy disky, ale uz jsem nenarazil na
                       disk me barvy, ktery by je uzaviral*/
                    toTurnOver.subList(removeFromIndex, toTurnOver.size()).clear();
                }
            }
        }
        return result;
    }
    
    
    /**
     * Vratí ArrayList všech polí kam tento hráč může táhnout a nastaví aktuální
     * počet bílých a černých disků na hracím poli.
     * @param board Hrací deska.
     * @return ArrayList všech polí kam tento hráč může táhnout.
     */
    public ArrayList<Field> getLegals(Board board){

        ArrayList<Field> legals = new ArrayList<>();
        Field fields[][] = board.getDesk();
        for (int i = 1; i < board.getSize() + 1; i++) {
            for (int j = 1; j < board.getSize() + 1; j++) {
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
        if(takenFromPool>= countOfDisk) return false;
        field.putDisk(pool[takenFromPool]);
        takenFromPool++;
        if (toTurnOver != null && !toTurnOver.isEmpty()) {
            for (Disk tmp : toTurnOver) {
                tmp.turn();
            }
            initUndo(field, toTurnOver);
            toTurnOver.clear();
        }
        return true;
    }
    
    
    /**
     * Abstraktní třída, kterou implementuje AiPlayer - provádí tahy
     * @param game  Aktuální hra
     * @return vrací true, pokul lze vložit disk
     */
    public abstract boolean putDisk(Game game);
    
    
    /**
     * Inicializace hráče v rámci hrací desky. Vytvoří sadu kamenů o příslušné
     * velikosti a umístí své počáteční kameny na desku.
     *
     * @param board Deska, v rámci které se hráč inicializuje.
     */
    public void init(Board board) {
        int firstWhite = board.getSize() / 2;
        Field tmp;
        
        this.countOfDisk = board.getRules().numberDisks();;
        this.pool = new Disk[countOfDisk];

        for (int i = 0; i < countOfDisk; i++) {
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
    }
    
    
    /**
     * Vraci typ inteligence hráče.
     * @return typ inteligence
     */
    public abstract Ai getInteligence();
    
    
    /**
     * Vrátí informaci o barvě hráče.
     * @return string - barva hráče
     */
    @Override
    public String toString() {
        String tmp = "";
        for (Field i : legals) {
            tmp +=i.toString()+ " ";
        }
        return this.white == true ? "white|" + tmp  : "black|" + tmp; 

    }
    
    
    /**
     * Vrací počet disku vybraných z hráčovy zásoby.
     * @return počet použitých disků
     */
    public int getTakenFromPool() {
        return takenFromPool;
    }
    
    
    /**
     * Vrací seynam disků k otočení.
     * @return seznam disků k otočení
     */
    public ArrayList<Disk> getToTurnOver() {
        return toTurnOver;
    }
    
    
    /**
     * Inicializuje objekt typu undo
     * @param field Poslední políčko, na které byl vložen disk
     * @param toTurn Seznam disků, které se otočily
     */
    void initUndo(Field field, ArrayList<Disk> toTurn) {
        this.undo.addToUndoLists(field, toTurn);
    }
    
    
    /**
     * Vrací desku do stavu před posledním tahem.
     * @return true, pokud se podařilo tah vrátit
     */
    public boolean undo() {
        if (this.undo.undo(takenFromPool - 1, this.white, this.pool)) {
            this.takenFromPool--;
            return true;
        }
        return false;
    }
    
    
    /**
     * getter pro Undo
     * @return undo
     */
    public Undo getUndo() {
        return undo;
    }
    
    
    /**
     * výčet typů inteligence 
     */
    public static enum Ai {

        rand, minMax, human;
    }
    

}
