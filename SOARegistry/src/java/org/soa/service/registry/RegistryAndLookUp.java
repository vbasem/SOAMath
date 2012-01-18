/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.service.registry;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.soa.service.types.ServiceType;
/**
 *
 * @author Basem
 */
@WebService(name = "RegistryAndLookUp")
public class RegistryAndLookUp
{
    public void registerArithmaticService(String identifier, String wsdlUri, String serviceType)
    {
        Logger.getLogger("registery").log(Level.INFO, "service type is {0}", new Object[]{serviceType});
        RegisteredService service = new RegisteredServiceImpl(identifier, wsdlUri, serviceType);
        Register.registerService(identifier, service);
    }
    
    public void unregisterService(String identifier)
    {
        Register.unregisterService(identifier); 
    }
    
    public RegisteredService getServiceById(String identifier)
    {
        return Register.getServiceById(identifier);
    }
    
//    public RegisteredService[] getAllServicesOfType(ServiceType type)
//    {
//        
//    }
}
