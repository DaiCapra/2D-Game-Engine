/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static pkg2dgame.Commons.tileSize;

/**
 *
 * @author Artmann
 */
public class Map implements Commons {
    String filepath;
    Level level;
    
    MapLayer[] mapLayers;
    int numberOfLayers = 5;
    List<String> mapLines;
    
    public Map(String filepath, Level level){
        mapLines = new LinkedList<String>();
        this.level = level;
        this.filepath = filepath;
        mapLayers = new MapLayer[numberOfLayers];
    }
    public void load(){          
        try {
            loadMapFromFile(filepath);
            initLayers();
            interpretLayers();  
        } catch (IOException ex) {
            System.out.println("Couldnt load file: "+filepath+"!");
        }
    }
    private void initLayers(){
        
        int k = 0;
        for(String s : mapLines){
            if(s.length() >= k)
                k = s.length();
        }
        int l = mapLines.size();

        for(int i = 0; i < numberOfLayers; i++){
            mapLayers[i] = new MapLayer(new Vector(k, l));
        }
    }
    
    private boolean loadMapFromFile(String filepath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filepath));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    mapLines.add(line);
                    sb.append(line);
                    sb.append('\n');
                    line = br.readLine();
                }
                String everything = sb.toString();
                System.out.println(everything);
            }
            catch(Exception e){
                return false;
            }
            finally {
                br.close();
                return true;
            }        
    }
    
    private void interpretLayers(){
        int index = 0;
        String texture;
        for(int i = 0; i < mapLines.size(); i++){
            for(int j = 0; j < mapLines.get(i).length(); j++){
                char c = mapLines.get(i).charAt(j);
                
                switch(c){
                    case 'x': setTile(0, false, new Vector(j,i), "tile3.png");
                        break;
                    case 'c': setTile(0, true, new Vector(j,i), "tile4.png");
                        break;    
                    case 'p': setPlayer(new Vector(j,i));
                        break;
                    case 'e': setExit(new Vector(j,i));
                        break;    
                    case '0': setEnemy(new Vector(j,i), 0);
                        break;    
                    case '.': 
                        break;
                    default:
                        break;
                }
            }
        }
    }
    private void setTile(int layerIndex, boolean walkable, Vector pos, String texture){
        Texture t = new Texture("/resources/tiles/" + texture);
        t.load();
        
        Vector v = new Vector((int)pos.x * tileSize, (int)pos.y * tileSize);
        mapLayers[layerIndex].tiles[(int)pos.x][(int)pos.y] = new Tile(v, walkable, t);
    }
    private void setPlayer(Vector pos){
        if(level.player == null){
            level.player = new Player(new Vector((int)pos.x * tileSize, (int)pos.y * tileSize), "/resources/player/player.png");
        }
    }
    private void setExit(Vector pos){
        Texture t = new Texture("/resources/level/exit.png");
        t.load();
        
        Vector v = new Vector((int)pos.x * tileSize + 50, (int)pos.y * tileSize + 50);
        level.exit = new Tile(v, false, t);
    }
    private void setEnemy(Vector pos, int type){
       
    }
    private void setPlattform(int layerIndex, Vector pos, String texture){
    
    }
    
    private void loadLayers(){
        
    }
    
    private void updateLayers(){
        
    }
}
