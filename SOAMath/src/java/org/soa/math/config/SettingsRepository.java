/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.config;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Basem
 */
public class SettingsRepository
{

    private static final String CONFIG_FILE = "config.ini";
    
    private static Map settings = new HashMap<String, Settings>();

    public static Settings getServicesUrlSettings()
    {
        String serviceUrlKey = "config";
        
        return instantiateSettingIfDoesntExist(serviceUrlKey, CONFIG_FILE);
    }
    
    private static Settings instantiateSettingIfDoesntExist(String key, String filePath)
    {
        if (!settings.containsKey(key))
        {
            Settings settingsInstance = new Settings();
            settingsInstance.setPropertyFileName(filePath);
            
            settings.put(key, settingsInstance);
        }
        
        return (Settings) settings.get(key);        
    }
}
