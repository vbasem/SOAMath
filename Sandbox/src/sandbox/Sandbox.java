/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basem
 */
public class Sandbox implements ArithS, Comparable<String>
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //File f = new File("hi");
            Formatter output = new Formatter("test.txt");
            output.format("%s", "hello everybody");
            output.close();
            // TODO code application logic here
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public int compareTo(String o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
