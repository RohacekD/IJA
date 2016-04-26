package ija.ija2015.homework1.board;

/**
 *
 * @author Honza
 */
public class Disk {
    //promenna nabyva hodnot true, pokud jde o bilou barvu
    //false znaci cernou barvu
    private boolean white;

    public Disk(boolean white) {
        this.white = white;
    }
    
    public void turn() {
        if (this.white == true)
            this.white = false;
        else
            this.white = true;
    }
    
    public boolean isWhite() {
        if (this.white == true)
            return true;
        return false;
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (obj instanceof Disk) {
            Disk tmp = (Disk) obj;
            return (this.white == tmp.white ? true : false);
        } else return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.white ? 1 : 0);
        return hash;
    }
    
    
}
