package pkg2dgame;


import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyAdapter;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Artmann
 */
public class Game extends JPanel implements Runnable {
     
    List<String> messages = new LinkedList<>();
    List<PhysicsObject> objects = new ArrayList<>();
    List<Line> lines;
    List<Level> levels = new LinkedList<>();
    
    boolean collisionsEnabled = true;
    
    Texture texture;
    AudioClip sound;
    AudioPlayer audioPlayer;
    
    float paintIntervall = 0;
    long systemStartTime;
    
    boolean gameLoaded = false;
    int currentLevelIndex = 1;
   
    boolean gameActive = true;
    
    public Game(){
        
        setFocusable(true);
        addKeyListener(new TAdapter());
        systemStartTime = System.currentTimeMillis();
        
        levels.add(new Level("level1", this));

        
        audioPlayer = new AudioPlayer(this);
        //audioPlayer.sounds.add(new Sound("/resources/song.wav"));
        audioPlayer.loadSounds();
        audioPlayer.playAll();
       
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        if(gameActive){
            if(getCurrentLevel() != null)
                getCurrentLevel().paint(g2d);
            /*
            for(Line l:lines){
                l.paint(g2d);
            }
            */
            try{
                writeDebug(g);
            }
            catch(Exception e ){
                System.out.print("WriteDebug: "+e.toString());
            }
        }
    }
    
    public void nextLevel(){
        gameActive = false;
        levels.clear();
        levels.add(new Level("level1", this));
        gameActive = true;
        
    }
    
    @Override 
    public void run() {
        while(gameActive){
            
            if(getCurrentLevel() != null)
                getCurrentLevel().update();
            
            if(paintIntervall == 1){
                
                messages.add(getCurrentLevel().player.boundingBox.toString());
                messages.add(getCurrentLevel().box1.boundingBox.toString());
                
                repaint();
                paintIntervall = 0;
            }
            paintIntervall++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }
    private void writeDebug(Graphics g){
        g.setColor(Color.black);
    
        int i = 0;
        for(String s: messages){
            g.drawString(s, 20, 50 + i*20);
            i++;
        }
        messages.clear();
    }
    private Level getCurrentLevel(){
        try{
            return levels.get(currentLevelIndex - 1);
        }
        catch(Exception e){
            return null;
        }
    }
    private class TAdapter extends KeyAdapter{
        
        public void keyPressed(KeyEvent e){
            if(gameActive){
                int key = e.getKeyCode();


                if(key == KeyEvent.VK_LEFT){
                    getCurrentLevel().player.acceleration.x = (-5);
                }
                if(key == KeyEvent.VK_RIGHT){
                    getCurrentLevel().player.acceleration.x = 5;
                }
                if(key == KeyEvent.VK_UP){

                    if(!getCurrentLevel().player.gravityEnabled){
                        getCurrentLevel().player.acceleration.y = -5;
                    }
                    else
                    if(getCurrentLevel().player.canJump){
                        getCurrentLevel().player.canJump = false;
                        getCurrentLevel().player.jump();  
                    }
                }
                if(key == KeyEvent.VK_DOWN){

                    if(!getCurrentLevel().player.gravityEnabled){
                        getCurrentLevel().player.acceleration.y = 5;
                    }
                }
                if(key == KeyEvent.VK_R){
                    getCurrentLevel().box2.velocity.x -= 0.4f;
                }
            }
        }
        public void keyReleased(KeyEvent e){
            if(gameActive){
                int key = e.getKeyCode();

                if(key == KeyEvent.VK_C){
                    getCurrentLevel().toggleGravity();
                }
                if(key == KeyEvent.VK_LEFT){
                    getCurrentLevel().player.acceleration.x = 0;
                }
                if(key == KeyEvent.VK_RIGHT){
                    getCurrentLevel().player.acceleration.x = 0;
                }
                if(key == KeyEvent.VK_UP){
                    getCurrentLevel().player.canJump = true;
                    if(!getCurrentLevel().player.gravityEnabled){
                        getCurrentLevel().player.acceleration.y =0;
                    }
                }
                if(key == KeyEvent.VK_DOWN){

                    if(!getCurrentLevel().player.gravityEnabled){
                        getCurrentLevel().player.acceleration.y =0;
                    }
                } 
            }
        }
    }
}
