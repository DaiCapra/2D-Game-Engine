/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public abstract class PhysicsObject implements Serializable {
    
    public enum Figure{
        rectangle,
        circle,
    }
    
    boolean gravityEnabled = false;
    boolean solid = true;
    
    Vector position;
    Vector velocity;
    Vector acceleration;

    int width, height;
    Vector center;
    float mass = 20000;
    float gravity = 1f;
    float airFriction = 0.6f;
    Vector TVelocity = new Vector(2,2);
    float dragTime = 100;
    
    boolean isOnGround;
    
    Rectangle boundingBox;
    boolean isRectangle;
    boolean isStatic;
    
    Figure figure;
    long start;
    long elapsed;
    
    String testarn;
    
    boolean firstUpdate = true;
    public PhysicsObject(Vector position, Figure figure){
        this.position = position;
        this.velocity = new Vector(0,0);
        this.acceleration = new Vector(0,0);
        this.figure = Figure.rectangle;
        this.boundingBox = new Rectangle((int)position.x, (int)position.y, width, height);
        
        setBounds();
    }
    
    private void setBounds(){
        boundingBox.x = (int)position.x;
        boundingBox.y = (int)position.y;
        center = new Vector(position.x+boundingBox.width/2, position.y+boundingBox.height/2);
    }
    
    public void update(){

        setBounds();
        if(!isStatic)
            gainVelocity();
        
        firstUpdate = false;
    }
    public void paint(Graphics g){
    }

    private void gainVelocity(){
        elapsed = System.currentTimeMillis() - start;
        
        velocity.x += acceleration.x * elapsed / 1000;
        position.x += velocity.x * elapsed;
        
        if(gravityEnabled){
            if(!isOnGround)
                velocity.y += gravity * elapsed / 1000;
        }
        
        velocity.y += acceleration.y * elapsed / 1000 ; 
        position.y += velocity.y * elapsed ;
       
        if(velocity.y >= TVelocity.y){
            velocity.y = TVelocity.y;
        }
        else if(velocity.y <= -TVelocity.y){
            velocity.y = -TVelocity.y;
        }
        if(!isStatic){
            applyDrag();
        }
        start = System.currentTimeMillis();
    }
    private void applyDrag(){
        float dV; 
        float acc;
        
        if(!gravityEnabled){
        dV = 0 - velocity.y;
        acc = dV / dragTime;
        
        velocity.y += acc * elapsed;
        }
        dV = 0 - velocity.x;
        acc = dV / dragTime;
        
        velocity.x += acc * elapsed;
        
        float round = 0.005f;
        if((velocity.x < round) && (velocity.x > -round) ){
            velocity.x = 0;
        }
        round = 0.005f;
        if((velocity.y < round) && (velocity.y > -round) ){
            velocity.y = 0;
        }   
    }
   
    public Vector contains(PhysicsObject pO){
        return null;
    }
    public Vector intersects(PhysicsObject pO){
        return null;
    }
}
