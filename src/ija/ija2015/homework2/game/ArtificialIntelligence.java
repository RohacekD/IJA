package ija.ija2015.homework2.game;

import ija.ija2015.homework2.board.Board;
import ija.ija2015.homework2.board.Disk;
import ija.ija2015.homework2.board.Field;
import java.util.ArrayList;
import java.util.Random;


/**
 * Třída obsahuje statické metody, které reprezentují jednotlivé algoritmy umělé inteligence.
 * @author xpavlu08, xjelin42
 */
public class ArtificialIntelligence {
    
    /**
     * Náhodný výběr vhodného pole pro tah.
     * @param board Herní deska.
     * @param plr Hráč který má udělat tah.
     * @return Nalezené pole.
     */
    public static Field randomAI(Board board, Player plr){
        ArrayList<Field> legals = plr.getLegals(board);
        if (legals.isEmpty()) {
            return null;
        } else {
            Random rnd = new Random();
            Field tmp = legals.get(rnd.nextInt(legals.size()));
            return tmp;
        }
    }
    
    
    /**
     * Výber vhodného pole pomocí metody MinMax
     * @param board Herní deska.
     * @param plr   Hráč který má udělat tah.
     * @return Nalezené vhodné pole.
     */
    public static Field minMaxAI(Board board, Player plr, int depth, Game game, boolean color, boolean first) {
        int maxDepth = 5;
       
        // dosahli jsme maximalni hloubky
        if(depth >= maxDepth) return null;
        
        Field finalMove = null;
        
        
        //ziskani policek, kam lze vlozit disk
        ArrayList<Field> moves = plr.getLegals(board);

        
        //prochazim pres vsechny mozne pozice
        for (Field currField : moves) {
             
            //vytvoreni kopie desky - hluboka kopie
            Board boardTmp = new Board(board);
            
            //vlozeni disku na vybranou pozici
            if (! putDiskAi(boardTmp, currField, plr))
                System.out.println("VLOZENI DISKU SE NEZDARILO");
            
           /* System.out.println("-----------DEBUGGING-----------");
            boardTmp.toString();
            System.out.println("-------------------------------");*/
    
            game.setCurrentPlayer(plr);
            //minmax pro druheho hrace
            Field nextMove = minMaxAI(boardTmp, game.nextPlayer(), depth + 1, game, color, false);
            
            //jsem v maximalni hloubce, nebo korenovem listu
            if (nextMove == null) {
                currField.setRating(getHeuristicValue(boardTmp, color));
            }
            //nejsem v korenovem listu - prepisuji hodnotu z potomka
            else {
                currField.setRating(nextMove.getRating());
            }
            
            //pokud zati neni vybrane policko, vyberu aktualni
            if (finalMove == null)
                finalMove = currField;
            //podle hloubky vybiram policko s nejlepsi ohodnocenim
            else {
                if (depth % 2 == 0) {
                    if (finalMove.getRating() < currField.getRating())
                        finalMove = currField;
                }
                else {
                    if (finalMove.getRating() > currField.getRating())
                        finalMove = currField;
                }
            }
        }
        return finalMove;
    }
    
    
    /**
     * Funkce vrací pro každé políčko, zda se na něj může hráč dostat
     * @param f Políčko na desce
     * @param color Barva hrace
     * @return vrací true, pokud se nějakým tahem může hráč dané barvy dostat na dané políčko
     */
    public static boolean canMove(Field f, boolean color) {
        //v pripade prazdneho policka, souperova disku, nebo hrany
        boolean result = false;
        
        if (!f.isEmpty())    return result;
        
        //prochazim pres vsechny smery
        for (Field.Direction dir : Field.Direction.values()) {
            if (f.nextField(dir).isEmpty() || !(f.nextField(dir).canPutDisk(null)) ||
                    f.nextField(dir).getDisk().isWhite() == color) {
            }
            else {
                Field tmp = f.nextField(dir);
                while (!tmp.nextField(dir).isEmpty() && tmp.nextField(dir).canPutDisk(null)) {
                    if (tmp.nextField(dir).getDisk().isWhite() == color)
                        return true;
                    else
                        tmp = tmp.nextField(dir);
                }
            }
        }
        return result;
    }
    
    
    /**
     * Funkce vypočítá mobilitu pro daného hráče
     * @param board Deska, na níž se simuluje tah
     * @param color Barva hrace
     * @return vrací výslednou mobilitu hráče
     */
    public static int getMobility(Board board, boolean color) {
        int mobility = 0;
        for (int i = 1; i < board.getSize() + 1; i++) {
            for (int j = 1; j < board.getSize() + 1; j++) {
                if (canMove((board.getField(i, j)), color)) mobility++;
            }
        }
        return mobility;
    }
    
    
    /**
     * Funkce vypočítá heuristickou funkci pro desku se specifickými rozměry
     * @param board Herní deska 
     * @param evalTable Tabulka s ohodnocením pozic pro dané rozměry desky
     * @param color Barva hráče
     * @return funkce vrací výsledné ohodnocení desky při vložení disku na danou pozici
     */
    public static int getValueForSpecDesk(Board board, int[][] evalTable, boolean color) {
        int posValue = 0; //celkove ohodnoceni zaujatych pozic na desce
        int countOfMyDisks = 0; //pocet mych disku
        int countOfOppenentsDisks = 0; //pocet souperovych disku
        int countOfDiskRes = 0; //celkove ohodnoceni poctu disku na desce (od 100 do - 100)
        int myFrontiers = 0; //me disky, ktere jsou v okoli prazdneho policka
        int oppFrontiers = 0; // souperovy disky v okoli prazdneho policka
        int frontiersRes = 0;  //celkove ohodnoceni disku v okoli prazdneho policka
        int myDisksInCorner = 0; //pocet mych disku v rohu
        int oppDisksInCorner = 0; //pocet souperovych disku v rohu
        int disksInCornerRes = 0; //celkove ohodnoceni rohu
        int myMobility = 0; // pocet moznych pozic pro vlozeni kamene
        int oppMobility = 0; // pocet moznych pozic, kam muze vlozit kamen souper
        int mobilityRes = 0; // celkove ohodnoceni mobility
        int result = 0;
        
        
        //System.out.println("Board pro vyhodnoceni");
        //board.toString();
        for (int i = 1; i < board.getSize() + 1; i++) {
            for (int j = 1; j < board.getSize() + 1; j++) {
                //policko je prazdne - hodnoti se frontier disky
                if (board.getField(i, j).isEmpty()) {
                    for (Field.Direction dir : Field.Direction.values()) {
                        if (!board.getField(i, j).nextField(dir).isEmpty() && 
                                board.getField(i, j).nextField(dir).canPutDisk(null)) {
                            if (board.getField(i, j).nextField(dir).getDisk().isWhite() == color) {
                                myFrontiers++;
                            }
                            else {
                                oppFrontiers++;
                            }
                        }
                    }
                }
                //policko neni prazdne, hodnoti se pozice a pocet disku
                else if (board.getField(i, j).canPutDisk(null)){
                    if (board.getField(i, j).getDisk().isWhite() == color) {
                        posValue += evalTable[i-1][j-1]; //vypocet vyhodnosti pozice
                        countOfMyDisks++; //pocet umistenych disku
                    }
                    else {
                        posValue -= evalTable[i-1][j-1]; // vypocet vyhodnosti pozice pro soupere
                        countOfOppenentsDisks++; //pocet umistenych disku soupere
                    }
                }
            }
        }
        //dava hodnoty 0 - [-]100
        if (countOfMyDisks > countOfOppenentsDisks) 
            countOfDiskRes = 100 * countOfMyDisks / (countOfMyDisks + countOfOppenentsDisks);
        else if (countOfOppenentsDisks > countOfMyDisks)
            countOfDiskRes = -(100 * countOfOppenentsDisks / (countOfMyDisks + countOfOppenentsDisks));
        else
            countOfDiskRes = 0;
        
        //dava hodnoty 0 - [-]100
        if (myFrontiers > oppFrontiers)
            frontiersRes = -(100 * myFrontiers / (myFrontiers + oppFrontiers));
        else if (myFrontiers < oppFrontiers)
            frontiersRes = 100 * myFrontiers / (myFrontiers + oppFrontiers);
        else
            frontiersRes = 0;
        
        //umisteni v rohu
        if (!board.getField(1, 1).isEmpty() && board.getField(1, 1).getDisk().isWhite() == color)
            myDisksInCorner++;
        else if (!board.getField(1, 1).isEmpty() && board.getField(1, 1).getDisk().isWhite() != color)
            oppDisksInCorner++;
        
        if (!board.getField(1, board.getSize()).isEmpty() && 
                board.getField(1, board.getSize()).getDisk().isWhite() == color)
            myDisksInCorner++;
        else if (!board.getField(1, board.getSize()).isEmpty() &&
                board.getField(1, board.getSize()).getDisk().isWhite() != color)
            oppDisksInCorner++;
        
        if (!board.getField(board.getSize(), 1).isEmpty() && 
                board.getField(board.getSize(), 1).getDisk().isWhite() == color)
            myDisksInCorner++;
        else if (!board.getField(board.getSize(), 1).isEmpty() &&
                board.getField(board.getSize(), 1).getDisk().isWhite() != color)
            oppDisksInCorner++;
        
        if (!board.getField(board.getSize(), board.getSize()).isEmpty() && 
                board.getField(board.getSize(), board.getSize()).getDisk().isWhite() == color)
            myDisksInCorner++;
        else if (!board.getField(board.getSize(), board.getSize()).isEmpty() &&
                board.getField(board.getSize(), board.getSize()).getDisk().isWhite() != color)
            oppDisksInCorner++;
        
        disksInCornerRes = 25 * (myDisksInCorner - oppDisksInCorner);
        
        
        // mobilita
        myMobility = getMobility(board, color);
        oppMobility = getMobility(board, !color);
        
        if (myMobility > oppMobility)
            mobilityRes = 100 * myMobility / (myMobility + oppMobility);
        else if (myMobility < oppMobility)
            mobilityRes = -(100 * oppMobility / (myMobility + oppMobility));
        else
            mobilityRes = 0;
        
        //prirazeni vysledne vahy
        result = countOfDiskRes + posValue + 7 * frontiersRes + 80 * disksInCornerRes + 8 * mobilityRes;
        return result;
    }
    
    
    /**
     * Funkce udržuje tabulky výhodnosti pozice pro každý typ desky (6x6, 8x8, 10x10, 12x12)
     * @param board Deska, na níž probíhá hra
     * @param color Barva hráče
     * @return vrací hodnotu výsledku provedení heristické funkce
     */
    public static int getHeuristicValue(Board board, boolean color) {
        int size = board.getSize(); // podle velikosti desky se vybere pole hodnot        
        int result_desk = 0;
        
        /* rozhodovaci tabulky s ohodnocenim */
        int[][] eval_table_v6 = {
            {40, -15, 15, 15, -15, 40},
            {-15, -30, -7, -7, -30, -15},
            {12, -5, 0, 0, -5, 12},
            {12, -5, 0, 0, -5, 12},
            {-15, -30, -7, -7, -30, -15},
            {40, -15, 15, 15, -15, 40},
        };
        
        int[][] eval_table_v8 = {
            {40,  -8,  8,  6,  6,  8,  -8, 40},
            {-8, -24, -4, -3, -3, -4, -24, -8},
            { 8,  -4,  7,  4,  4,  7,  -4,  8},
            { 6,  -3,  4,  0,  0,  4,  -3,  6},
            { 6,  -3,  4,  0,  0,  4,  -3,  6},
            { 8,  -4,  7,  4,  4,  7,  -4,  8},
            {-8, -24, -4, -3, -3, -4, -24, -8},
            {40,  -8,  8,  6,  6,  8,  -8, 40}
        };
        
        int[][] eval_table_v10 = {
            {40, -8, 8, 6, 4, 4, 6, 8, -8, 40},
            {-8, -24, -4, -3, -1, -1, -3, -4, -24, -8},
            {8, -4, 7, 4, 2, 2, 4, 7, -4, 8},
            {6, -3, 4, 2, 1, 1, 2, 4, -3, 6},
            {4, -2, 4, 2, 0, 0, 2, 4, -2, 4},
            {4, -2, 4, 2, 0, 0, 2, 4, -2, 4},
            {6, -3, 4, 2, 1, 1, 2, 4, -3, 6},
            {8, -4, 7, 4, 2, 2, 4, 7, -4, 8},
            {-8, -24, -4, -3, -1, -1, -3, -4, -24, -8},
            {40, -8, 8, 6, 4, 6, 8, -8, 40}
        };
        
        int[][] eval_table_v12 = {
            {40, -8, 8, 6, 4, 2, 2, 4, 6, 8, -8, 40},
            {-8, -24, -4, -3, -1, 0, 0, -1, -3, -4, -24, -8},
            {8, -4, 7, 4, 2, 1, 1, 2, 4, 7, -4, 8},
            {6, -3, 4, 2, 1, 1, 1, 1, 2, 4, -3, 6},
            {4, -2, 4, 2, 1, 0, 0, 1, 2, 4, -2, 4},
            {2, -1, 4, 2, 1, 0, 0, 1, 2, 4, -1, 2},
            {2, -1, 4, 2, 1, 0, 0, 1, 2, 4, -1, 2},
            {4, -2, 4, 2, 1, 0, 0, 1, 2, 4, -2, 4},
            {6, -3, 4, 2, 1, 1, 1, 1, 2, 4, -3, 6},
            {8, -4, 7, 4, 2, 1, 1, 2, 4, 7, -4, 8},
            {-8, -24, -4, -3, -1, 0, 0, -1, -3, -4, -24, -8},
            {40, -8, 8, 6, 4, 2, 2, 4, 6, 8, -8, 40}
        };
        
        //na zaklade rozmeru desky volam funkci pro vypocet hodnoty huristicke funkce 
        switch (size) {
            case 6:
                result_desk = getValueForSpecDesk(board, eval_table_v6, color);
                break;
            case 8:
                result_desk = getValueForSpecDesk(board, eval_table_v8, color);
                break;
            case 10:
                result_desk = getValueForSpecDesk(board, eval_table_v10, color);
                break;
            case 12:
                result_desk = getValueForSpecDesk(board, eval_table_v12, color);
                break;
            default:
                System.out.println("Nepodporovany rozmer desky");
                break;
        }
        
        return result_desk;
    }
    
    /**
     * Metoda vloží disk na konkrétní desku a políčko za vybraného hráče
     * @param board Deska, na níž se vkládá disk
     * @param field Políčko, na které se vkládá disk
     * @param player Hráč, jenž vkládá disk
     * @return vrací true, pokud se podařilo disk vložit
     */
    public static boolean putDiskAi(Board board, Field field, Player player) {
        int row, col;
        row = field.getRow();
        col = field.getColumn();
       /* System.out.println("******Pred vlozenim*******");
        board.toString();
        System.out.println("**************************");*/
        Field fieldInDesk = board.getField(row, col);
        if (player.canPutDisk(fieldInDesk)) {
            ArrayList<Disk> turning = player.getToTurnOver();
            Disk disk = new Disk(player.white);
            fieldInDesk.putDisk(disk);
            for (Disk tmp : turning) {
                tmp.turn();
            }
            return true;
        }
        return false;
    }
}
