/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.util.LinkedList;

/**
 *
 * @author Artmann
 */
public class PhysicsMath {
    Line h1X;
    Line h2X;
    Line hDiff;
    Line h1Y;
    Line h2Y;
    Line hDiffY;    
    
    public void handleRectangles(PhysicsObject obj1, PhysicsObject obj2){
        boolean isLeft = false;
        boolean isTop = false;
        
        boolean moveX = false;
        boolean moveY = false;

        float x, y, x1, x2, y1, y2;
        
        if(obj1.center.y <= obj2.center.y){
            isTop = true;
        }
        if(obj1.center.x <= obj2.center.x){
            isLeft = true;
        }
        
        if(isLeft){
                
                x1 = obj1.boundingBox.x + obj1.boundingBox.width/2;
                x2 = obj2.boundingBox.x + obj2.boundingBox.width/2;
        
                h1X = new Line(x1, 130,x1 + obj1.boundingBox.width/2, 130,Color.red);
                h2X = new Line(x2, 130, x2 - obj2.boundingBox.width/2, 130, Color.red);

                hDiff = new Line(h1X.x2, 140, h2X.x2, 140, Color.orange);
                
                x = hDiff.x1 - hDiff.x2;
                
                if(hDiff.x1 >= hDiff.x2){
                    moveX = true;
                }
            }
            else
            {
                x1 = obj1.boundingBox.x + obj1.boundingBox.width/2;
                x2 = obj2.boundingBox.x + obj2.boundingBox.width/2;
                
                h1X = new Line(x1, 130,x1 - obj1.boundingBox.width/2, 130,Color.red);              
                h2X = new Line(x2, 130, x2 + obj2.boundingBox.width/2, 130, Color.red);

                hDiff = new Line(h2X.x2, 140, h1X.x2, 140, Color.orange);
                                
                x = hDiff.x1 - hDiff.x2;
                
                if(hDiff.x2 <= hDiff.x1){
                    moveX = true; 
                    //obj1.position.x += x;
                }
            }
        
        if(isTop){
            
            y1 = obj1.boundingBox.y + obj1.boundingBox.height/2;
            y2 = obj2.boundingBox.y + obj2.boundingBox.height/2;
            
            h1Y = new Line(50, y1, 50, y1 + obj1.boundingBox.height/2,Color.red);
            h2Y = new Line(50, y2, 50, y2 - obj2.boundingBox.height/2,Color.red);
            
            hDiffY = new Line(60, h1Y.y2, 60, h2Y.y2, Color.orange);

            y = hDiffY.y1 - hDiffY.y2;
            
            if(hDiffY.y2 <= hDiffY.y1){
                    moveY = true;
                    //obj1.position.y -= y;
            }
        }
        else{
            
            y1 = obj1.boundingBox.y + obj1.boundingBox.height/2;
            y2 = obj2.boundingBox.y + obj2.boundingBox.height/2;
            
            h1Y = new Line(50, y1, 50, y1 - obj1.boundingBox.height/2,Color.red);
            h2Y = new Line(50, y2, 50, y2 + obj2.boundingBox.height/2,Color.red);
            
            hDiffY = new Line(60, h1Y.y2, 60, h2Y.y2, Color.orange);
            
            y = hDiffY.y2 - hDiffY.y1;
 
            if(hDiffY.y1 <= hDiffY.y2){
                    moveY = true;
                    //obj1.position.y += y;
            }
        }
       
        if((moveX) && (moveY)){
            if(x < y){
                if(isLeft){
                    obj1.position.x -= x;
                }
                else{
                    obj1.position.x += x;
                }
                //momentum preservation
                if(!obj2.isStatic){
                    obj2.velocity.x = getCollisionVelocity(obj1, obj2, true).x;
                }
                obj1.velocity.x = 0;
            }
            else
            {
                if(isTop){
                    obj1.position.y -= y;
                }
                else{
                    obj1.position.y += y;
                }
                if(!obj2.isStatic){
                    obj2.velocity.y = getCollisionVelocity(obj1, obj2, true).y;
                }
                obj1.velocity.y = 0; 
            }
        }
    }
    public void checkForGround(PhysicsObject obj1, PhysicsObject obj2){
        boolean isLeft = false;
        boolean isTop = false;
        
        boolean moveX = false;
        boolean moveY = false;
      
        float x, y, x1, x2, y1, y2;
        
        int offset = 20;
        obj1.boundingBox.height += offset;
        
        if(obj1.center.y <= obj2.center.y){
            isTop = true;
        }
        if(obj1.center.x <= obj2.center.x){
            isLeft = true;
        }
        if(isLeft){
                x1 = obj1.boundingBox.x + obj1.boundingBox.width/2;
                x2 = obj2.boundingBox.x + obj2.boundingBox.width/2;
        
                h1X = new Line(x1, 130,x1 + obj1.boundingBox.width/2, 130,Color.red);
                h2X = new Line(x2, 130, x2 - obj2.boundingBox.width/2, 130, Color.red);

                hDiff = new Line(h1X.x2, 140, h2X.x2, 140, Color.orange);
                
                x = hDiff.x1 - hDiff.x2;
                
                if(hDiff.x1 >= hDiff.x2){
                    moveX = true;
                }
            }
            else
            {
                x1 = obj1.boundingBox.x + obj1.boundingBox.width/2;
                x2 = obj2.boundingBox.x + obj2.boundingBox.width/2;
                
                h1X = new Line(x1, 130,x1 - obj1.boundingBox.width/2, 130,Color.red);              
                h2X = new Line(x2, 130, x2 + obj2.boundingBox.width/2, 130, Color.red);

                hDiff = new Line(h2X.x2, 140, h1X.x2, 140, Color.orange);
                                
                x = hDiff.x1 - hDiff.x2;
                
                if(hDiff.x2 <= hDiff.x1){
                    moveX = true; 
                }
            }
        
        if(isTop){
            y1 = obj1.boundingBox.y + obj1.boundingBox.height/2;
            y2 = obj2.boundingBox.y + obj2.boundingBox.height/2;
            
            h1Y = new Line(50, y1, 50, y1 + obj1.boundingBox.height/2,Color.red);
            h2Y = new Line(50, y2, 50, y2 - obj2.boundingBox.height/2,Color.red);
            
            hDiffY = new Line(60, h1Y.y2, 60, h2Y.y2, Color.orange);

            y = hDiffY.y1 - hDiffY.y2;
            
            if(hDiffY.y2 <= hDiffY.y1){
                    moveY = true;
            }
        }
        else{
            
            y1 = obj1.boundingBox.y + obj1.boundingBox.height/2;
            y2 = obj2.boundingBox.y + obj2.boundingBox.height/2;
            
            h1Y = new Line(50, y1, 50, y1 - obj1.boundingBox.height/2,Color.red);
            h2Y = new Line(50, y2, 50, y2 + obj2.boundingBox.height/2,Color.red);
            
            hDiffY = new Line(60, h1Y.y2, 60, h2Y.y2, Color.orange);
            
            y = hDiffY.y2 - hDiffY.y1;
 
            if(hDiffY.y1 <= hDiffY.y2){
                    moveY = true;
            }
        }
       
        if((moveX) && (moveY)){
            if(x > y){
                if(isTop){
                    obj1.isOnGround = true;
                }
            }
        }
        obj1.boundingBox.height -= offset;
    }
    public float distanceToCenter(PhysicsObject obj1, PhysicsObject obj2){
        float x1 = Math.abs(obj1.center.x - obj2.center.x);
        float y1  = Math.abs(obj1.center.y - obj2.center.y);
        
        float sum = (float) (Math.pow(x1, 2) + Math.pow(y1, 2));
        return (float)Math.sqrt(sum);
        
    }
    public Vector projection(Vector v1, Vector v2){
        float a = (v1.x * v2.x) + (v1.y * v2.y);
        float b = (v2.x * v2.x) + (v2.y * v2.y);
        float c = a/b;
              
        return new Vector(c * v2.x, c * v2.y);
    }
    private Vector getCollisionVelocity(PhysicsObject obj1, PhysicsObject obj2, boolean elastic){
        if(!elastic){
            float newVelocityX = (obj1.mass * obj1.velocity.x + obj2.mass * obj2.velocity.x)/(obj1.mass + obj2.mass);
            float newVelocityY = (obj1.mass * obj1.velocity.y + obj2.mass * obj2.velocity.y)/(obj1.mass + obj2.mass);
            return new Vector(newVelocityX, newVelocityY);
        }
        else{
            float newVelocityX = (obj1.mass * obj1.velocity.x + obj2.mass * obj2.velocity.x)/(obj2.mass * 1.1f); //when V1e = 0
            float newVelocityY = (obj1.mass * obj1.velocity.y + obj2.mass * obj2.velocity.y)/(obj2.mass * 1.1f);
            return new Vector(newVelocityX, newVelocityY);
        }
    }
}
