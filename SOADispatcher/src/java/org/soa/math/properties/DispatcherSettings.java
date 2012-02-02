/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.properties;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basem
 */
public class DispatcherSettings
{
    private static Properties properties = null;
    private static String properties_file_name = "properties.ini";
    private static String properties_folder_name = "resources";

    public static void initializeProperties()
    {
        PropertiesLoader loader = new PropertiesLoader();
        try {
            properties = loader.loadFromFilePath(new File(".").getCanonicalPath() + 
                    File.separator +
                    properties_folder_name +
                    File.separator +
                    properties_file_name);
        } catch (IOException ex) {
            Logger.getLogger(DispatcherSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getProperty(String key)
    {
        initializePropertiesIfNull();
        return properties.getProperty(key);
    }
    
    public static int getNumericProperty(String key)
    {
        String value = getProperty(key);
        
        return Integer.parseInt(value);
    }
    
    protected static void initializePropertiesIfNull()
    {
                if (properties == null) 
        {
            initializeProperties();
        }
        
    }
    
    public static void changePropertiesFileName(String newFileName)
    {
        properties_file_name = newFileName;
    }
    
    public static void resetProperties()
    {
        properties = null;
    }
    
}
