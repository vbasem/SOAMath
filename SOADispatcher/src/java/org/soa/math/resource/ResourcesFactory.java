/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.HashMap;
import org.soa.math.global.AbstractFactory;
import org.soa.math.resource.clients.ClientFactory;
import org.soa.math.resource.type.ResourceType;
import org.soa.service.registry.RegisteredService;

/**
 *
 * @author Basem
 */
public class ResourcesFactory extends AbstractFactory
{
    protected static final ResourceMonitor resourceMonitorStaticInstance = new ArithmaticWebServiceResourceMonitor();
    
    public static ResourceMonitor getStaticArithmaticResourceMonitor()
    {
        if (!isTestMode())
        {
            return resourceMonitorStaticInstance;
        }
        
        return null;
    }
    
    
    public static Resource getServiceResource(RegisteredService source)
    {
        if (isTestMode())
        {
            return null;
        }
        
        ResourceType type = new ResourceType(source.getType());
        Resource resource = new ArithmaticResource(
                ClientFactory.getArithmaticWebServiceClient(source.getUrl(), source.getType()), 
                type, 
                source.getId());
        
        return resource;
    }
}
