/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2;

import ija.ija2015.homework2.board.Field;
import ija.ija2015.homework2.game.Game;
import ija.ija2015.homework2.game.Player.Ai;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

/**
 *
 * @author Dalibor Jelinek
 */
public class GameFrame extends javax.swing.JFrame implements ActionListener{
    private final SituatedButton[][] guiBoard;
    private ImageIcon imageBigWhite;
    private ImageIcon imageBigBlack;
    private Game guiGame;
    private boolean ended;
    private boolean otherCantPlay;
    
    private ImageIcon usingBlack;
    private ImageIcon usingWhite;
    private ImageIcon imageMenuBlack;
    private ImageIcon imageMenuWhite;
    private SwingWorker aiWorker;
    
   
    

    /**
     * Creates new form GameFrame
     */
    public GameFrame(Game game) {
        super();
        initComponents();
        guiGame = game;
        int size = game.getBoard().getSize();
        imageBigBlack = new ImageIcon(getClass().getResource("/img/BigBlack.png"));
        imageBigWhite = new ImageIcon(getClass().getResource("/img/BigWhite.png"));
        imageMenuBlack = new ImageIcon(getClass().getResource("/img/black.png"));
        imageMenuWhite = new ImageIcon(getClass().getResource("/img/white.png"));
        aiWorker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    makeMove();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        
        switch(size){
            case 6:  usingBlack = new ImageIcon(imageBigBlack.getImage().getScaledInstance(99, 99, java.awt.Image.SCALE_SMOOTH));
                     usingWhite = new ImageIcon(imageBigWhite.getImage().getScaledInstance(99, 99, java.awt.Image.SCALE_SMOOTH));
                     break;
            case 8:  usingBlack = new ImageIcon(imageBigBlack.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
                     usingWhite = new ImageIcon(imageBigWhite.getImage().getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH));
                     break;
            case 10: usingBlack = new ImageIcon(imageBigBlack.getImage().getScaledInstance(57, 57, java.awt.Image.SCALE_SMOOTH));
                     usingWhite = new ImageIcon(imageBigWhite.getImage().getScaledInstance(57, 57, java.awt.Image.SCALE_SMOOTH));
                     break;
            case 12: usingBlack = new ImageIcon(imageBigBlack.getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
                     usingWhite = new ImageIcon(imageBigWhite.getImage().getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH));
                     break;
                    
        }
        Border emptyBorder = BorderFactory.createEmptyBorder();        
        GridLayout deskGrid = new GridLayout(size,size);
        jPanel1.setLayout(deskGrid);
        guiBoard = new SituatedButton[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SituatedButton tmp = new SituatedButton(i,j);
                jPanel1.add(tmp);
                tmp.setBorder(emptyBorder);
                tmp.addActionListener(this);
                //tmp.setIcon(usingBlack);

                guiBoard[i][j] = tmp;


            }
        }

