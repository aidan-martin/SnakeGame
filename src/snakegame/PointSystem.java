/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


/**
 *
 * @author aidanmartin
 */
public class PointSystem {

    public void drawScore(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Herculanum", Font.BOLD, 40));
        graphics.drawString("POINTS: " + getPointValue(), 30, 90);

    }

    private int pointValue = 0;
    

    /**
     * @return the pointValue
     */
    public int getPointValue() {
        return pointValue;
    }

    /**
     * @param pointValue the pointValue to set
     */
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    /**
     * @param pointValue the pointValue to set
     */
    public void addPointValue(int value) {
        this.pointValue += value;
    }

}
