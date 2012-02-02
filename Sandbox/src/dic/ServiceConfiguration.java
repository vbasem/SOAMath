/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dic;

/**
 *
 * @author Basem
 */
public class ServiceConfiguration implements ServiceConfigurationInterface
{
    public String config = "production_configuration";

    @Override
    public String getConfig()
    {
        return this.config;
    }
    
    
}
