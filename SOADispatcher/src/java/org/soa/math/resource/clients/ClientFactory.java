/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.soa.math.global.AbstractFactory;
import org.soa.math.request.RequestType;
import org.soa.math.resource.ServerControl;
import org.soa.math.resource.clients.arithmatic.AdditionWebServiceClient;
import org.soa.math.resource.clients.arithmatic.DivisionWebServiceClient;
import org.soa.math.resource.clients.arithmatic.MultiplicationWebServiceClient;
import org.soa.math.resource.clients.arithmatic.SubstractionWebServiceClient;

/**
 *
 * @author Basem
 */
public class ClientFactory extends AbstractFactory
{
    private static final ServerControl serverControl = new ServerControl();
    private static final RegistryServiceClient registryService = new RegistryServiceClient();
    private static final VirtualMachineControlClient vmClient = new VirtualMachineControlClient();
    private static final HashMap<String, Class> clientInstanceCreatorMapping;
    
    static
    {
        clientInstanceCreatorMapping = new HashMap();
        clientInstanceCreatorMapping.put(RequestType.ADDITION.toString() , AdditionWebServiceClient.class);
        clientInstanceCreatorMapping.put(RequestType.MULTIPLICATION.toString() , MultiplicationWebServiceClient.class);
        clientInstanceCreatorMapping.put(RequestType.SUBSTRACTION.toString() , SubstractionWebServiceClient.class);
        clientInstanceCreatorMapping.put(RequestType.DIVISION.toString() , DivisionWebServiceClient.class);
    }

    public static ResourceClient getArithmaticWebServiceClient(String serverUrl, String type)
    {
        if (isTestMode())
        {
            return null;
        }
        
        
        ResourceClient client = null;
        try
        {
            try
            {
                client = (ResourceClient) clientInstanceCreatorMapping
                        .get(type).getConstructor(String.class)
                        .newInstance(serverUrl); //.new AdditionWebServiceClient(serverUrl);
            } catch (InstantiationException ex)
            {
                Logger.getLogger(ClientFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex)
            {
                Logger.getLogger(ClientFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex)
            {
                Logger.getLogger(ClientFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex)
            {
                Logger.getLogger(ClientFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex)
        {
            Logger.getLogger(ClientFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex)
        {
            Logger.getLogger(ClientFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }

    public static RegistryServiceClient getRegistryServiceClient()
    {
        if (isTestMode())
        {
            return null;
        }

        return registryService;
    }

    public static VirtualMachineControlClient getVmControlClient()
    {
        if (isTestMode())
        {
            return null;
        }

        return vmClient;
    }
    
    public static ServerControl getServerControl()
    {
        if (isTestMode())
        {
            return null;
        }

        return serverControl;        
    }
}
