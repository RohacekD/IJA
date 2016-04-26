package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Board;

/**
 * Reprezentuje hru. Při inicializaci se vždy přiřadí hrací deska.
 *
 * @author xpavlu08, xjelin42
 */
public class Game {

    private final Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    /**
     * Inicializuje hru.
     *
     * @param board Hrací deska, která je součástí hry.
     */
    public Game(Board board) {
        this.board = board;
        this.whitePlayer = null;
        this.blackPlayer = null;
        this.currentPlayer = null;
    }

    /**
     * Přidá hráče a současně vyvolá jeho inicializaci. Pokud hráč dané barvy
     * již existuje, nedělá nic a vrací false.
     *
     * @param player Přidávaný hráč.
     * @return Úspěch akce.
     */
    public boolean addPlayer(Player player) {
        if ((this.whitePlayer != null) && (this.blackPlayer != null)) {
            return false;
        } else if (this.blackPlayer == null && player.isWhite() == false) {
            this.blackPlayer = player;
            blackPlayer.init(this.board);
            return true;
        } else if (this.whitePlayer == null && player.isWhite() == true) {
            this.whitePlayer = player;
            this.currentPlayer = player;
            whitePlayer.init(this.board);
            return true;
        }
        return false;
    }

    /**
     * Vrátí aktuálního hráče, který je na tahu.
     *
     * @return Aktuální hráč.
     *
     */
    public Player currentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Změní aktuálního hráče.
     *
     * @return Aktuálně nastavený hráč.
     */
    public Player nextPlayer() {
        if (this.currentPlayer == this.blackPlayer) {
            this.currentPlayer = this.whitePlayer;
        } else {
            this.currentPlayer = this.blackPlayer;
        }
        return this.currentPlayer;
    }

    /**
     * Vrátí hrací desku.
     *
     * @return Hrací deska.
     */
    public Board getBoard() {
        return this.board;
    }

}
