/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import environment.Direction;
import images.Animator;
import images.ImageManager;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author aidanmartin
 */
public class GhostCharacter {

    public void move() {
        int xNew = x;
        int yNew = y;
        
        if (direction == Direction.LEFT) {
            xNew--;
        } else if (direction == Direction.RIGHT) {
            xNew++;
        } else if (direction == Direction.UP) {
            yNew--;
        } else if (direction == Direction.DOWN) {
            yNew++;
        }

        Point newLoc = moveValidator.validateMove(new Point(xNew, yNew));
        x = newLoc.x;
        y = newLoc.y;
        
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), cellData.getSystemCoordX(x, y),
                cellData.getSystemCoordY(x, y),
                cellData.getCellWidth(),
                cellData.getCellHeight(),
                null);
        
    }

    public GhostCharacter(int x, int y, Direction direction, CellDataProviderIntf cellData, MoveValidatorIntf moveValidator) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.cellData = cellData;
        this.moveValidator = moveValidator;

        
        ImageManager im = new ImageManager();
        im.addImage(GHOST_FRONT_01, ResourceTools.loadImageFromResource("snakegame/ghost_front1.png"));
        im.addImage(GHOST_FRONT_02, ResourceTools.loadImageFromResource("snakegame/ghost_front2.png"));
        im.addImage(GHOST_FRONT_03, ResourceTools.loadImageFromResource("snakegame/ghost_front3.png"));
        
        im.addImage(GHOST_BACK_01, ResourceTools.loadImageFromResource("snakegame/ghost_back7.png"));
        im.addImage(GHOST_BACK_02, ResourceTools.loadImageFromResource("snakegame/ghost_back8.png"));
        im.addImage(GHOST_BACK_03, ResourceTools.loadImageFromResource("snakegame/ghost_back9.png"));
        
        im.addImage(GHOST_LEFT_01, ResourceTools.loadImageFromResource("snakegame/ghost_left4.png"));
        im.addImage(GHOST_LEFT_02, ResourceTools.loadImageFromResource("snakegame/ghost_left5.png"));
        im.addImage(GHOST_LEFT_03, ResourceTools.loadImageFromResource("snakegame/ghost_left6.png"));
        
        im.addImage(GHOST_RIGHT_01, ResourceTools.loadImageFromResource("snakegame/ghost_right10.png"));
        im.addImage(GHOST_RIGHT_02, ResourceTools.loadImageFromResource("snakegame/ghost_right11.png"));
        im.addImage(GHOST_RIGHT_03, ResourceTools.loadImageFromResource("snakegame/ghost_right12.png"));
        
        
        frontImages = new ArrayList<>();
        frontImages.add(GHOST_FRONT_01);
        frontImages.add(GHOST_FRONT_02);
        frontImages.add(GHOST_FRONT_03); 
        
        backImages = new ArrayList<>();
        backImages.add(GHOST_BACK_01);
        backImages.add(GHOST_BACK_02);
        backImages.add(GHOST_BACK_03);
        
        leftImages = new ArrayList<>();
        leftImages.add(GHOST_LEFT_01);
        leftImages.add(GHOST_LEFT_02);
        leftImages.add(GHOST_LEFT_03);
        
        rightImages = new ArrayList<>();
        rightImages.add(GHOST_RIGHT_01);
        rightImages.add(GHOST_RIGHT_02);
        rightImages.add(GHOST_RIGHT_03);
                
        animator = new Animator(im, frontImages, 400);
    }
    
    Animator animator;
    
    private static final String GHOST_FRONT_01 = "GHOST_FRONT_01";
    private static final String GHOST_FRONT_02 = "GHOST_FRONT_03";
    private static final String GHOST_FRONT_03 = "GHOST_FRONT_03";
    private static ArrayList<String> frontImages;
    
    private static final String GHOST_BACK_01 = "GHOST_BACK_01";
    private static final String GHOST_BACK_02 = "GHOST_BACK_02";
    private static final String GHOST_BACK_03 = "GHOST_BACK_03";
    private static ArrayList<String> backImages;
    
    private static final String GHOST_LEFT_01 = "GHOST_LEFT_01";
    private static final String GHOST_LEFT_02 = "GHOST_LEFT_02";
    private static final String GHOST_LEFT_03 = "GHOST_LEFT_03";
    private static ArrayList<String> leftImages;
    
    private static final String GHOST_RIGHT_01 = "GHOST_RIGHT_01";
    private static final String GHOST_RIGHT_02 = "GHOST_RIGHT_02";
    private static final String GHOST_RIGHT_03 = "GHOST_RIGHT_03";
    private static ArrayList<String> rightImages;

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int x;
    private int y;
    private Direction direction;
    private Image image;
    private Image sprite1, sprite2, sprite3, sprite4, sprite5, sprite6, 
                  sprite7, sprite8, sprite9, sprite10, sprite11, sprite12;
    private Point location;
    
    private final CellDataProviderIntf cellData;
    private final MoveValidatorIntf moveValidator;
//    private Point location;
//    private CellDataProviderIntf cellData;


    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {    
        return y;
    }
    
    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
        
        if (direction == Direction.UP) {
            animator.setImageNames(backImages);
        } else if (direction == Direction.DOWN) {
            animator.setImageNames(frontImages);
        } else if (direction == Direction.LEFT) {
            animator.setImageNames(leftImages);
        } else if (direction == Direction.RIGHT) {
            animator.setImageNames(rightImages);
        }
    }

    /**
     * @return the image
     */
    public Image getImage() {
        if (animator != null) {
            return animator.getCurrentImage();
        } else {
            return this.sprite1;
        }
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
//</editor-fold>

    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }
}
