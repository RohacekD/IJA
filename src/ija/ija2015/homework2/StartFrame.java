package ija.ija2015.homework2;

import ija.ija2015.homework2.game.Game;
import ija.ija2015.homework2.game.Player.Ai;
import java.awt.Color;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 * Třída hlavního herního menu.
 *
 * @author xjelin42, xpavlu08
 */
public class StartFrame extends javax.swing.JFrame {

    DefaultListModel<String> model;
    private boolean freez;

    /**
     * Konstruktor inicializuje vzhled okna.
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
        jSpinnerI = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        jSpinnerB = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        jSpinnerC = new javax.swing.JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        jLabelI = new javax.swing.JLabel();
        jLabelB = new javax.swing.JLabel();
        jLabelC = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
        blackIcon.setToolTipText("");

        whiteIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/white.png"))); // NOI18N

        jComboBoxWhite.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxWhite.setModel(new javax.swing.DefaultComboBoxModel(Ai.values()));

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
        jCheckBoxFreez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFreezActionPerformed(evt);
            }
        });

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

        jSpinnerI.setEnabled(false);

        jSpinnerB.setEnabled(false);

        jSpinnerC.setEnabled(false);

        jLabelI.setFont(new java.awt.Font("Constantia", 2, 14)); // NOI18N
        jLabelI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelI.setText("I");
        jLabelI.setEnabled(false);

        jLabelB.setFont(new java.awt.Font("Constantia", 2, 14)); // NOI18N
        jLabelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelB.setText("B");
        jLabelB.setEnabled(false);

        jLabelC.setFont(new java.awt.Font("Constantia", 2, 14)); // NOI18N
        jLabelC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelC.setText("C");
        jLabelC.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("s");
        jLabel5.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("s");
        jLabel6.setEnabled(false);

        javax.swing.GroupLayout newGamePanelLayout = new javax.swing.GroupLayout(newGamePanel);
        newGamePanel.setLayout(newGamePanelLayout);
        newGamePanelLayout.setHorizontalGroup(
            newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newGamePanelLayout.createSequentialGroup()
                .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newGamePanelLayout.createSequentialGroup()
                        .addGap(21, 26, Short.MAX_VALUE)
                        .addComponent(jComboBoxWhite, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jComboBoxBlack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newGamePanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSliderSize, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18))
            .addGroup(newGamePanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(whiteIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(blackIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(newGamePanelLayout.createSequentialGroup()
                .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newGamePanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(newGamePanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jCheckBoxFreez)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelB, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelC, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelI, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerI, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                            .addComponent(jSpinnerB)
                            .addComponent(jSpinnerC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newGamePanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jCheckBoxFreez))
                    .addGroup(newGamePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinnerI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelI)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinnerB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelB)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(newGamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinnerC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelC))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonLoadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3)))
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
    /**
     * Po kliknutí na New Game zobrází jPanel pro spuštění nové hry.
     *
     * @param evt Stisknutí tlačítka New Game.
     */
    private void jButNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButNewGameActionPerformed
        jScrollPaneLoad.setVisible(false);
        jPanelHelp.setVisible(false);
        newGamePanel.setVisible(true);
    }//GEN-LAST:event_jButNewGameActionPerformed
    /**
     * Po kliknutí na Load game zobrazí jScrollPane s výpisem uložených her.
     *
     * @param evt Strisknutí tlačítka Load Game
     */
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
    /**
     * Po dvojkliku na hru se pokusí načíst hru ze souboru. Pokud se načtení
     * zdaří, udělá vytvoří nový GameFrame, zobrazí jej a nastaví mu ikonu.
     *
     * @param evt
     */
    private void jListLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListLoadMouseClicked
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            int index = list.locationToIndex(evt.getPoint());
            String toLoad = (String) list.getModel().getElementAt(index);
            // System.out.println(toLoad);
            Game game = Reversi.loadFromFile(toLoad);
            java.awt.EventQueue.invokeLater(() -> {
                JFrame tmp = new GameFrame(game, false, null);
                tmp.setVisible(true);
                tmp.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
            });
        }
    }//GEN-LAST:event_jListLoadMouseClicked
    /**
     * Získa informace z nastavení a pokud odpovídají kriteriím, spustí hru. V
     * opačném případě vypíše chybové hlášení.
     *
     * @param evt Stisknutí tlačítka Play.
     */
    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        freez = false;
        int fr[] = new int[3];
        if (jCheckBoxFreez.isSelected()) {
            try {
                jSpinnerI.commitEdit();
                jSpinnerB.commitEdit();
                jSpinnerC.commitEdit();
            } catch (ParseException e) {
              //  System.out.println("Incompatible freezing value(s).");
            }
            Integer i = (Integer) jSpinnerI.getValue();
            Integer b = (Integer) jSpinnerB.getValue();
            Integer c = (Integer) jSpinnerC.getValue();
            if (i == 0 || b == 0 || c == 0) {
                JOptionPane.showMessageDialog(null, "Incompatible freezing value(s).\n Allowed values are between 1-1000 second.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if ((Ai) jComboBoxWhite.getSelectedItem() == Ai.minMax || (Ai) jComboBoxBlack.getSelectedItem() == Ai.minMax) {
                JOptionPane.showMessageDialog(null, "Freezing is not allowed in miniMax mode.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                freez = true;
                fr[0] = i;
                fr[1] = b;
                fr[2] = c;
            }
        }

        Game game = Reversi.createNewGame(jSliderSize.getValue(), (Ai) jComboBoxWhite.getSelectedItem(), (Ai) jComboBoxBlack.getSelectedItem());

        java.awt.EventQueue.invokeLater(() -> {
            JFrame tmp = new GameFrame(game, freez, fr);
            tmp.setVisible(true);
            tmp.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
        });

    }//GEN-LAST:event_jButtonPlayActionPerformed
    /**
     * Při změně hodnoty jSlideru vypíše zvolenou hodnotu v sosedním labelu.
     *
     * @param evt Změna hodnoty jSlideru.
     */
    private void jSliderSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderSizeStateChanged
        jLabel1.setText(jSliderSize.getValue()+"");
    }//GEN-LAST:event_jSliderSizeStateChanged
    /**
     * Zobrazí nápovědu.
     *
     * @param evt Stisknutí tlačítka Help.
     */
    private void jButHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButHelpActionPerformed
        jScrollPaneLoad.setVisible(false);
        jPanelHelp.setVisible(true);
        newGamePanel.setVisible(false);
    }//GEN-LAST:event_jButHelpActionPerformed
    /**
     * Pokud je zaškrtnuto / odškrtnuto zamrzání, povolí / zakáže příslušná
     * nastavní.
     *
     * @param evt Změna hodnoty checkboxu pro zamrzání.
     */
    private void jCheckBoxFreezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFreezActionPerformed
          if(jCheckBoxFreez.isSelected()){
            jSpinnerI.setEnabled(true);
            jSpinnerB.setEnabled(true);
            jSpinnerC.setEnabled(true);
            jLabelI.setEnabled(true);
            jLabelB.setEnabled(true);
            jLabelC.setEnabled(true);
            jLabel5.setEnabled(true);
            jLabel6.setEnabled(true);
        }
        else{
            jSpinnerI.setEnabled(false);
            jSpinnerB.setEnabled(false);
            jSpinnerC.setEnabled(false);
            jLabelI.setEnabled(false);
            jLabelB.setEnabled(false);
            jLabelC.setEnabled(false);
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
        }
  
    }//GEN-LAST:event_jCheckBoxFreezActionPerformed

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelB;
    private javax.swing.JLabel jLabelC;
    private javax.swing.JLabel jLabelI;
    private javax.swing.JLayeredPane jLayeredPaneRight;
    private javax.swing.JList jListLoad;
    private javax.swing.JPanel jPanelHelp;
    private javax.swing.JScrollPane jScrollPaneLoad;
    private javax.swing.JSlider jSliderSize;
    private javax.swing.JSpinner jSpinnerB;
    private javax.swing.JSpinner jSpinnerC;
    private javax.swing.JSpinner jSpinnerI;
    private javax.swing.JPanel newGamePanel;
    private javax.swing.JLabel whiteIcon;
    // End of variables declaration//GEN-END:variables
}
