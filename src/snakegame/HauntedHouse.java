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
    private GhostCharacter casper;
    private final Portal portal_01, portal_02;

    public HauntedHouse() {
        paused = true;

        grid = new Grid(23, 12, 45, 45, new Point(150, 50), Color.BLACK);
        casper = new GhostCharacter(3, 4, Direction.DOWN, this, this);
        score = new PointSystem();
        background = ResourceTools.loadImageFromResource("snakegame/blackclouds.jpg");
        startScreen = ResourceTools.loadImageFromResource("snakegame/hauntedhouse2.jpg");
        pauseMenu = ResourceTools.loadImageFromResource("snakegame/blackclouds_pause.jpg");

        items = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            items.add(new Item(getRandomGridLocation(), Item.ITEM_TYPE_POISON, this));
        }

        for (int i = 0; i < 5; i++) {
            items.add(new Item(getRandomGridLocation(), Item.ITEM_TYPE_POTION, this));
        }
        for (int i = 0; i < 1; i++) {
            items.add(new Item(getRandomGridLocation(), Item.ITEM_TYPE_COIN, this));
        }

        portal_01 = new Portal(5, 9, this);
        portal_02 = new Portal(8, 4, this);

        setUpSound();

        soundManager.play(SOUND_INTRO, -1);
    }

//<editor-fold defaultstate="collapsed" desc="getRandomGridLocation">
    public int getRandom(int min, int max) {
        return (int) (min + (Math.random() * (max - min + 1)));
    }
    
    public Point getRandomGridLocation() {
        return new Point(getRandom(0, grid.getColumns() - 1), getRandom(0, grid.getRows() - 1));
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sound Manager">
    SoundManager soundManager;
    private static final String SOUND_GAMEPLAY = "GamePlay";
    private static final String SOUND_PORTAL = "Portal";
    private static final String SOUND_INTRO = "Intro";
    private static final String SOUND_POTION = "Potion";
    private static final String SOUND_COIN = "Coin";

    private void setUpSound() {
        //set up list of tracks in a playlist
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(SOUND_GAMEPLAY, Source.RESOURCE, "/snakegame/Gameplay.wav"));
        tracks.add(new Track(SOUND_PORTAL, Source.RESOURCE, "/snakegame/portal.wav"));
        tracks.add(new Track(SOUND_INTRO, Source.RESOURCE, "/snakegame/Intro.wav"));
        tracks.add(new Track(SOUND_POTION, Source.RESOURCE, "/snakegame/potion.wav"));
        tracks.add(new Track(SOUND_COIN, Source.RESOURCE, "/snakegame/coin.wav"));

        //***** PUT POTION ON PICK UP
        Playlist playlist = new Playlist(tracks);
        //pass playlist to a soundmanager
        soundManager = new SoundManager(playlist);
    }
//</editor-fold>

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
                    counter = +1;
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
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            screen = Screen.PLAY;
        } else if (e.getKeyCode() == KeyEvent.VK_I) {
            screen = Screen.MENU;

        }

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
                graphics.drawString("Press I for Instructions", 55, 135);

                break;

            case MENU:

                graphics.drawImage(pauseMenu, 0, 0, 1300, 800, this);
                graphics.setFont(new Font("Letter Gothic Std", Font.BOLD, 50));
                graphics.drawString("INSTRUCTIONS:", 400, 80);
                graphics.setFont(new Font("Letter Gothic Std", Font.PLAIN, 30));
                graphics.drawString("Collect the BLUE potion bottles to gain points!", 200, 150);
                graphics.drawString("Watch out for the GREEN poison bottles!", 200, 200);
                graphics.drawString("Try going through a PORTAL!", 200, 250);
                graphics.setFont(new Font("Letter Gothic Std", Font.BOLD, 50));
                graphics.drawString("DIFFICULTY:", 410, 350);
                graphics.setFont(new Font("Letter Gothic Std", Font.PLAIN, 30));
                graphics.drawString("Easy (1) - Medium (2) - Hard (3) - Extreme (4) - Crazy (5)", 200, 400);
                graphics.drawString("(Space to Start)", 200, 450);
                graphics.drawString("(P to Pause/Play)", 200, 500);

                break;

            case PLAY:

                graphics.drawImage(background, 0, 0, 1300, 800, this);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("Letter Gothic Std", Font.BOLD, 20));
                graphics.drawString("Menu (M)", 10, 70);
                graphics.drawString("Pause/Play(P)", 10, 90);

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
                    for (Item item : items) {
                        item.draw(graphics);
                    }

                }
                if (score != null) {
                    score.drawScore(graphics);
                }

                if (portal_01 != null) {
                    portal_01.draw(graphics);
                }

                if (portal_02 != null) {
                    portal_02.draw(graphics);
                }

                if (score.getPointValue() < 0) {
                    screen = screen.DEATH;
                }

                break;

            case DEATH:
                //reset score to 0
                //pause casper

                graphics.drawImage(pauseMenu, 0, 0, 1300, 800, this);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("Letter Gothic Std", Font.BOLD, 50));
                graphics.drawString("GAME OVER", 480, 380);
                graphics.setFont(new Font("Letter Gothic Std", Font.BOLD, 25));
                graphics.drawString("SCORE: " + score.getPointValue(), 550, 300);

                break;
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
            screen = Screen.DEATH;
        } else if (proposedLocation.x > grid.getColumns() - 1) {
            screen = Screen.DEATH;
        } else if (proposedLocation.y < 0) {
            screen = Screen.DEATH;
        } else if (proposedLocation.y > grid.getRows() - 1) {
            screen = Screen.DEATH;
        }
        checkIntersection(proposedLocation);

        if (proposedLocation.equals(portal_01.getLocation())) {
            proposedLocation = portal_02.getLocation();
            soundManager.play(SOUND_PORTAL);
            portal_01.setLocation(getRandomGridLocation());
        } else if (proposedLocation.equals(portal_02.getLocation())) {
            proposedLocation = portal_01.getLocation();
            soundManager.play(SOUND_PORTAL);
            portal_02.setLocation(getRandomGridLocation());

        }

        return proposedLocation;
    }

    public Point checkIntersection(Point location) {

        //randomly generate items all over screen
        if (items != null) {
            for (Item item : items) {
                if (item.getLocation().equals(location)) {
                    if (item.getType().equals(Item.ITEM_TYPE_POISON)) {
                        score.addPointValue(-50);
                    } else if (item.getType().equals(Item.ITEM_TYPE_POTION)) {
                        score.addPointValue(+25);
                    } else if (item.getType().equals(Item.ITEM_TYPE_COIN)) {
                        score.addPointValue(+100);
                        soundManager.play(SOUND_COIN);
                    }

                    item.setLocation(getRandomGridLocation());

                }
            }
        }
        return location;
    }
//</editor-fold>

}
