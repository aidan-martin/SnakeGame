/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import environment.Direction;
import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
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
class HauntedHouse extends Environment implements CellDataProviderIntf, MoveValidatorIntf {

    private Grid grid;
    Image background;
    Image startScreen;
    private ArrayList<Barrier> barriers;
    private Screen screens = Screen.START;
    private GhostCharacter casper;


    public HauntedHouse() {
        grid = new Grid(15, 15, 35, 35, new Point(150, 50), Color.BLACK);
        casper = new GhostCharacter(3, 4, Direction.DOWN, this);
        background = ResourceTools.loadImageFromResource("snakegame/blackclouds.jpg");
        startScreen = ResourceTools.loadImageFromResource("snakegame/hauntedhouse2.jpg");
        

//        barriers = new ArrayList<>();
//        barriers.add(new Barrier(0, 0, Color.pink, this));
//        barriers.add(new Barrier(1, 0, Color.pink, this));
//        barriers.add(new Barrier(2, 0, Color.pink, this));
//        barriers.add(new Barrier(3, 0, Color.pink, this));
//        barriers.add(new Barrier(4, 0, Color.pink, this));
//        barriers.add(new Barrier(5, 0, Color.pink, this));
    }

    @Override
    public void initializeEnvironment() {
    }

    private final int limit_SLOW = 8;
    private final int limit_MEDIUM = 5;
    private final int limit_FAST = 3;
    private final int limit_EXTREME = 1;

    private int counter;
    private int limit = limit_MEDIUM;

    @Override
    public void timerTaskHandler() {

        if (casper != null) {

            if (counter < limit) {
                counter++;
            } else {
                casper.move();
                counter = 0;

            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            casper.setDirection(Direction.LEFT);

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            casper.setDirection(Direction.DOWN);

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            casper.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            casper.setDirection(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            this.limit = limit_SLOW;
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            this.limit = limit_MEDIUM;
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            this.limit = limit_FAST;
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            this.limit = limit_EXTREME;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            screens = Screen.PLAY;

        }
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
//        System.out.println("Mouse clicked at " + e.getPoint());
//        System.out.println("Mouse clicked in cell " + grid.getCellLocationFromSystemCoordinate(e.getPoint()));

    }

    @Override
    public void paintEnvironment(Graphics graphics) {

        switch (screens) {
            case START:

                graphics.drawImage(startScreen, 0, 0, 970, 700, this);
                graphics.setFont(new Font("Herculanum", Font.PLAIN, 42));
                graphics.drawString("Press space to start.", 40, 70);

                break;

            case PLAY:

                graphics.drawImage(background, 0, 0, 1000, 800, this);
                 {

                }
                if (grid != null) {
                    grid.paintComponent(graphics);
                }
//                graphics.drawImage(ghost, 200, 300, 80, 80, this);

                if (barriers != null) {
                    for (int i = 0; i < barriers.size(); i++) {
                        barriers.get(i).draw(graphics);
                    }
                    break;
                }
                if (casper != null) {
                    casper.draw(graphics);
                }

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
