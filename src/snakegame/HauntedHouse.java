/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author aidanmartin
 */
class HauntedHouse extends Environment implements CellDataProviderIntf, MoveValidatorIntf{

   private Grid grid;
   Image ghost;
   Image background;
   private ArrayList<Barrier> barriers;
   


    public HauntedHouse() {
        grid = new Grid(15, 15, 35, 35, new Point(150, 50), Color.BLACK);
        ghost = ResourceTools.loadImageFromResource("snakegame/ghost.gif");
        background = ResourceTools.loadImageFromResource("snakegame/blackclouds.jpg");
        
        barriers = new ArrayList<>();
        barriers.add(new Barrier(0, 0, Color.pink, this));
        barriers.add(new Barrier(1, 0, Color.pink, this));
        barriers.add(new Barrier(2, 0, Color.pink, this));
        barriers.add(new Barrier(3, 0, Color.pink, this));
        barriers.add(new Barrier(4, 0, Color.pink, this));
        barriers.add(new Barrier(5, 0, Color.pink, this));


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
//        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            System.out.println("Go Left!");
//        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            System.out.println("Go Down!");
//        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
//            System.out.println("Go Up!");
//        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            System.out.println("Go Right!");
//        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_W) {
//            System.out.println("Go UP!");
//        } else if (e.getKeyCode() == KeyEvent.VK_A) {
//            System.out.println("Go LEFT!");
//        } else if (e.getKeyCode() == KeyEvent.VK_S) {
//            System.out.println("Go DOWN!");
//        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            System.out.println("GO RIGHT!");
//        }

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at " + e.getPoint());
        System.out.println("Mouse clicked in cell " + grid.getCellLocationFromSystemCoordinate(e.getPoint()));

    }

    @Override
    public void paintEnvironment(Graphics graphics) {

        graphics.drawImage(background, 0, 0, 1000, 800, this);
        {

        }
        if (grid != null) {
            grid.paintComponent(graphics);
        }
        graphics.drawImage(ghost, 200, 300, 80, 80, this);

        if (barriers != null) {
            for (int i = 0; i < barriers.size(); i++) {
                barriers.get(i).draw(graphics);
            }
//            barriers.draw(graphics);
        }
        
//<editor-fold defaultstate="collapsed" desc="CellDataProviderIntf">
    }
    
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }
    
    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }
    
    @Override
    public int getSystemCoordX(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).x;
    }
    
    @Override
    public int getSystemCoordY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;
        
        
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="MoveValidatorIntf">
    @Override
    public Point validateMove(Point proposedLocation) {
       return proposedLocation; 
    }
//</editor-fold>

}
