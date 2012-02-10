/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import com.google.common.collect.HashBiMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import org.soa.math.resource.clients.ClientFactory;

/**
 *
 * @author Basem
 */
public class ArithmaticWebServiceResourceMonitor implements ResourceMonitor, Runnable
{
    protected Map resources = new HashMap();
    protected Map registeredResourcesAvailable = new HashBiMap();
    
    @Override
    public void freeResource(Resource resource)
    {
        String resourceId = resource.getResourceDescriptor();
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Resource acquireResource()
    {
        ClientFactory.getRegistryServiceClient().getAllRegisteredServices();
    }

    @Override
    public void startMonitor()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stopMonitor()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Observable o, Object arg)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
