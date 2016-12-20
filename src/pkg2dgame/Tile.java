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
public class Tile extends PhysicsObject implements Commons{
    
    boolean walkable;
    Texture texture;
    
    public Tile(Vector pos, boolean walkable, Texture texture){
        super(pos, Figure.rectangle);
        this.walkable = walkable;
        this.texture = texture;
        this.width = tileSize;
        this.height = tileSize;
        
        initTile();
    }
    private void initTile(){
        this.boundingBox = new Rectangle((int)position.x, (int)position.y, width, height);
        if(!walkable){
            this.isStatic = true;
        }
    }
    
    public boolean getWalkable(){
        return walkable;
    }
    public Texture getTexture(){
        return texture;
    }
}
