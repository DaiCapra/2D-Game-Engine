/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Artmann
 */
public class Texture extends JFrame{
    private ImageIcon imageIcon;
    
    Image image;
    String path;
    
    public Texture(String path){
        this.path = path;
    }
    public void load(){
        try{
            imageIcon = new ImageIcon(getClass().getResource(path));
            image = imageIcon.getImage();
        }
        catch(Exception e){
            
        }
    }
    public void draw(Graphics2D g2d, Vector drawPos, int width, int height, Color color){
        if(image!=null)
            g2d.drawImage(image, (int)drawPos.x, (int)drawPos.y, width, height, null);
        
    }
}
