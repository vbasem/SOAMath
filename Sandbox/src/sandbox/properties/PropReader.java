/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.properties;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Basem
 */
public class PropReader {

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {

        File f = new File(".");
        Reader r = new FileReader(f.getCanonicalPath() + File.separator + "resources" + File.separator + "properties.ini");
        Properties p = new Properties();
        p.load(r);
        
        System.out.println(File.listRoots()[1].getCanonicalPath());


    }
}
