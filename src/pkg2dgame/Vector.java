/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

/**
 *
 * @author Artmann
 */
public class Vector implements Serializable{
    public float x;
    public float y;
    
    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }
  
    public void paint(Vector startPos, Graphics2D g2d){
        g2d.drawLine((int)startPos.x, (int)startPos.y, (int)startPos.x + (int)x, (int)startPos.y + (int)y);
    }
    public String toString(){
        return "("+x+", "+y+")";
    }
    public static Vector Zero(){
        return new Vector(0,0);
    }
}
