/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients;

import org.soa.math.global.AbstractFactory;

/**
 *
 * @author Basem
 */
public class ClientFactory extends AbstractFactory
{

    private static RegistryServiceClient registryService = null;
    private static VirtualMachineControlClient vmClient = null;

    public static ResourceClient getAdditionWebServiceClient(String serverUrl)
    {
        if (isTestMode())
        {
            return null;
        }

        ResourceClient client = new AdditionWebServiceClient(serverUrl);

        return client;
    }

    public static RegistryServiceClient getRegistryServiceClient()
    {
        if (isTestMode())
        {
            return null;
        }

        if (registryService == null)
        {
            registryService = new RegistryServiceClient();
        }

        return registryService;
    }

    public static VirtualMachineControlClient getVmControlClient()
    {
        if (isTestMode())
        {
            return null;
        }

        if (vmClient == null)
        {
            vmClient = new VirtualMachineControlClient();
        }

        return vmClient;
    }
}
