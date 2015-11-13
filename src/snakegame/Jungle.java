/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author aidanmartin
 */
class Jungle extends Environment {

    Grid grid;

    public Jungle() {
        grid = new Grid(15, 15, 35, 35, new Point(150, 50), Color.BLACK);

    }

    @Override
    public void initializeEnvironment() {
    }

    int counter;

    @Override
    public void timerTaskHandler() {
//        System.out.println("Hey dude. " + ++counter);
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Go Left!");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Go Down!");
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Go Up!");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Go Right!");
        }
    }


    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("Go UP!");
       } else if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Go LEFT!");
       } else if(e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("Go DOWN!");
       } else if(e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("GO RIGHT!");
       }

        
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at " + e.getPoint());
        System.out.println("Mouse clicked in cell " + grid.getCellLocationFromSystemCoordinate(e.getPoint()));
        
    }

    @Override
    public void paintEnvironment(Graphics graphics) {

        if (grid != null) {
            grid.paintComponent(graphics);
        }
    }

}
