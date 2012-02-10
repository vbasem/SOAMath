/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.properties;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Basem
 */
public class SettingsRepository
{

    private static final String CONCURRENCY_SETTINGS = "concurrency_settings.ini";
    private static final String SERVICES_URL = "services_url.ini";
    
    private static Map settings = new HashMap<String, Settings>();

    public static Settings getConcurrencySettings()
    {
        String concurrencyKey = "concurrency";
        
        return instantiateSettingIfDoesntExist(concurrencyKey, CONCURRENCY_SETTINGS);
    }
    
    public static Settings getServicesUrlSettings()
    {
        String serviceUrlKey = "service_url";
        
        return instantiateSettingIfDoesntExist(serviceUrlKey, SERVICES_URL);
    }
    
    private static Settings instantiateSettingIfDoesntExist(String key, String filePath)
    {
        if (!settings.containsKey(key))
        {
            Settings concurrency = new Settings();
            concurrency.setPropertyFileName(filePath);
            
            settings.put(key, concurrency);
        }
        
        return (Settings) settings.get(key);        
    }
}
