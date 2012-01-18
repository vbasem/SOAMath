/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.service.registry;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.logging.Level;
import org.soa.service.types.ServiceType;

/**
 *
 * @author Basem
 */
public class Register
{
    private static HashMap<String, RegisteredService> service_register = new HashMap<String, RegisteredService>();
    
    public static void registerService(String identifier, RegisteredService service )
    {
        service_register.put(identifier, service);
        Logger.getLogger("registery").log(Level.INFO, "registring {0} at {1} requested at {2}", new Object[]{identifier, service.getUrl(), System.currentTimeMillis()});
    }
    
    public static RegisteredService getServiceById(String identifier)
    {
        return service_register.get(identifier);
    }
    
    public static void unregisterService(String identifier)
    {
        service_register.remove(identifier);
        Logger.getLogger("registery").log(Level.INFO, "removing {0}", identifier);
    }
}
