/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.Map.Entry;
import java.util.*;
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

    protected Map<String, ResourceCollection> freeResources = new ConcurrentHashMap();
    protected Map<String, ResourceCollection> usedResources = new ConcurrentHashMap();
    protected Map<String, PendingResourceRequests> pendingResourceRequests = new ConcurrentHashMap();
    protected Map registeredResourcesAvailable = new ConcurrentHashMap();
    protected Thread monitorThread = new Thread(this);
    ;
    private boolean stopMonitoring = false;

    @Override
    public void freeResource(Resource resource)
    {
        removeResourceFromAllQueues(resource);

        String resourceId = resource.getResourceDescriptor();

        ClientFactory.getRegistryServiceClient().unregisterService(resourceId);
        ClientFactory.getVmControlClient().stopService(resourceId);

    }

    protected void removeResourceFromAllQueues(Resource resource)
    {
        String resourceType = resource.getType().getTypeName();
        getFreeResourceCollectionForType(resourceType).remove(resource.getResourceDescriptor());
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

        synchronized (getFreeResourceCollectionForType(resourceType))
        {
            removeResourceFromBusyCollection(res);
            checkIfAnyRequestIsPendingForThisResourceType(res);
        }
    }

    @Override
    public void run()
    {
        while (!stopMonitoring)
        {
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

    private boolean isFreeResourceAvailable(String resourceType)
    {
        return !getFreeResourceCollectionForType(resourceType).isEmpty();
    }

    private boolean isFreeResoiurceCollectionAvailable(String resourceType)
    {

        if (freeResources.containsKey(resourceType))
        {
            return true;
        }

        return false;
    }

    private ResourceCollection getFreeResourceCollectionForType(String resourceType)
    {
        if (!isFreeResoiurceCollectionAvailable(resourceType))
        {
            FreeResources resourceCollection = new FreeResources();
            freeResources.put(resourceType, resourceCollection);
        }

        return freeResources.get(resourceType);
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

        getPendingRequestForType(resourceType).
                addRequestThreadToPendingAndIncrement(Thread.currentThread());

        Thread currentThread = Thread.currentThread();
        // we dont have ready resource, wait till notified
        synchronized (currentThread)
        {
            try
            {
                currentThread.wait();

                // a resource object was given to the pending queue for this thread
                // we fetch it by using this thread as an ID
                return pendingResourceRequests.get(resourceType).
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
        synchronized (getFreeResourceCollectionForType(resourceType))
        {
            if (isFreeResourceAvailable(resourceType))
            {
                return freeResources.get(resourceType).pop();
            }
        }

        return null;
    }

    private void removeResourceFromBusyCollection(Resource res)
    {
        String resourceType = res.getType().getTypeName();

        usedResources.get(resourceType).remove(res.getResourceDescriptor());
        // freeResources.get(resourceType).put(res.getResourceDescriptor(), res);
    }

    private void markResourceAsBusy(Resource res)
    {
        //TODO: this type correct type?
        getUsedResourceCollectionForType(res.getType().getTypeName()).put(res.getResourceDescriptor(), res);;
        //freeResources.get(resourceType).remove(res.getResourceDescriptor());
    }

    private void checkIfAnyRequestIsPendingForThisResourceType(Resource resource)
    {
        PendingResourceRequests pending = pendingResourceRequests.get(resource.getType().getTypeName());

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
            putFreeResourceInCollection(resource);
        }
        
    }

    private void getAvailableResourcesFromRegistry()
    {
        List<RegisteredService> registeredServices =
                ClientFactory.getRegistryServiceClient().getAllRegisteredServices();

        for (RegisteredService service : registeredServices)
        {
            // if we have it, skip it
            
            if (registeredResourcesAvailable.containsKey(service.getId()))
            {
                continue;
            }

            Resource res = ResourcesFactory.getArithmaticWebServiceResource(service);
            registeredResourcesAvailable.put(res.getResourceDescriptor(), res);
            res.addObserver(this);
            // check if anyone needs resource before adding it to free collection
            checkIfAnyRequestIsPendingForThisResourceType(res);
        }
    }

    private void putFreeResourceInCollection(Resource res)
    {
        String resourceTypeName = res.getType().getTypeName();
        getFreeResourceCollectionForType(resourceTypeName);
        freeResources.get(resourceTypeName).put(res.getResourceDescriptor(), res);
    }
    
    /**
     *
     */
    private void balanceResourcesToMeetRequests()
    {
        String typeOfLargestFreeResourceCollection = getTypeOfLargestFreeResourceCollection();
        String typeOfLargestPendingResourceCollection = getTypeForLargestPendingResourceCollection();


        if (areTheCollectionsNotNull(
                typeOfLargestFreeResourceCollection,
                typeOfLargestPendingResourceCollection))
        {
            // not the same type ( which is strange, since pending would have used the available resource
            if (typeOfLargestFreeResourceCollection.compareTo(typeOfLargestPendingResourceCollection) != 0)
            {
                if (freeResources.get(typeOfLargestFreeResourceCollection).size() > pendingResourceRequests.get(typeOfLargestPendingResourceCollection).getRequestCounter())
                {
                    freeResource(freeResources.get(typeOfLargestFreeResourceCollection).pop());
                    ClientFactory.getVmControlClient().startService(typeOfLargestPendingResourceCollection);

                }
            }
        }
    }

    private String getTypeOfLargestFreeResourceCollection()
    {
        Map m = new HashMap();

        int largestFreeCollectionSize = 0;
        String largestFreeCollectionType = null;
        Iterator<Entry<String, ResourceCollection>> freeCollectionsIterator =
                freeResources.entrySet().iterator();

        while (freeCollectionsIterator.hasNext())
        {
            Entry<String, ResourceCollection> collection = freeCollectionsIterator.next();
            ResourceCollection currentCollection = collection.getValue();
            String currentCollectionType = collection.getKey();

            if (currentCollection.size() > largestFreeCollectionSize)
            {
                largestFreeCollectionSize = currentCollection.size();
                largestFreeCollectionType = currentCollectionType;
            }
        }

        return largestFreeCollectionType;
    }

    private String getTypeForLargestPendingResourceCollection()
    {
        Iterator pendingResourceItr = pendingResourceRequests.entrySet().iterator();
        String typeOfLargestPendingCollection = null;
        int sizeOfLargestCollection = 0;

        while (pendingResourceItr.hasNext())
        {
            Entry<String, PendingResourceRequests> pending = (Entry<String, PendingResourceRequests>) pendingResourceItr.next();

            if (pending.getValue().getRequestCounter() > sizeOfLargestCollection)
            {
                sizeOfLargestCollection = pending.getValue().getRequestCounter();
                typeOfLargestPendingCollection = pending.getKey();
            }
        }

        return typeOfLargestPendingCollection;
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
        int availableServers =
                SettingsRepository.getConcurrencySettings().
                getNumericProperty("number_of_available_machines");

        if (registeredResourcesAvailable.size() < availableServers)
        {
            ClientFactory.getVmControlClient().startService(type);
        }

        // how do i wait till server is up?
        // smply go into waiting, we notify the same way we do on finished resources
        // when this method returns calling thread is gona wait

    }

    private PendingResourceRequests getPendingRequestForType(final String resourceType)
    {
        if (!pendingResourceRequests.containsKey(resourceType))
        {
            pendingResourceRequests.put(resourceType, new PendingResourceRequests());
        }

        return pendingResourceRequests.get(resourceType);
    }

    private ResourceCollection getUsedResourceCollectionForType(final String typeName)
    {
        if (!usedResources.containsKey(typeName))
        {
            usedResources.put(typeName, new UsedResources());
        }

        return usedResources.get(typeName);
    }
}
