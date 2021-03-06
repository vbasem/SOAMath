/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.service.registry;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
/**
 *
 * @author Basem
 */
@WebService(name = "RegistryAndLookUp")
public class RegistryAndLookUp
{
    public String registerArithmaticService(String identifier, String wsdlUri, String serviceType)
    {
        //Logger.getLogger("registery").log(Level.INFO, "service type is {0}", new Object[]{serviceType});
        RegisteredService service = new RegisteredService(identifier, wsdlUri, serviceType);
        Register.registerService(identifier, service);
        
        return "ok";
    }
    
    public RegisteredService[] listAllRegisteredServices()
    {
        return Register.listAllRegisteredServices();
    }
    
    public String unregisterService(String identifier)
    {
        Register.unregisterService(identifier); 
        
        return "ok";
    }
    
    public RegisteredService getServiceById(String identifier)
    {
        return Register.getServiceById(identifier);
    }
    
    public RegisteredService[] getAllServicesOfType(String type)
    {
        return Register.getAllServicesOfType(type);
    }
}
