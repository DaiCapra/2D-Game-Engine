/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

/**
 *
 * @author Artmann
 */
public class MapLayer {
    
    public Tile[][] tiles;
    Vector size;
    
    public MapLayer(Vector size){
        this.size = size;
        
        tiles = new Tile[(int)size.x][(int)size.y];
        
    }
    
    
}
