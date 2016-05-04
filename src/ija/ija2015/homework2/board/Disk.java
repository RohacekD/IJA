package ija.ija2015.homework2.board;

import java.io.Serializable;

/**
 * Třída reprezentuje jeden kámen. Kámen může nabývat dvou barev - bílá nebo
 * černá.
 *
 * @author xpavlu08, xjelin42
 */
public class Disk implements Serializable{

    //promenna nabyva hodnot true, pokud jde o bilou barvu
    //false znaci cernou barvu
    private boolean isWhite;

    /**
     * Inicializace kamene.
     *
     * @param white Barva kamene.
     */
    public Disk(boolean white) {
        this.isWhite = white;
    }

    public Disk(Disk disk) {
        this.isWhite = disk.isWhite;
    }
    
    /**
     * Otočení (změna barvy) kamene.
     */
    public void turn() {
        this.isWhite = this.isWhite != true;
    }

    /**
     * Test, zda je kámen bílý.
     *
     * @return Vrací true, pokud je kámen bílý.
     */
    public boolean isWhite() {
        return this.isWhite == true;
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (obj instanceof Disk) {
            Disk tmp = (Disk) obj;
            return (this.isWhite == tmp.isWhite);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.isWhite ? 1 : 0);
        return hash;
    }

}
