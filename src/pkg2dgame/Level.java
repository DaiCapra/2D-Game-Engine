/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import static pkg2dgame.Commons.tileSize;

import pkg2dgame.PhysicsObject.Figure;

/**
 *
 * @author Artmann
 */
public class Level extends PhysicsMath implements Commons {
    Game game;
    
    Map map;
    Player player;
    Camera camera;
    String name;
    Tile exit;
    
    
    List<PhysicsObject> physicsObjects;
    List<GameObject> gameObjects;
    
    Box box1;
    Box box2;
    
    boolean levelLoaded = false;
    boolean gravityEnabled = false;
    boolean firstFrame = false;
    public Level(String name, Game game){   
        this.name = name;
        this.game = game;
        
        physicsObjects = new LinkedList<PhysicsObject>();
        gameObjects = new LinkedList<GameObject>();
        
        map = new Map(name + ".txt", this);
        camera = new Camera(this, new Vector(0,0), gameWindowWidth, gameWindowHeight);
        
        box1 = new Box(new Vector(100,200), 64, 64, Figure.rectangle);
        box2 = new Box(new Vector(300,200), 64, 64, Figure.rectangle);

        loadLevel();
        addObjects();
        
 
        
    }
    public void loadLevel(){     
        map.load();
        player.load();
        
        System.out.println("Level: " + name +" created.");
        
    }
    
    private void addObjects(){
        physicsObjects.add(box1);
        physicsObjects.add(box2);
        physicsObjects.add(player);
        
        for(int i = 0; i < 1; i++){
           for(int j = 0; j < map.mapLayers[i].size.y; j++ ){
               for(int k = 0; k < map.mapLayers[i].size.x; k++){
                   if(map.mapLayers[i].tiles[k][j] != null){
                        physicsObjects.add(map.mapLayers[i].tiles[k][j]);
                   }
               }
           }
        }
    }
    public void update(){
        camera.update();
        player.update();
        
        updateObjects();
        updateEnemies();
        
        checkCollisions();
        
        if(playerAtExit()){
            game.nextLevel();
        }
        
        //Force gravity on
        if (!firstFrame){
            firstFrame = true;
            toggleGravity();
        }
    }
    public void paint(Graphics2D g2d){
        paintMap(g2d);
        player.paint(g2d, getDrawPos(player.position));
        box1.paint(g2d, getDrawPos(box1.position));
        box2.paint(g2d, getDrawPos(box2.position));
    }
    
    private void paintMap(Graphics2D g2d){
        //higher is deeper
        for(int i = 0; i < 1; i++){
           for(int j = 0; j < map.mapLayers[i].size.y; j++ ){
               for(int k = 0; k < map.mapLayers[i].size.x; k++){
                   if(map.mapLayers[i].tiles[k][j] != null){
                       Vector pos = new Vector(map.mapLayers[i].tiles[k][j].position.x,
                            map.mapLayers[i].tiles[k][j].position.y);
                       
                       if(insideCamera(pos))
                            map.mapLayers[i].tiles[k][j].texture.draw(g2d, getDrawPos(pos), tileSize, tileSize, Color.yellow);
                   }
               }
           }
        }
        exit.texture.draw(g2d, getDrawPos(exit.position), tileSize, tileSize, Color.yellow);
    }
    private void updateObjects(){
        if(physicsObjects != null){
            for(PhysicsObject obj: physicsObjects){
                if(obj != null){
                    obj.update();
                    obj.isOnGround = false;
                }
            }
        }
        exit.update();
    }
    private void updateEnemies(){
    }
    
    private void checkCollisions(){
       if(physicsObjects != null){ 
        if(physicsObjects.size() >= 1){
            for(PhysicsObject pO : physicsObjects){
                for(PhysicsObject pO2 : physicsObjects){
                    if(pO != pO2){  
                        if(!pO.isStatic){
                            if((pO.figure == PhysicsObject.Figure.rectangle) && (pO2.figure == PhysicsObject.Figure.rectangle)){
                                
                                checkForGround(pO, pO2);
                                handleRectangles(pO, pO2);
                            }
                        }
                    }
                }
            }
        }
       }
    }
        public void toggleGravity(){
        if(gravityEnabled){
            gravityEnabled = false;
        }
        else{
            gravityEnabled = true;
        }

        for(PhysicsObject po : physicsObjects){
            if(!po.isStatic){
                
                if(gravityEnabled){
                    po.gravityEnabled = true;
                }
                else{
                    po.gravityEnabled = false;
                }
            }
        }
    }
    private boolean playerAtExit(){
        if(distanceToCenter(player, exit) <= 50){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean insideCamera(Vector position){
        float x = position.x;
        float y = position.y;
        
        if((x >= (camera.position.x - camera.offset)) && (x <= (camera.position.x + camera.viewport.getWidth()+ camera.offset))){
            if ((y >= (camera.position.y - camera.offset)) && (y <= (camera.position.y + camera.viewport.getWidth() + camera.offset)))
                    return true;
        }
        return false;
    }
    public Vector getDrawPos(Vector objPos){
        return new Vector(objPos.x - camera.position.x, objPos.y -  camera.position.y);
    }

}
