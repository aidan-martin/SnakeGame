/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author aidanmartin
 */
public class Item {

    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(),
                getCellData().getSystemCoordX(getX(), getY()),
                getCellData().getSystemCoordY(getX(), getY()),
                getCellData().getCellWidth(),
                getCellData().getCellHeight(), null);

    }

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Item(Point location, String type, CellDataProviderIntf cellData) {
        this.x = location.x;
        this.y = location.y;
        this.type = type;
        this.cellData = cellData;
        setImage(type);
    }

    public Item(int x, int y, String type, CellDataProviderIntf cellData) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.cellData = cellData;
        setImage(type);
    }

    private void setImage(String type) {
        switch (type) {
            case Item.ITEM_TYPE_POISON:
                image = ResourceTools.loadImageFromResource("snakegame/poison_bottle.png");
                break;
                
            case Item.ITEM_TYPE_PORTAL:
                image = ResourceTools.loadImageFromResource("snakegame/portal_1.png");
                break;
                
            default:
            case Item.ITEM_TYPE_POTION:
                image = ResourceTools.loadImageFromResource("snakegame/potion.png");
                break; 
        }
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final String ITEM_TYPE_PORTAL = "PORTAL";
    public static final String ITEM_TYPE_POTION = "POTION";
    public static final String ITEM_TYPE_POISON = "POISON";

    private int x, y;
    private String type;
    private Image image;
    private final CellDataProviderIntf cellData;

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the cellData
     */
    public CellDataProviderIntf getCellData() {
        return cellData;
    }

    /**
     * @return the location
     */
    public Point getLocation() {
        return new Point(x, y);
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.x = location.x;
        this.y = location.y;
    }
//</editor-fold>

}
