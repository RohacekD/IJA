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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Dalibor Jelinek
 */
public class Reversi {

    public static Game createNewGame(int size, Ai p1Ai, Ai p2Ai) {

        Player p1, p2;
        ReversiRules rules = new ReversiRules(size);
        Board board = new Board(rules);
        Game game = new Game(board);

        if (p1Ai == Ai.human) {
            p1 = new HumanPlayer(true);
        } else {
            p1 = new AiPlayer(true, p1Ai);
        }

        if (p2Ai == Ai.human) {
            p2 = new HumanPlayer(false);
        } else {
            p2 = new AiPlayer(false, p2Ai);
        }

        game.addPlayer(p1);
        game.addPlayer(p2);

        board.toString();

        return game;
    }

        public static Game loadFromFile(String name){
        Game game;
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
        public static ArrayList<String> getSavedGames(){
            ArrayList<String> result = new ArrayList<>();
           
            File folder = new File("saves");
            File[] listOfFiles = folder.listFiles();
            if (!folder.isDirectory()){

                return result;
                
            } 

            for (int i = 0; i < folder.listFiles().length; i++) {
                    System.out.println(listOfFiles[i].getName());
                    if(listOfFiles[i].isFile()) result.add(listOfFiles[i].getName());
            }
            return result;
        }
    public static void runMenu(){
                  try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              
             JFrame tmp =new StartFrame();
             tmp.setLocationRelativeTo(null);
             tmp.setVisible(true);
            
            }
        });
    }
    public static void main(String[] args) {

        runMenu();
        //Game game = createNewGame(6, Ai.human, Ai.rand);
        
        //Game game = loadFromFile("save.sv");

        //game.play();

    }

}
