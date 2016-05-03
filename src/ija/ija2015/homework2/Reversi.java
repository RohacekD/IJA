/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2;

import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.game.HumanPlayer;
import ija.ija2015.homework2.game.AiPlayer;
import ija.ija2015.homework2.game.Game;
import ija.ija2015.homework2.game.Player;
import ija.ija2015.homework2.game.Player.Ai;
import ija.ija2015.homework2.game.ReversiRules;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author Dalibor Jelinek
 */
public class Reversi {


    /**
     * 
     * @param game
     * @param name
     * @return 
     */
    public boolean writeToFile(Game game, String name) {
        try {
            File dir = new File("saves");
            dir.mkdir();

            FileOutputStream fout = new FileOutputStream("./saves/" + name);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(game);
            oos.close();
            System.out.println("Done");
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Game loadFromFile(String name){
        Game game = null;
        try {

            FileInputStream fin = new FileInputStream("./saves/"+ name);
            ObjectInputStream ois = new ObjectInputStream(fin);
            game = (Game) ois.readObject();
            ois.close();

            return game;

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Game createNewGame(int size,Ai p1Ai, Ai p2Ai){
        
        Player p1, p2;
        ReversiRules rules = new ReversiRules(size);
        Board board = new Board(rules);
        Game game = new Game(board);
    
        if(p1Ai == Ai.human) p1 = new HumanPlayer(true);
        else p1 = new AiPlayer(true, p1Ai);
        
        if(p2Ai == Ai.human) p2 = new HumanPlayer(false);
        else p2 = new AiPlayer(false, p1Ai);
        
        game.addPlayer(p1);
        game.addPlayer(p2);
        
        board.toString();
        
        return game;
    }
        

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte AI|HI a pote B|W");

        Game game = createNewGame(3,Ai.human,Ai.rand);
        
        game.play();
        

    }

}
