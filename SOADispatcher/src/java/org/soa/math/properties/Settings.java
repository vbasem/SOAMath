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
public class Settings
{

    private Properties properties = null;
    private String properties_file_name = "properties.ini";
    private String properties_folder_name = "resources";

    public void initializeProperties()
    {
        PropertiesLoader loader = new PropertiesLoader();
        try
        {
            properties = loader.loadFromFilePath(new File("C:\\math\\SOAMath\\SOADispatcher").getCanonicalPath()
                    + File.separator
                    + properties_folder_name
                    + File.separator
                    + properties_file_name);
        } catch (IOException ex)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getProperty(String key)
    {
        initializePropertiesIfNull();
        return properties.getProperty(key);
    }

    public int getNumericProperty(String key)
    {
        String value = getProperty(key);

        return Integer.parseInt(value);
    }

    protected Settings initializePropertiesIfNull()
    {
        if (properties == null)
        {
            initializeProperties();
        }
        
        return this;
    }

    public void setPropertyFileName(String newFileName)
    {
        properties_file_name = newFileName;
    }

    public Settings resetProperties()
    {
        properties = null;

        return this;
    }
}
