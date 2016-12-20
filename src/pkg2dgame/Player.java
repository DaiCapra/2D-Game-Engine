/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Artmann
 */
public class Player extends PhysicsObject {
    
    Vector position;
    Texture texture;
    String texturePath;
    float health = 100;
    boolean canJump = true;
    
    private int jumpCount;
   
    
    public Player(Vector position, String texturePath){
        super(position, Figure.rectangle);
        this.position = position;
        this.texturePath = texturePath;

        mass = 20000;
        texture = new Texture(texturePath);
        
        initPlayer();
    }
    private void initPlayer(){
        gravity = 1.7f;
        setBoundingBox();
    }
    
    private void setBoundingBox(){
        boundingBox = new Rectangle((int)position.x, (int)position.y, 65, 65);
        center = new Vector(position.x + boundingBox.width/2, position.y + boundingBox.height/2);
    }
    public void load(){
        
        texture.load();
    }
    @Override
    public void update(){
        super.update();
        if(isOnGround){
            jumpCount = 1;
        }
    }
    public void paint(Graphics2D g2d, Vector drawPos){
        if(texture != null) 
            texture.draw(g2d, drawPos, texture.image.getWidth(null), texture.image.getHeight(null), Color.yellow);
    }
    public void jump(){
            if(jumpCount == 1){
                velocity.y = -0.7f;
                jumpCount--;
            }
            
       
    }
    private boolean isAlive(){
        if(health <= 0){
            return false;
        }
        else{
            return true;
        }
    }
   
}
