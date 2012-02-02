/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dic;

import com.google.inject.Inject;

/**
 *
 * @author Basem
 */
public class ServiceProvider
{
    private ServiceConfigurationInterface conf;

    @Inject
    public ServiceProvider(ServiceConfigurationInterface config)
    {
        conf = config;
    }
    
    public String getConfiguration()
    {
        return conf.getConfig();
    }

}
