/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import audio.AudioPlayer;
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

    private final Grid grid;
    Image background;
    Image startScreen;
    private final PointSystem score;
    private ArrayList<Barrier> barriers;
    private final ArrayList<Item> items;
    private final ArrayList<Item> portals;
    private Screen screens = Screen.START;
    private final GhostCharacter casper;
    private final int itemsX = 10;
    private final int itemsY = 5;
//    private int portalX = 2;
//    private int portalY = 4;

    private static final String POISON_ITEM = "POISON";
    private static final String POTION_ITEM = "POTION";

    public HauntedHouse() {
        paused = true;

        grid = new Grid(15, 15, 35, 35, new Point(150, 50), Color.BLACK);
        casper = new GhostCharacter(3, 4, Direction.DOWN, this, this);
        score = new PointSystem();
        background = ResourceTools.loadImageFromResource("snakegame/blackclouds.jpg");
        startScreen = ResourceTools.loadImageFromResource("snakegame/hauntedhouse2.jpg");

        items = new ArrayList<>();
        items.add(new Item(itemsX, itemsY, POISON_ITEM, ResourceTools.loadImageFromResource("snakegame/poison_bottle.png"), this));
        items.add(new Item(12, 5, POTION_ITEM, ResourceTools.loadImageFromResource("snakegame/potion.png"), this));

        portals = new ArrayList<>();
//        portals.add(new Portal(portalX, portalY, ));

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
    private boolean paused = false;

    @Override
    public void timerTaskHandler() {
        if (!paused) {
            if (casper != null) {

                if (counter < limit) {
                    counter++;
                } else {
                    casper.move();
                    counter = 0;

                }
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

        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            this.paused = !this.paused;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            screens = Screen.PLAY;

        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            AudioPlayer.play("/snakegame/Portal.wav");
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

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

                if (score != null) {
                    score.drawScore(graphics);

                }
                if (grid != null) {
                    grid.paintComponent(graphics);
                }

                if (barriers != null) {
                    for (int i = 0; i < barriers.size(); i++) {
                        barriers.get(i).draw(graphics);
                    }
                    break;
                }
                if (casper != null) {
                    casper.draw(graphics);
                }
                if (items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        items.get(i).draw(graphics);
                    }
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
        if (proposedLocation.x < 0) {
            System.out.println("LOST TO THE LEFT...");
        } else if (proposedLocation.x >= grid.getColumns()) {
            System.out.println("LOST TO THE RIGHT...");
        } else if (proposedLocation.y >= grid.getRows()) {
            System.out.println("LOST TO THE BOTTOM...");
        } else if (proposedLocation.y < 0) {
            System.out.println("LOST TO THE TOP...");
        }

        return checkIntersection(proposedLocation);
    }

    public Point checkIntersection(Point location) {
        //check if casper is in same cell as item
        //if casper runs into items, items disappear (collect)
        // get points? lose points?
        // portal - adjust location?
        if (items != null) {
            for (Item item : items) {
                if (item.getLocation().equals(location)) {
                    //stepped on an item!
                    //if POISON => subtract points
                    //if POTION => add points
                    if (item.getType().equals(POISON_ITEM)) {
                        System.out.println("SUBTRACTING....");
                    } else if (item.getType().equals(POTION_ITEM)) {
                        System.out.println("ADDING..");
                    }
                    //move item
                    item.setX(-1000);
                    //make a funny noise
                }
            }
        }
        return location;
    }
//</editor-fold>

}
