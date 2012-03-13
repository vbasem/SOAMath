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
    private String properties_file_name = null;

    public void initializeProperties()
    {
        PropertiesLoader loader = new PropertiesLoader();
        properties = loader.loadFromFilePath(properties_file_name);
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
