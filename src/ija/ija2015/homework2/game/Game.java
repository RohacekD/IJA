/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2.game;
import ija.ija2015.homework2.board.Board;

/**
 *
 * @author Honza
 */
public class Game {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    
    public Game (Board board) {
        this.board = board;
        this.whitePlayer = null;
        this.blackPlayer = null; 
        this.currentPlayer = null;
    }
    
    public boolean addPlayer(Player player) {
        if ((this.whitePlayer != null) && (this.blackPlayer != null))
            return false;
        else if (this.blackPlayer == null && player.isWhite() == false) {
            this.blackPlayer = player;
            blackPlayer.init(this.board);
            return true;
        } 
        else if (this.whitePlayer == null && player.isWhite() == true){
            this.whitePlayer = player;
            this.currentPlayer = player; 
            whitePlayer.init(this.board);
            return true;
        }
        return false;
    }
    
    public Player currentPlayer() {
        return this.currentPlayer;
    }
    
    public Player nextPlayer() {
        if (this.currentPlayer == this.blackPlayer) 
            this.currentPlayer = this.whitePlayer;
        else
            this.currentPlayer = this.blackPlayer;
        return this.currentPlayer;
    }
    
    public Board getBoard () {
        return this.board;
    }
    
}
