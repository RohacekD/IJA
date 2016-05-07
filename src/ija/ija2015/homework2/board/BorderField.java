package ija.ija2015.homework2.board;

import java.io.Serializable;

/**
 * Třída reprezentující okrajová (neaktivní) pole desky. Pole neuchovává znalost
 * o svém okolí a nelze ne něj umístit kámen.
 *
 * @author xpavlu08, xjelin42
 */
public class BorderField implements Field, Serializable {

    /**
     * Nedělá nic.
     *
     * @param dirs Směr ve kterém se přidává pole (nebere se v potaz).
     * @param field Přidávané pole (nebere se v potaz).
     */
    @Override
    public void addNextField(Field.Direction dirs, Field field) {
    }

    /**
     * Nedělá nic.
     *
     * @param dirs Směr ve kterém se přidává pole (nebere se v potaz).
     * @return Vždy vrátí null.
     */
    @Override
    public Field nextField(Field.Direction dirs) {
        return null;
    }

    /**
     * Nedělá nic.
     *
     * @param disk Vkládaný kámen (nebere se v potaz).
     * @return Vždy vrátí false.
     */
    @Override
    public boolean putDisk(Disk disk) {
        return false;
    }

    /**
     * Nedělá nic.
     *
     * @return Vždy vrátí null.
     */
    @Override
    public Disk getDisk() {
        return null;
    }

    /**
     * Test, zda je možné vložit na pole kámen.
     *
     * @param disk disk
     * @return Vrací vždy false.
     */
    @Override
    public boolean canPutDisk(Disk disk) {
        return false;
    }

    /**
     * Vrací vždy false.
     *
     * @return false
     */
    @Override
    public boolean isEmpty() {
        return false;
    }
    
    /**
     * Ohodnoceni hraničního políčka je vždy 0
     * @return Vrací ohodnocení (0)
     */
    @Override
    public int getRating() {
        return 0;
    }

    /**
     * Nedělá nic - hraninímu políčkuohodnocení nenastavuji
     * @param value Hodnota.
     */
    @Override
    public void setRating(int value) {
        
    }
    
    /**
     * Záměrně vrací špatnou hodnotu.
     * @return -1
     */
    @Override
    public int getColumn() {
        return -1;
    }
    
    /**
     * Záměrně vrací špatnou hodnotu.
     * @return -1
     */
    @Override
    public int getRow() {
        return -1;
    }
    
    /**
     * nedělá nic
     */
    @Override
    public void setDiskToNull() {
    }

}
