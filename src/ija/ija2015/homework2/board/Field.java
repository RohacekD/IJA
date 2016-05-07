package ija.ija2015.homework2.board;

/**
 * Rozhraní k polím, které lze umístit na hrací desku. Pole zná své okolí, tj.
 * pole, která ho obklopují ve všech osmi směrech.
 *
 * @author xpavlu08, xjelin42
 */
public interface Field {

    /**
     * Přidá sousední pole field v daném směru dirs.
     *
     * @param dirs Směr ve kterém se přidává pole.
     * @param field Přidávané pole.
     */
    void addNextField(Field.Direction dirs, Field field);

    /**
     * Vrátí sousední pole v daném směru dirs.
     *
     * @param dirs Směr ve kterém se přidává pole.
     * @return Sousední pole v daném směru dirs.
     */
    Field nextField(Field.Direction dirs);

    /**
     * Vloží na pole kámen. Jednou vložený kámen již nelze odebrat.
     *
     * @param disk Vkládaný kámen.
     * @return Vrací úspěšnost akce. Pokud je pole již obsazeno, vrací false.
     */
    boolean putDisk(Disk disk);

    /**
     * Test, zda je možné vložit na pole kámen
     *
     * @param disk Vkládaný kámen.
     * @return Vrací úspěšnost akce.
     */
    boolean canPutDisk(Disk disk);

    /**
     * Vrací kámen, který je vložen na pole.
     *
     * @return Vložený kámen. Pokud je pole prázdné, vrací null.
     */
    Disk getDisk();

    /**
     * Vrací informaci o tom, zda je pole prázdné.
     *
     * @return Vrací TRUE, jestliže je políčko prázdné
     */
    boolean isEmpty();
    
    /**
     * Vrací ohodnocení políčka pro minimax
     * @return Ohodnocení políčka
     */
    int getRating();
    
    /**
     * Nastaví hodnocení políčka pro minimax
     * @param value Hodnota.
     */
    void setRating(int value);
    
    /**
     * vrati cislo sloupce
     * @return cislo sloupce
     */
    int getColumn();
    
    /**
     * vrati cislo radku
     * @return cislo radku
     */
    int getRow();
    
    /**
     * nastavuje disk na NULL - pro ucely UNDO 
     */ 
    void setDiskToNull();
    
    /**
     * Výčtový typ reprezentující okolí (směry) jednotlivých polí.
     */
    public static enum Direction {

        LU, U, RU, R, RD, D, LD, L;

    }
}
