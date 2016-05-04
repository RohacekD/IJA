/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2015.homework2;

import javax.swing.JButton;

/**
 *
 * @author Dalibor Jelinek
 */

public class SituatedButton extends JButton{
    
    private final int x;
    private final int y;

    public SituatedButton(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    public int getXpos(){
        return x;
    }
    
        public int getYpos(){
        return y;
    }
    
    
}
