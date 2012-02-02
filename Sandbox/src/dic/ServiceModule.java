/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dic;

import com.google.inject.AbstractModule;


/**
 *
 * @author Basem
 */
public class ServiceModule extends AbstractModule
{

    @Override
    protected void configure()
    {
        
        bind(ServiceConfigurationInterface.class).to(ServiceConfiguration.class);
    }    
}
