/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.service.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basem
 */
public class Register
{
    private static Map<String, RegisteredService> serviceRegister = new ConcurrentHashMap<String, RegisteredService>();
    
    public static void registerService(String identifier, RegisteredService service )
    {
        RegistryHeartBeat.startMonitoringHeartBeats();
        
        serviceRegister.put(identifier, service);
     //   Logger.getLogger("registery").log(Level.INFO, "registring {0} at {1} requested at {2}", new Object[]{identifier, service.getUrl(), System.currentTimeMillis()});
    }
    
    public static RegisteredService getServiceById(String identifier)
    {
        return serviceRegister.get(identifier);
    }
    
    public static void unregisterService(String identifier)
    {
        serviceRegister.remove(identifier);
        Logger.getLogger("registery").log(Level.INFO, "removing {0}", identifier);
    }
    
    public static RegisteredService[] listAllRegisteredServices()
    {
        //Logger.getLogger("registery").log(Level.INFO, "getting all services {0}", serviceRegister.size());
        RegisteredService[] services = {};
        if (!serviceRegister.isEmpty())
        {
            services = serviceRegister.values().toArray(services);
        }
        
        return services;
    }

    public static RegisteredService[] getAllServicesOfType(String type)
    {
        RegisteredService[] resultArray = {};
        List<RegisteredService> servicesOfSameType = new ArrayList<RegisteredService>();
        
        for (RegisteredService service: serviceRegister.values())
        {
            Logger.getLogger("registry").log(Level.WARNING, type, service);
            Logger.getLogger("registry").log(Level.WARNING, service.getType(), service);
            Logger.getLogger("registry").log(Level.WARNING, Boolean.toString(service.getType().compareToIgnoreCase(type) == 0), service);
            if (service.getType().compareToIgnoreCase(type) == 0)
            {
                servicesOfSameType.add(service);
            }
        }
        
        resultArray  = servicesOfSameType.toArray(resultArray);
        
        return resultArray;
    }
    
}
