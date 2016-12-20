/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Rectangle;

/**
 *
 * @author Artmann
 */
public class Camera {
    
    int offset = 600;
    int playerOffset = 150;
    Rectangle viewport;
    Vector position;
    Level level;
    
    public Camera(Level level, Vector position, int width, int height){
        this.level = level;
        this.position = position;
    
        viewport = new Rectangle((int)position.x, (int)position.y, width, height);
    }
    public void update(){
        position.x = level.player.position.x - (float)(viewport.getWidth() / 2) + playerOffset ;
        position.y = level.player.position.y - (float)(viewport.getHeight() / 2);
    }
}
