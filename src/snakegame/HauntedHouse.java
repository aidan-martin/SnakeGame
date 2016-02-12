/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import audio.AudioPlayer;
import audio.Playlist;
import audio.SoundManager;
import audio.Source;
import audio.Track;
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
    Image pauseMenu;
    private final PointSystem score;
    private ArrayList<Barrier> barriers;
    private final ArrayList<Item> items;
    private Screen screen = Screen.START;
    private final GhostCharacter casper;
    private Portal portal_01, portal_02;

    public HauntedHouse() {
        paused = true;

        grid = new Grid(23, 15, 45, 45, new Point(150, 50), Color.BLACK);
        casper = new GhostCharacter(3, 4, Direction.DOWN, this, this);
        score = new PointSystem();
        background = ResourceTools.loadImageFromResource("snakegame/blackclouds.jpg");
        startScreen = ResourceTools.loadImageFromResource("snakegame/hauntedhouse2.jpg");
        pauseMenu = ResourceTools.loadImageFromResource("snakegame/blackclouds_pause.jpg");

        items = new ArrayList<>();
        items.add(new Item(3, 7, Item.ITEM_TYPE_POISON, ResourceTools.loadImageFromResource("snakegame/poison_bottle.png"), this));
        items.add(new Item(12, 5, Item.ITEM_TYPE_POTION, ResourceTools.loadImageFromResource("snakegame/potion.png"), this));
    
        portal_01 = new Portal(5, 9, this);
        portal_02 = new Portal(8, 4, this);
        
        setUpSound();
        
//        soundManager.play(SOUND_INTRO, -1);
        soundManager.play(SOUND_GAMEPLAY, -1);
        soundManager.play(SOUND_PORTAL, -1);
    }
    
    SoundManager soundManager;
    private static final String SOUND_GAMEPLAY = "GamePlay";
    private static final String SOUND_PORTAL = "Portal";
    private static final String SOUND_INTRO = "Intro";

    private void setUpSound() {
        //set up list of tracks in a playlist
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(SOUND_GAMEPLAY, Source.RESOURCE, "/snakegame/gameplay.wav"));
        tracks.add(new Track(SOUND_PORTAL, Source.RESOURCE, "/snakegame/portal.wav"));
        tracks.add(new Track(SOUND_INTRO, Source.RESOURCE, "/snakegame/intro.wav"));

        Playlist playlist = new Playlist(tracks);
        //pass playlist to a soundmanager
        soundManager = new SoundManager(playlist);
    }

    
//<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
    }

    private final int limit_SLOW = 10;
    private final int limit_MEDIUM = 5;
    private final int limit_FAST = 3;
    private final int limit_EXTREME = 1;
    private final int limit_CRAZY = 0;

    private int counter;
    private int limit = limit_MEDIUM;
    private boolean paused = false;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="timerTaskHandler">
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="keyPressedHandler">
    @Override
    public void keyPressedHandler(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            casper.setDirection(Direction.LEFT);
            AudioPlayer.play(SOUND_INTRO);

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
        } else if (e.getKeyCode() == KeyEvent.VK_5) {
            this.limit = limit_CRAZY;

        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            this.paused = !this.paused;    
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            screen = Screen.PLAY;
            soundManager.stop(SOUND_INTRO);
            soundManager.play(SOUND_GAMEPLAY);
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            screen = Screen.MENU;
            
        } 
//           
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="keyReleasedHandler">
    @Override
    public void keyReleasedHandler(KeyEvent e) {

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="environmentMouseClicked">
    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="paintEnvironment">
    @Override
    public void paintEnvironment(Graphics graphics) {

        switch (screen) {
            case START:
                
                graphics.drawImage(startScreen, 0, 0, 1300, 700, this);
                graphics.setFont(new Font("Letter Gothic Std", Font.BOLD, 30));
                //find a nice font to use
                graphics.drawString("CASPER the Friendly Ghost?", 50, 110);
                graphics.setFont(new Font("Letter Gothic Std", Font.BOLD, 20));
                graphics.drawString("Press space to Start...", 50, 130);
                graphics.drawString("Press M for Menu/Instructions...", 50, 150);

                break;

            case MENU:

                graphics.drawImage(pauseMenu, 0, 0, 1300, 800, this);
                graphics.drawString("Difficulty:", 50, 150);
                graphics.drawString("Easy (1) - Medium (2) - Hard (3) - Extreme (4) - Crazy (5)", 50, 170);
                graphics.drawString("(Space to Start)", 50, 190);
                graphics.drawString("(P to Pause)", 50, 210);
                break;

            case PLAY:
                
                graphics.drawImage(background, 0, 0, 1300, 800, this);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("Letter Gothic Std", Font.BOLD, 20));
                graphics.drawString("M / P", 23, 70);

                if (grid != null) {
                    grid.paintComponent(graphics);
                }

                if (barriers != null) {
                    for (int i = 0; i < barriers.size(); i++) {
                        barriers.get(i).draw(graphics);
                    }
                }
                
                if (casper != null) {
                    casper.draw(graphics);
                }
                
                if (items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        items.get(i).draw(graphics);
                    }
                    if (score != null) {
                        score.drawScore(graphics);
                    }
                }
                
                if (portal_01 != null) {
                    portal_01.draw(graphics);
                }

                if (portal_02 != null) {
                    portal_02.draw(graphics);
                }
            
            
        }
//</editor-fold>

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
            proposedLocation.x = grid.getColumns() - 1;
        } else if (proposedLocation.x > grid.getColumns() - 1) {
            proposedLocation.x = 0;
        } else if (proposedLocation.y < 0) {
            proposedLocation.y = grid.getRows() - 1;
        } else if (proposedLocation.y > grid.getRows() - 1) {
            proposedLocation.y = 0;
        }
        checkIntersection(proposedLocation);
        
        //check the portals
        // if landed on portal #1, then move to portal #2, and vice versa
        if (proposedLocation.equals(portal_01.getLocation())) {
            proposedLocation = portal_02.getLocation();
        } else if (proposedLocation.equals(portal_02.getLocation())) {
            proposedLocation = portal_01.getLocation();
        }  
        
        return proposedLocation;
    }

    public Point checkIntersection(Point location) {
        // portal - adjust location?
        if (items != null) {
            for (Item item : items) {
                if (item.getLocation().equals(location)) {
                    if (item.getType().equals(Item.ITEM_TYPE_POISON)) {
                        System.out.println("SUBTRACTING....");
                        score.addPointValue(-100);
                    } else if (item.getType().equals(Item.ITEM_TYPE_POTION)) {
                        System.out.println("ADDING..");
                        score.addPointValue(+100);
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

//randomly generate portals, after gone threw them, move to another location

//randomly generate potions and poison on screen
    //more poison -- harder to get points

//how should the ghost die?
    //an enemy? 
        //something to follow Casper after he reaches 500* points?
    //lives?
        //hearts, falls off the screen loses a life

//SOUND!!! Make a sound manager
    //gameplay music
    //start menu music
    //sound effects when potion/poison picked up
    //sound effects for portal

//if reaches 3000 points, casper poelen's face appears **
    