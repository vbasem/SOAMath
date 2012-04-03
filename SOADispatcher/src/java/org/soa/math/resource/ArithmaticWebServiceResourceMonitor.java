/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import com.google.common.collect.Ordering;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.soa.math.properties.SettingsRepository;
import org.soa.math.resource.clients.ClientFactory;
import org.soa.service.registry.RegisteredService;

/**
 *
 * @author Basem
 */
public class ArithmaticWebServiceResourceMonitor extends Observable implements ResourceMonitor, Runnable
{

    protected Map<String, ActiveResourceSpace> activeResourceSpaces = new ConcurrentHashMap();
    protected Map registeredResourcesAvailable = new ConcurrentHashMap();
    protected Thread monitorThread = new Thread(this);
    private boolean stopMonitoring = false;

    @Override
    public void freeResource(Resource resource)
    {
        removeResourceFromAllQueues(resource);

        String resourceId = resource.getResourceDescriptor();
        ClientFactory.getServerControl().stopAndUnregisterServer(resourceId);

    }

    protected void removeResourceFromAllQueues(Resource resource)
    {
        String resourceType = resource.getType().getTypeName();
        getResourceSpace(resource).removeFreeResource(resource);
        registeredResourcesAvailable.remove(resource.getResourceDescriptor());
    }

    @Override
    public Resource acquireResource(String resourceType)
    {
        Resource res = getResourceWhenItBecomesAvailable(resourceType);
        markResourceAsBusy(res);

        return res;
    }

    @Override
    public void startMonitor()
    {
        if (!monitorThread.isAlive())
        {
            Logger.getLogger(ArithmaticWebServiceResourceMonitor.class.getName()).log(Level.WARNING, "starting resource monitor");
            monitorThread.start();
        }
    }

    @Override
    public void stopMonitor()
    {
        stopMonitoring = true;
    }

