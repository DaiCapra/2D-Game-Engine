/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgame;

import java.io.*;
/**
 *
 * @author Artmann
 */
public class GameObject extends PhysicsObject implements Serializable{
    
    boolean isKinetic;
    
    public GameObject(Vector position, Figure figure, boolean isKinetic){
        super(position, figure);
        
        this.isKinetic = isKinetic;
        
    }

    public void serialize(){
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream("asd.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(this);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      }catch(IOException i)
      {
          i.printStackTrace();
      }
    }
    public static GameObject deserialize() {
        GameObject go = null;
        
        try {
            FileInputStream fileIn = new FileInputStream("asd.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            go = (GameObject) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return go;
    }

}
