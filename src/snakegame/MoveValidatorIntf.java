/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Point;

/**
 *
 * @author aidanmartin
 */
public interface MoveValidatorIntf {
    public Point validateMove(Point proposedLocation);
    
}
