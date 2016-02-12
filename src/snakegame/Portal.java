/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import images.Animator;
import images.ImageManager;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author aidanmartin
 */
public class Portal {

//<editor-fold defaultstate="collapsed" desc="Drawing">
    public void draw(Graphics graphics) {
        graphics.drawImage(animator.getCurrentImage(),
                getCellData().getSystemCoordX(getX(), getY()),
                getCellData().getSystemCoordY(getX(), getY()),
                getCellData().getCellWidth(),
                getCellData().getCellHeight(), null);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Portal(int x, int y, CellDataProviderIntf cellData) {
        this.x = x;
        this.y = y;
        this.cellData = cellData;
        
        ImageManager im = new ImageManager();
        im.addImage(PORTAL_1, ResourceTools.loadImageFromResource("snakegame/portal_1.png"));
        im.addImage(PORTAL_2, ResourceTools.loadImageFromResource("snakegame/portal_2.png"));
        im.addImage(PORTAL_3, ResourceTools.loadImageFromResource("snakegame/portal_3.png"));
        im.addImage(PORTAL_4, ResourceTools.loadImageFromResource("snakegame/portal_4.png"));
        
        portalImageNames = new ArrayList<>();
        portalImageNames.add(PORTAL_1);
        portalImageNames.add(PORTAL_2);
        portalImageNames.add(PORTAL_3);
        portalImageNames.add(PORTAL_4);
        portalImageNames.add(PORTAL_3);
        portalImageNames.add(PORTAL_2);
        
        animator = new Animator(im, portalImageNames, 300);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private static final String PORTAL_1 = "PORTAL_1";
    private static final String PORTAL_2 = "PORTAL_2";
    private static final String PORTAL_3 = "PORTAL_3";
    private static final String PORTAL_4 = "PORTAL_4";
    
    private ArrayList<String> portalImageNames;
    private Animator animator;
    private CellDataProviderIntf cellData;
    private int y;
    private int x;
    
    /**
     * @return the cellData
     */
    public CellDataProviderIntf getCellData() {
        return cellData;
    }
    
    /**
     * @param cellData the cellData to set
     */
    public void setCellData(CellDataProviderIntf cellData) {
        this.cellData = cellData;
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
     * @return the location
     */
    public Point getLocation() {
        return new Point(x, y);
    }
    
//</editor-fold>
}
