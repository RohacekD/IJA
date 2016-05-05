/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2;
import ija.ija2015.homework2.game.Game;
import ija.ija2015.homework2.game.Player.Ai;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingConstants;

/**
 *
 * @author Dalibor Jelinek
 */
public class StartFrame extends javax.swing.JFrame {
    DefaultListModel<String> model;
    

    /**
     * Creates new form StartFrame
     */
    public StartFrame() {
        initComponents();
        jScrollPaneLoad.setVisible(false);
        jPanelHelp.setVisible(false);
        newGamePanel.setVisible(true);
        getContentPane().setBackground(Color.DARK_GRAY);
        jPanelHelp.setBackground(Color.DARK_GRAY);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) jListLoad.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jListLoad.setFixedCellHeight(50);
        model = new DefaultListModel<>();
        
        
    }
    
    



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButNewGame = new javax.swing.JButton();
        jButtonLoadGame = new javax.swing.JButton();
        jButHelp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPaneRight = new javax.swing.JLayeredPane();
        newGamePanel = new javax.swing.JPanel();
        blackIcon = new javax.swing.JLabel();
        whiteIcon = new javax.swing.JLabel();
        jComboBoxWhite = new javax.swing.JComboBox();
        jComboBoxBlack = new javax.swing.JComboBox();
        jSliderSize = new javax.swing.JSlider();
        jCheckBoxFreez = new javax.swing.JCheckBox();
        jButtonPlay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelHelp = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPaneLoad = new javax.swing.JScrollPane();
        jListLoad = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reversi - IJA VUT FIT");
        setResizable(false);

        jButNewGame.setFont(new java.awt.Font("Vafle VUT", 0, 18)); // NOI18N
        jButNewGame.setText("NEW GAME");
        jButNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButNewGameActionPerformed(evt);
            }
        });

        jButtonLoadGame.setFont(new java.awt.Font("Vafle VUT", 0, 18)); // NOI18N
        jButtonLoadGame.setText("LOAD GAME");
        jButtonLoadGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadGameActionPerformed(evt);
            }
        });

        jButHelp.setFont(new java.awt.Font("Vafle VUT", 0, 18)); // NOI18N
        jButHelp.setText("HELP");
        jButHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButHelpActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

        jLayeredPaneRight.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLayeredPaneRight.setLayout(new javax.swing.OverlayLayout(jLayeredPaneRight));

        newGamePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        blackIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/black.png"))); // NOI18N
        blackIcon.setText("jLabel3");

        whiteIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/white.png"))); // NOI18N
        whiteIcon.setText("jLabel3");

        jComboBoxWhite.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxWhite.setModel(new javax.swing.DefaultComboBoxModel(Ai.values()));
        jComboBoxWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxWhiteActionPerformed(evt);
            }
        });

        jComboBoxBlack.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxBlack.setModel(new javax.swing.DefaultComboBoxModel(Ai.values()));

        jSliderSize.setMajorTickSpacing(2);
        jSliderSize.setMaximum(12);
        jSliderSize.setMinimum(6);
        jSliderSize.setMinorTickSpacing(2);
        jSliderSize.setSnapToTicks(true);
        jSliderSize.setValue(8);
        jSliderSize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSliderSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderSizeStateChanged(evt);
            }
        });

        jCheckBoxFreez.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jCheckBoxFreez.setText("Disc Freezing");

        jButtonPlay.setFont(new java.awt.Font("Vafle VUT", 0, 18)); // NOI18N
        jButtonPlay.setText("PLAY");
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("8");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Size:");

        javax.swing.GroupLayout newGamePanelLayout = new javax.swing.GroupLayout(newGamePanel);
        newGamePanel.setLayout(newGamePanelLayout);
        newGamePanelLayout.setHorizontalGroup(
            newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newGamePanelLayout.createSequentialGroup()
                .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newGamePanelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newGamePanelLayout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(whiteIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(blackIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9))
                        .addGroup(newGamePanelLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(newGamePanelLayout.createSequentialGroup()
                                    .addComponent(jComboBoxWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBoxBlack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jCheckBoxFreez)))
                        .addGroup(newGamePanelLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSliderSize, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        newGamePanelLayout.setVerticalGroup(
            newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newGamePanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(whiteIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blackIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxWhite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBlack))
                .addGap(21, 21, 21)
                .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jSliderSize, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jCheckBoxFreez)
                .addGap(21, 21, 21)
                .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jLayeredPaneRight.add(newGamePanel);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/help.png"))); // NOI18N

        javax.swing.GroupLayout jPanelHelpLayout = new javax.swing.GroupLayout(jPanelHelp);
        jPanelHelp.setLayout(jPanelHelpLayout);
        jPanelHelpLayout.setHorizontalGroup(
            jPanelHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHelpLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanelHelpLayout.setVerticalGroup(
            jPanelHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLayeredPaneRight.add(jPanelHelp);

        jListLoad.setFont(new java.awt.Font("Vafle VUT", 0, 18)); // NOI18N
        jListLoad.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListLoad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListLoadMouseClicked(evt);
            }
        });
        jScrollPaneLoad.setViewportView(jListLoad);

        jLayeredPaneRight.add(jScrollPaneLoad);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLayeredPaneRight, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPaneRight)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButNewGameActionPerformed
        jScrollPaneLoad.setVisible(false);
        jPanelHelp.setVisible(false);
        newGamePanel.setVisible(true);
    }//GEN-LAST:event_jButNewGameActionPerformed

    private void jButtonLoadGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadGameActionPerformed
        
        ArrayList<String> names = Reversi.getSavedGames();
        model.clear();
        for (String s : names) {
            model.addElement(s);
        }
        jListLoad.setModel(model);
        jListLoad.setSelectedIndex(0);
        jScrollPaneLoad.setVisible(true);
        newGamePanel.setVisible(false);
        jPanelHelp.setVisible(false);

    }//GEN-LAST:event_jButtonLoadGameActionPerformed

    private void jListLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListLoadMouseClicked
        JList list = (JList)evt.getSource();
    if (evt.getClickCount() == 2) {
        int index = list.locationToIndex(evt.getPoint());
        String toLoad = (String)list.getModel().getElementAt(index);
        System.out.println(toLoad);
        Game game = Reversi.loadFromFile(toLoad);
                     java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame(game).setVisible(true);
            }
        });
    }
    }//GEN-LAST:event_jListLoadMouseClicked

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        Game game = Reversi.createNewGame(jSliderSize.getValue(), (Ai) jComboBoxWhite.getSelectedItem(), (Ai) jComboBoxBlack.getSelectedItem());
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame(game).setVisible(true);
            }
        });
        
    }//GEN-LAST:event_jButtonPlayActionPerformed

    private void jSliderSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderSizeStateChanged
        jLabel1.setText(jSliderSize.getValue()+"");
    }//GEN-LAST:event_jSliderSizeStateChanged

    private void jComboBoxWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxWhiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxWhiteActionPerformed

    private void jButHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButHelpActionPerformed
        jScrollPaneLoad.setVisible(false);
        jPanelHelp.setVisible(true);
        newGamePanel.setVisible(false);
    }//GEN-LAST:event_jButHelpActionPerformed

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
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel blackIcon;
    private javax.swing.JButton jButHelp;
    private javax.swing.JButton jButNewGame;
    private javax.swing.JButton jButtonLoadGame;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JCheckBox jCheckBoxFreez;
    private javax.swing.JComboBox jComboBoxBlack;
    private javax.swing.JComboBox jComboBoxWhite;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPaneRight;
    private javax.swing.JList jListLoad;
    private javax.swing.JPanel jPanelHelp;
    private javax.swing.JScrollPane jScrollPaneLoad;
    private javax.swing.JSlider jSliderSize;
    private javax.swing.JPanel newGamePanel;
    private javax.swing.JLabel whiteIcon;
    // End of variables declaration//GEN-END:variables
}
