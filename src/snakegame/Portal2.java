/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author aidanmartin
 */
public class Portal2 {
    
    public void draw(Graphics graphics){
        graphics.drawImage(getImage(),
                getCellData().getSystemCoordX(getX(), getY()),
                getCellData().getSystemCoordY(getX(), getY()),
                getCellData().getCellWidth(),
                getCellData().getCellHeight(), null);
        
    }

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Portal2(int x, int y, String type, Image image,  CellDataProviderIntf cellData){
        this.x = x;
        this.y = y;
        this.type = type;
        this.image = image;
        this.cellData = cellData;
        
//</editor-fold>
}
//<editor-fold defaultstate="collapsed" desc="Properties">

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

//</editor-fold>
}