    /**
     * the collection of free resources of the type is locked till after the
     * thread requesting resources is notified.
     *
     * TODO: check for concurrency issues
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg)
    {
        Resource res = (Resource) o;
        String resourceType = res.getType().getTypeName();

        removeResourceFromBusyCollection(res);
        checkIfAnyRequestIsPendingForThisResourceType(res);
    }

    @Override
    public void run()
    {
        while (!stopMonitoring)
        {
            updatePendingRequestsAcitivity();
            getAvailableResourcesFromRegistry();
            balanceResourcesToMeetRequests();

            Thread.yield();

            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ArithmaticWebServiceResourceMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void updatePendingRequestsAcitivity()
    {
        for (ActiveResourceSpace pendingCollection : activeResourceSpaces.values())
        {
            pendingCollection.updateActivity();
        }
    }

    private boolean isFreeResourceAvailable(String resourceType)
    {
        return !activeResourceSpaces.get(resourceType).getFreeResourceCollection().isEmpty();
    }

    private Resource getResourceWhenItBecomesAvailable(String resourceType)
    {
        // first attempt is to get reousrce before we go to wait
        Resource res = getFreeResourceIfAvailable(resourceType);

        if (res != null)
        {
            return res;
        }

        // try and start a new server for the service if possible
        // the go to sleep until it comes online
        startNewResourceServerIfPossible(resourceType);

        Thread currentThread = Thread.currentThread();

        getPendingRequestForType(resourceType).
                addRequestThreadToPendingAndIncrement(currentThread);

        // we dont have ready resource, wait till notified
        synchronized (currentThread)
        {
            try
            {
                currentThread.wait();

                // a resource object was given to the pending queue for this thread
                // we fetch it by using this thread as an ID
                return activeResourceSpaces.get(resourceType).
                        getResourceAssignedToPendingThread(currentThread);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(ArithmaticWebServiceResourceMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        throw new InternalError("wait for resource cant get to this point");
    }

    private Resource getFreeResourceIfAvailable(String resourceType)
    {
        return getPendingRequestForType(resourceType).getFreeResourceIfAvailable();
    }

    private void removeResourceFromBusyCollection(Resource res)
    {
        getResourceSpace(res).removeBusyResource(res);
    }

    private void markResourceAsBusy(Resource res)
    {
        getResourceSpace(res).addBusyResource(res);
    }

    private void checkIfAnyRequestIsPendingForThisResourceType(Resource resource)
    {
        ActiveResourceSpace pending = getResourceSpace(resource);

        if (pending != null && pending.isAnyRequestPending())
        {
            Thread threadWaitingForResource = pending.getPendingThreadAndDecrement();
            pending.assignResourceToThread(resource, threadWaitingForResource);

            synchronized (threadWaitingForResource)
            {
                threadWaitingForResource.notify();
            }
        } else
        {
            // back into free resources if no pending
            getResourceSpace(resource).addFreeResource(resource);
        }

    }

    private void getAvailableResourcesFromRegistry()
    {
        List<RegisteredService> registeredServices =
                ClientFactory.getServerControl().getListOfAllAvailableServers();

        for (RegisteredService service : registeredServices)
        {
            // if we have it, skip it

            if (registeredResourcesAvailable.containsKey(service.getId()))
            {
                continue;
            }

            Resource res = initializeNewResource(service);
            // check if anyone needs resource before adding it to free collection
            checkIfAnyRequestIsPendingForThisResourceType(res);
        }
    }

    private Resource initializeNewResource(final RegisteredService service)
    {
        Resource res = ResourcesFactory.getServiceResource(service);
        registeredResourcesAvailable.put(res.getResourceDescriptor(), res);
        res.addObserver(this);

        return res;
    }

    /**
     *
     */
    private void balanceResourcesToMeetRequests()
    {

        if (isPossibleToStartNewServer())
        {
            return;
        }

        ActiveResourceSpace largestPendingResourceSpace = getLargestResourceSpace();
        ActiveResourceSpace lowestLoadSpace = null;


        if (largestPendingResourceSpace == null)
        {
            return;
        }

        for (ActiveResourceSpace resourceSpace : activeResourceSpaces.values())
        {

            if (resourceSpace.getResourceType().compareTo(largestPendingResourceSpace.getResourceType()) == 0)
            {
                continue;
            }


            if (resourceSpace.getLoad() == ActiveResourceSpace.MAX_LOAD)
            {
                continue;
            }

            if (resourceSpace.getLoad() > largestPendingResourceSpace.getLoad())
            {
                continue;
            }

            if (lowestLoadSpace == null)
            {
                lowestLoadSpace = resourceSpace;
            }
        }



        if (lowestLoadSpace != null)
        {
            // if a start of same type is pending, we dont need to balance until is up
            if (!ClientFactory.getServerControl().isServerStarting(largestPendingResourceSpace.getResourceType()))
            {
                freeResource(lowestLoadSpace.removeFirstFreeResource());
                ClientFactory.getServerControl().startServer(largestPendingResourceSpace.getResourceType());
            }

        }
    }

    private ActiveResourceSpace getLargestResourceSpace()
    {
        ActiveResourceSpace largestPending = null;

        try
        {
            ActiveResourceSpace pendingRequests = Ordering.natural().max(activeResourceSpaces.values());

            if (pendingRequests.isAnyRequestPending())
            {
                largestPending = pendingRequests;
            }
        } catch (Exception e)
        {
            largestPending = null;
        }

        return largestPending;
    }

    private boolean areTheCollectionsNotNull(String firstCollection, String secondCollection)
    {
        if (firstCollection != null && secondCollection != null)
        {
            return true;
        }

        return false;
    }

    private void startNewResourceServerIfPossible(String type)
    {
        if (isPossibleToStartNewServer())
        {
            ClientFactory.getServerControl().startServer(type);
        }

        // how do i wait till server is up?
        // smply go into waiting, we notify the same way we do on finished resources
        // when this method returns calling thread is gona wait

    }

    private ActiveResourceSpace getPendingRequestForType(final String resourceType)
    {
        if (!activeResourceSpaces.containsKey(resourceType))
        {
            activeResourceSpaces.put(resourceType, new ActiveResourceSpace(resourceType));
        }

        return activeResourceSpaces.get(resourceType);
    }

    private ActiveResourceSpace getResourceSpace(final Resource res)
    {
        return getPendingRequestForType(res.getType().getTypeName());
    }

    private boolean isPossibleToStartNewServer()
    {
        int availableServers =
                SettingsRepository.getConcurrencySettings().
                getNumericProperty("number_of_available_machines");

        return registeredResourcesAvailable.size() < availableServers;
    }
}
