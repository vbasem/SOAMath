/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import org.soa.math.global.AbstractFactory;
import org.soa.math.resource.clients.ClientFactory;
import org.soa.math.resource.type.ResourceType;

/**
 *
 * @author Basem
 */
public class ResourcesFactory extends AbstractFactory
{
    protected static ResourceMonitor resourceMonitorStaticInstance = null;
    
    public static ResourceMonitor getStaticArithmaticResourceMonitor()
    {
        if (!isTestMode())
        {
            return instantiateResourceMonitorSingleton();
        }
        
        return null;
    }
    
    protected static ResourceMonitor instantiateResourceMonitorSingleton()
    {
        if (resourceMonitorStaticInstance == null)
        {
            resourceMonitorStaticInstance = new ArithmaticWebServiceResourceMonitor();
        }
        
        return resourceMonitorStaticInstance;
    }
    
    
    public static Resource getArithmaticWebServiceResource(ResourceType type)
    {
        if (isTestMode())
        {
            return null;
        }
        
        Resource resource = new ArithmaticResource(ClientFactory.getAdditionWebServiceClient(), type);
        
        return resource;
    }
}
