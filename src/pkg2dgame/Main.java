/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import javax.swing.JFrame;

/**
 *
 * @author Artmann
 */


public class Main extends JFrame implements Commons{

    public Main(){
        
        Game game = new Game();
        add(game);
        setTitle("2DGAME!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(gameWindowWidth, gameWindowHeight);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        new Thread(game).start();
    }
    
    public static void main(String[] args) {
        new Main();  
    }
}
