/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.properties;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basem
 */
public class PropertiesLoader {

    public Properties loadFromFilePath(String filePath) {
        Reader r;
        Properties p = new Properties();
        
        try {
            r = new FileReader(filePath);
            
            try {
                p.load(r);
            } catch (IOException ex) {
                Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }
}
