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
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author aidanmartin
 */
public class Portal{
    Animator animator;
    Image image;
    private CellDataProviderIntf cellData;
    private int y;
    private int x;
    private Image portal1, portal2, portal3, portal4;
    
    
    public void draw(Graphics graphics){
//        graphics.drawImage();
    // ?????????????????????????????       
        
    
    }
    
    public Portal(int x, int y, CellDataProviderIntf cellData){
        this.x =x;
        this.y = y;
        this.cellData = cellData;
        
        ImageManager im = new ImageManager();
        im.addImage(PORTAL_1, ResourceTools.loadImageFromResource("snakegame/portal_1.png"));
        im.addImage(PORTAL_2, ResourceTools.loadImageFromResource("snakegame/portal_1.png"));
        im.addImage(PORTAL_3, ResourceTools.loadImageFromResource("snakegame/portal_1.png"));
        im.addImage(PORTAL_4, ResourceTools.loadImageFromResource("snakegame/portal_1.png"));

        ArrayList<Object> portalImages = new ArrayList<>();
        portalImages.add(PORTAL_1);
        
        animator = new Animator(im, portalImages, 600);
    }      
    
    private static final String PORTAL_1 = "PORTAL_1";
    private static final String PORTAL_2 = "PORTAL_2";
    private static final String PORTAL_3 = "PORTAL_3";
    private static final String PORTAL_4 = "PORTAL_4";

//    private Object getCellData() {
//        return null;
//    }
//    private Object getImage() {
//        return null;
//    }
//    private Object getX() {
//        return null;
//    }
//    private Object getY() {
//      return null;  
//    }
//    private Object getSystemCoordX(){
//        return SystemCoordX;
//    }
//    private Object getSystemCoordY(){
//        return null;
//    }
//    private
    
}