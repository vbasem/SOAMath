/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine.config;

import java.util.Properties;

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
