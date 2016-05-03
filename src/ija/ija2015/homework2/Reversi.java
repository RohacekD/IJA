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
import java.util.Scanner;

/**
 *
 * @author Dalibor Jelinek
 */
public class Reversi {

    public static Player initPlayer() {
        Scanner sc = new Scanner(System.in);
        boolean color;
        Ai inteligence;

        String input = sc.nextLine();
        switch (input) {
            case "AI": {
                input = sc.nextLine();
                switch (input) {
                    case "B":
                        color = false;
                        inteligence = Ai.rand;
                        break;
                    case "W":
                        color = true;
                        inteligence = Ai.rand;
                        break;
                    default:
                        System.out.println("CHYBA 2");
                        return null;
                }
                return new AiPlayer(color, inteligence);

            }
            case "HI": {
                input = sc.nextLine();
                switch (input) {
                    case "B":
                        color = false;
                        break;
                    case "W":
                        color = true;
                        break;
                    default:
                        System.out.println("CHYBA 3");
                        return null;
                }
                return new HumanPlayer(color);
            }
            default:
                System.out.println("CHYBA 1");
                return null;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte AI|HI a pote B|W");

        int size = 8;
        int tmp;

        ReversiRules rules = new ReversiRules(size);
        Board board = new Board(rules);
        Game game = new Game(board);

       // Player p1 = initPlayer();
       // Player p2 = initPlayer();
        Player p1 = new HumanPlayer(true);
        Player p2 = new AiPlayer(false,Ai.rand);
        

        if (p1 == null || p2 == null) {
            System.out.println("FAIL");
            return;
        }

        p1.init(board);
        p2.init(board);

        game.addPlayer(p1);
        game.addPlayer(p2);
        board.toString();

        while (!game.isEnded()) {
            boolean iCanPlay = true;
            if (game.currentPlayer().getInteligence() == Ai.human) {
                int x, y;
                x = y = 0;
                do {
                    if (game.currentPlayer().getLegals(board).isEmpty()) {
                        System.out.println("Neni kam hrat");
                        game.setOtherCantPlay(true);
                        game.nextPlayer();
                        iCanPlay = false;
                        break;
                    }
                    System.out.println("tahne: "+ game.currentPlayer().toString());
                    System.out.println("Zadej X a pak Y");
                    x = sc.nextInt();
                    y = sc.nextInt();
                } while (!game.currentPlayer().canPutDisk(board.getField(x, y)));
                if (iCanPlay) {
                    game.currentPlayer().putDisk(board.getField(x, y));
                    game.nextPlayer();
                    board.toString();
                }

            } else {
                if (game.currentPlayer().getLegals(board).isEmpty()) {
                    System.out.println("Neni kam hrat");
                    game.setOtherCantPlay(true);
                    game.setOtherCantPlay(true);
                    game.nextPlayer();
                } else {
                    game.currentPlayer().putDisk(board);
                    game.nextPlayer();
                    board.toString();
                }

            }
        }

    }

}
