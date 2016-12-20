/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Artmann
 */
public class Box extends PhysicsObject{
    public int width, height;
    public List<Vector> axis;
    Texture texture = null;
    
    
    public Box(Vector position, int width, int height, Figure figure){
        super(position, figure);
        
        this.width = width;
        this.height = height;
        
        mass = width * height * 10;
        
        setBoundingBox();
    }
    private void setBoundingBox(){
        boundingBox = new Rectangle((int)position.x, (int)position.y, width, height);
        center = new Vector(position.x+boundingBox.width/2, position.y+boundingBox.height/2);
    }
       
    public void paint(Graphics g, Vector drawPos){

       g.setColor(Color.blue);
       g.fillRect((int)drawPos.x, (int)drawPos.y, width, height);
       
    }
}
