/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.soa.math.properties.SettingsRepository;
import org.soa.math.resource.clients.ClientFactory;
import org.soa.service.registry.RegisteredService;

/**
 *
 * control the listing of servers ( services ) and starting / stopping them
 * serves as a concurrency gateway to avoid parallel starts or stops of the same
 * service
 *
 * @author Basem
 */
public class ServerControl
{

    protected Map serversWaitingToBeStarted = new ConcurrentHashMap();
    protected Map serversWaitingToBeStopped = new ConcurrentHashMap();
    protected Map lastReceivedListOfServicesFromRegistry = new ConcurrentHashMap();
    protected int numberOfAvailableMachines = SettingsRepository.getConcurrencySettings().getNumericProperty("number_of_available_machines");

    public synchronized void startServer(String serverType)
    {
        if (!isServerStarting(serverType) && isSpaceForMoreMachines())
        {
            // check if we have space to start another machine give current started and starting
            if ((lastReceivedListOfServicesFromRegistry.size() + serversWaitingToBeStarted.size())
                    < SettingsRepository.getConcurrencySettings().getNumericProperty("number_of_available_machines"))
            {
                System.out.println(" request to start server " + serverType);
                // TODO: this limits start of same type of server to 1
                serversWaitingToBeStarted.put(serverType, true);
            }
            numberOfAvailableMachines = ClientFactory.getVmControlClient().startService(serverType);
        }
    }

    public synchronized List<RegisteredService> getListOfAllAvailableServers()
    {
        // now we can start more server of this type if we had waiting for start pending
        List<RegisteredService> servicesFromRegistry =
                ClientFactory.getRegistryServiceClient().getAllRegisteredServices();

        for (RegisteredService service : servicesFromRegistry)
        {
            if (lastReceivedListOfServicesFromRegistry.containsKey(service.getId()))
            {
                continue;
            }

            lastReceivedListOfServicesFromRegistry.put(service.getId(), service.getType());
            serversWaitingToBeStarted.remove(service.getType());
        }

        return servicesFromRegistry;
    }

    public synchronized void stopAndUnregisterServer(String serverId)
    {
        System.out.println(" request to stop server " + serverId);
        if (lastReceivedListOfServicesFromRegistry.containsKey(serverId) && isNotBeingStopped(serverId))
        {
            lastReceivedListOfServicesFromRegistry.remove(serverId);
            numberOfAvailableMachines = ClientFactory.getVmControlClient().stopService(serverId);
            ClientFactory.getRegistryServiceClient().unregisterService(serverId);
            unlockMachineIdAfterstop(serverId);
        }
    }
    
    protected void lockMachineIdUntilStopped(String serverId)
    {
        serversWaitingToBeStopped.put(serverId, true);
    }
    
    protected void unlockMachineIdAfterstop(String serverId)
    {
        serversWaitingToBeStopped.remove(serverId);
    }
    
    protected boolean isNotBeingStopped(String serverId)
    {
        return !serversWaitingToBeStopped.containsKey(serverId);
    }
    
        public synchronized boolean isServerStarting(String serverType)
    {
        return serversWaitingToBeStarted.containsKey(serverType);
    }
    
    public synchronized boolean isSpaceForMoreMachines()
    {
        return numberOfAvailableMachines > 0;
    }
    
}
