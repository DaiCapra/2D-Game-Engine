/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.util.LinkedList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Artmann
 */
public class AudioPlayer {
    
    List<Sound> sounds;
    final Game game;
    
    
    public AudioPlayer(Game game){
        sounds = new LinkedList<>();
        this.game = game;
    }
    public void loadSounds(){
        for(Sound s : sounds){
           
            try{
                s.url = game.getClass().getResource(s.name);
                s.audioIn = AudioSystem.getAudioInputStream(s.url);
                s.clip.open(s.audioIn);
                s.loaded = true;
            }
            catch(Exception e){
                game.messages.add("Sound: "+ e.toString());
            }
        }
    }
    public void playbyIndex(int index){
        if(sounds.get(index).loaded)
            sounds.get(index).clip.start();
    }
    public void playAll(){
        for(Sound s : sounds){
            if(s.loaded)
                s.clip.start();
        }
    }
    public void stopAll(){
       for(Sound s : sounds){
            s.clip.stop();
        }
    }
    public void stopbyIndex(int index){
        if(sounds.get(index).loaded)
            sounds.get(index).clip.stop();
    }
    public void pausebyIndex(int index){
        
    }

}
