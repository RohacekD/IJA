package ija.ija2015.homework2;

import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.game.HumanPlayer;
import ija.ija2015.homework2.game.AiPlayer;
import ija.ija2015.homework2.game.Game;
import ija.ija2015.homework2.game.Player;
import ija.ija2015.homework2.game.Player.Ai;
import ija.ija2015.homework2.game.ReversiRules;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Spustitelná část celé aplikace. Obsahuje metody pro spuštění jak grafické,
 * tak konzolové verze programu. CLI verze nepodporuje zamrzání.
 *
 * @author xjelin42, xpavlu08
 */
public class Reversi {

    /**
     * Tato metoda vytvoří hru pro CLI.
     *
     * @param size velikost hry.
     * @param p1Ai Inteligence bílého hráče.
     * @param p2Ai Inteligence čeného hráče.
     * @return Instance připravené hry.
     */
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

    /**
     * Tato metoda umožní načíst uloženou hru ze souboru.
     *
     * @param name Název uložené hry.
     * @return Načtené hra nebo null v případě selhání.
     */
    public static Game loadFromFile(String name) {
        Game game;
        try {

            FileInputStream fin = new FileInputStream("./saves/" + name);
            ObjectInputStream ois = new ObjectInputStream(fin);
            game = (Game) ois.readObject();
            ois.close();

            return game;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Incompatible file", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static ArrayList<String> getSavedGames() {
        ArrayList<String> result = new ArrayList<>();

        File folder = new File("saves");
        File[] listOfFiles = folder.listFiles();
        if (!folder.isDirectory()) {

            return result;

        }

        for (int i = 0; i < folder.listFiles().length; i++) {
          //  System.out.println(listOfFiles[i].getName());
            if (listOfFiles[i].isFile()) {
                result.add(listOfFiles[i].getName());
            }
        }
        return result;
    }

    /**
     * Metoda se pokusí nastavit vzhled okna a poté spustí grafické menu
     * aplikace.
     */
    public static void runMenu() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame tmp = new StartFrame();
                tmp.setLocationRelativeTo(null);
                tmp.setVisible(true);
                tmp.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
            }
        });
    }

    public static void main(String[] args) {
        // GUI variance
        runMenu();

        //varianta nové hry CLI
        //Game game = createNewGame(6, Ai.human, Ai.rand);
        //varianta načtení ze souboru CLI
        //Game game = loadFromFile("save.sv");
        //game.play();
    }

}
