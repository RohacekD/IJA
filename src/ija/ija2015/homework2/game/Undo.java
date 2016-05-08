package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Disk;
import ija.ija2015.homework2.board.Field;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Třída reprezentující objekt typu Undo.
 *
 * @author xjelin42, xpavlu08
 */
public class Undo implements Serializable{
    
    Field[] field;
    ArrayList<Disk>[] turnedDisks;
    int putHere;
    int depth;
    
    
    /**
     * Konstruktor třídy Undo inicializuje objekt. Player předá objektu počet
     * tahů, které budou zapamatovány.
     *
     * @param depth Hloubka, do které se budou tahy moci revertovat.
     */
    @SuppressWarnings("unchecked")
    public Undo(int depth) {
        field = new Field[depth];
        turnedDisks = (ArrayList<Disk>[]) new ArrayList[depth];
        putHere = 0;
        this.depth = depth;
        
        for (int i = 0; i < depth; i++) {
            turnedDisks[i] = new ArrayList<>();
            field[i] = null;
        }
    }
    
    
    /**
     * Funkce kontroluje, zda můžeme tah navrátit zpět.
     *
     * @return True, poku je možné vrátit tah.
     */
    public boolean canUndo() {
        boolean isPossible = false;
        for (int i = 0; i < depth; i++) {
            if (field[i] != null) isPossible = true;
        }
        return isPossible;
    }
    
    
    /**
     * Funkce přidá do pole turnedDisks seznam disků, které se budou otáčet.
     * Do pole field přidá poslední použité políčko, z něhož se odebere disk.
     * 
     * @param field Políčko, ze kterého se při operaci undo odstraní disk.
     * @param list Seznam disků, které se při operaci undo budou přetáčet.
     */
    public void addToUndoLists(Field field, ArrayList<Disk> list) {
        if (this.field[putHere] != null) this.field[putHere] = null;
        if (!this.turnedDisks[putHere].isEmpty()) this.turnedDisks[putHere].clear();
        
        this.field[putHere] = field;
        this.turnedDisks[putHere].addAll(list);
        
        this.putHere = (this.putHere + 1) % depth;
    }
    
    
    /**
     * Nastaví ukazatel na předchozí prvek v polích s disky a políčky.
     */
    void setToPrevious() {
        this.putHere--;
        if (this.putHere == -1)
            this.putHere = this.depth-1;
    }
    
    
    /**
     * Provede reverzi posledního tahu. Hráči navíc navrátí disk.
     *
     * @param pos Pozice v hráčově poli disků, na kterou se přidělí disk.
     * @param color Barva, kterou bude mít nový disk.
     * @param pool Pole hráčových disků.
     * @return True, pokud se operace undo vydařila.
     */
    public boolean undo(int pos, boolean color, Disk[] pool) {
        setToPrevious();
        
        if (canUndo()) {
            for (Disk turnMe : this.turnedDisks[this.putHere]) {
                turnMe.turn();
            }
            this.turnedDisks[putHere].clear();
            
            this.field[putHere].setDiskToNull();
            
            this.field[putHere] = null;
            
            pool[pos] = new Disk(color);
            return true;
        }
        else return false;
    }
}
