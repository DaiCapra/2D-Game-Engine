/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Artmann
 */
public class Sound {

    String name;
    URL url;
    AudioInputStream audioIn;
    Clip clip;
    boolean loaded;
    
    public Sound(String name){
        this.name = name;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
        audioIn = null;
        url = null;  
    }
    public void changeVolume(float gain){
        if(gain > -80){
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(gain);
        }
    }
    
}