        jPanel2.setBackground(Color.DARK_GRAY);
        getContentPane().setBackground(Color.DARK_GRAY);
        updateGui(guiGame);
        aiWorker.execute();

    }

    public void makeMove() throws InterruptedException {
        while (guiGame.currentPlayer().getInteligence() != Ai.human && !ended) {
            updateGui(guiGame);
            Thread.sleep(1000);
            if (guiGame.currentPlayer().getLegals(guiGame.getBoard()).isEmpty()) {
                System.out.println("Neni kam hrat UI move");
                if (otherCantPlay) {
                    ended = true;
                    System.out.println("KONEC - UI move");
                }
                guiGame.nextPlayer();
                                if(guiGame.currentPlayer().getLegals(guiGame.getBoard()).isEmpty()||guiGame.currentPlayer().getTakenFromPool() == guiGame.getBoard().getRules().numberDisks()){
                   guiGame.nextPlayer(); 
                   otherCantPlay = true;
                   
                }

            } else if (guiGame.currentPlayer().getTakenFromPool() == guiGame.getBoard().getRules().numberDisks()) {
                System.out.println("Dosly disky!");
                guiGame.nextPlayer();
                if (otherCantPlay) {
                    ended = true;
                    System.out.println("KONEC - UI move");
                    continue;
                }
                otherCantPlay = true;
            } else {
                guiGame.currentPlayer().putDisk(guiGame);
                guiGame.nextPlayer();
                otherCantPlay = false;
                if(guiGame.currentPlayer().getLegals(guiGame.getBoard()).isEmpty()||guiGame.currentPlayer().getTakenFromPool() == guiGame.getBoard().getRules().numberDisks()){
                   guiGame.nextPlayer(); 
                   otherCantPlay = true;
                   
                }

            }

        }
        updateGui(guiGame);

    }
    
    public void makeUserMove(int x, int y) {
        if (guiGame.currentPlayer().getInteligence() == Ai.human && !ended) {
            if (guiGame.currentPlayer().getLegals(guiGame.getBoard()).isEmpty()) {
                System.out.println("Neni kam hrat user move");
                if (otherCantPlay) {
                    ended = true;
                    System.out.println("KONEC - user move");
                } else {
                    guiGame.nextPlayer();
                    otherCantPlay = true;
                    System.out.println("sputil jsem UI");
                                        SwingWorker myWorker = new SwingWorker<String, Void>() {
                        @Override
                        protected String doInBackground() throws Exception {
                            try {
                                makeMove();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            return null;
                        }
                    };
                    myWorker.execute();
                }
                updateGui(guiGame);

            } else if ((guiGame.currentPlayer().getTakenFromPool() == guiGame.getBoard().getRules().numberDisks())) {
                System.out.println("Dosly disky!");
                guiGame.nextPlayer();
                if (otherCantPlay) {
                    ended = true;
                    System.out.println("KONEC - user move");

                }
                guiGame.nextPlayer();
                otherCantPlay = true;
                aiWorker.execute();
                updateGui(guiGame);
            } else {
                otherCantPlay = false;
                if (guiGame.currentPlayer().canPutDisk(guiGame.getBoard().getField(x, y))) {
                    guiGame.currentPlayer().putDisk(guiGame.getBoard().getField(x, y));
                    guiGame.nextPlayer();
                    SwingUtilities.invokeLater(() -> {
                        updateGui(guiGame);
                    });

                    SwingWorker myWorker = new SwingWorker<String, Void>() {
                        @Override
                        protected String doInBackground() throws Exception {
                            try {
                                makeMove();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            return null;
                        }
                    };
                    myWorker.execute();

                }
                

            }

        }
        if(ended)updateGui(guiGame);
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelWhiteLeft = new javax.swing.JLabel();
        jLabelBlackLeft = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelWhiteOnBoard = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelBlackOnBoard = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelNextMove = new javax.swing.JLabel();
        jLabelWinner = new javax.swing.JLabel();
        jButtonUndo = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Reversi - GAME WINDOW");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));
        jPanel1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/white.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/black.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 80));

        jLabelWhiteLeft.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelWhiteLeft.setForeground(new java.awt.Color(204, 204, 204));
        jLabelWhiteLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWhiteLeft.setText("60");
        jLabelWhiteLeft.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelBlackLeft.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelBlackLeft.setForeground(new java.awt.Color(204, 204, 204));
        jLabelBlackLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBlackLeft.setText("60");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Disks left");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Disks left");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("On board");

        jLabelWhiteOnBoard.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelWhiteOnBoard.setForeground(new java.awt.Color(204, 204, 204));
        jLabelWhiteOnBoard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWhiteOnBoard.setText("60");
        jLabelWhiteOnBoard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("On board");

        jLabelBlackOnBoard.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelBlackOnBoard.setForeground(new java.awt.Color(204, 204, 204));
        jLabelBlackOnBoard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBlackOnBoard.setText("60");
        jLabelBlackOnBoard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelNextMove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/white.png"))); // NOI18N
        jLabelNextMove.setText("jLabel1");
        jLabelNextMove.setPreferredSize(new java.awt.Dimension(80, 80));

        jLabelWinner.setFont(new java.awt.Font("Vafle VUT", 0, 24)); // NOI18N
        jLabelWinner.setForeground(new java.awt.Color(204, 204, 204));
        jLabelWinner.setText("next move:");

        jButtonUndo.setText("Undo");
        jButtonUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUndoActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelWhiteLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelWhiteOnBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelBlackOnBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel9)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelBlackLeft, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(52, 52, 52))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNextMove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelWinner)
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelWhiteLeft)
                    .addComponent(jLabelBlackLeft))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBlackOnBoard)
                        .addGap(24, 24, 24)
                        .addComponent(jLabelWinner)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabelNextMove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelWhiteOnBoard)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButtonUndo)
                .addGap(18, 18, 18)
                .addComponent(jButtonSave)
                .addGap(96, 96, 96))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void updateGui(Game game){
        Field board[][] = game.getBoard().getDesk();

        for (int i = 0; i < guiBoard.length; i++) {
            for (int j = 0; j < guiBoard.length; j++) {
                if(!board[i+1][j+1].isEmpty()){
                    if(board[i+1][j+1].getDisk().isWhite()){
                        guiBoard[i][j].setIcon(usingWhite);
                    }
                    else{
                        guiBoard[i][j].setIcon(usingBlack);
                    }     
                }else guiBoard[i][j].setIcon(null);
                
            }
        }
        int count = game.getBoard().getRules().numberDisks();
        jLabelBlackLeft.setText(count-game.getBlackPlayer().getTakenFromPool()+"");
        jLabelWhiteLeft.setText(count-game.getWhitePlayer().getTakenFromPool()+"");
        jLabelWhiteOnBoard.setText(game.getBoard().score()[0]+"");
        jLabelBlackOnBoard.setText(game.getBoard().score()[1]+"");
        if(game.currentPlayer().isWhite()){
            jLabelNextMove.setIcon(imageMenuWhite);
        }
        else jLabelNextMove.setIcon(imageMenuBlack);
        if(ended){
            jLabelWinner.setText("WINNER!!!");
            if(game.getBoard().score()[0]>game.getBoard().score()[1])
                jLabelNextMove.setIcon(imageMenuWhite);
            else jLabelNextMove.setIcon(imageMenuBlack);
        }
    }
    

    
    private void jButtonUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUndoActionPerformed
        guiGame.currentPlayer().undo();
        updateGui(guiGame);
      
    }//GEN-LAST:event_jButtonUndoActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
         String name = JOptionPane.showInputDialog(null,"Insert name of game","Save game",JOptionPane.OK_CANCEL_OPTION);
         
        guiGame.writeToFile(name);
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //  new GameFrame(6).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonUndo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBlackLeft;
    private javax.swing.JLabel jLabelBlackOnBoard;
    private javax.swing.JLabel jLabelNextMove;
    private javax.swing.JLabel jLabelWhiteLeft;
    private javax.swing.JLabel jLabelWhiteOnBoard;
    private javax.swing.JLabel jLabelWinner;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        SituatedButton tmp = (SituatedButton)e.getSource();
        makeUserMove(tmp.getXpos()+1, tmp.getYpos()+1);
        System.out.println(tmp.getXpos()+1+", "+1+tmp.getYpos());
    }
}
