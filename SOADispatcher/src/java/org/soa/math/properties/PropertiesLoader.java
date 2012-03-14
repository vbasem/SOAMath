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
public class PropertiesLoader
{

    public Properties loadFromFilePath(String configFileName)
    {
        Properties p = new Properties();
        InputStream configSource = this.getClass().getResourceAsStream(configFileName);

        try
        {
            p.load(configSource);
        } catch (IOException ex)
        {
            Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }
}
