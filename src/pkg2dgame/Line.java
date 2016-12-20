/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Artmann
 */
public class Line {
    
    public float x1,x2,y1,y2;
    public Color color;
    
    public Line(float x1, float y1, float x2, float y2, Color color){
        this.x1 = x1;
        this.y1 = y1;
        this.x2= x2;
        this.y2 = y2;
        this.color = color;
    }
    public void paint(Graphics2D g2d){
        g2d.setColor(color);
        g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }
}
