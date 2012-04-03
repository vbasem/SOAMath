/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

/**
 *
 * @author Basem
 */
public class ServiceResourcesAndRequests
{

    protected String resourceType;
    protected ResourceCollection freeResources;
    protected ResourceCollection busyResources;

    public ServiceResourcesAndRequests(String resourceType)
    {
        this.resourceType = resourceType;
        freeResources = new FreeResources(resourceType);
        busyResources = new UsedResources(resourceType);
    }

    Resource getFreeResourceIfAvailable()
    {
        return freeResources.getFirstAvailableElementOrNull();
    }

    public void removeResourceFromAllCollections(Resource res)
    {
        freeResources.remove(res.getResourceDescriptor());
        busyResources.remove(res.getResourceDescriptor());
    }

    public ResourceCollection getFreeResourceCollection()
    {
        return freeResources;
    }

    public ResourceCollection getBusyResourceCollection()
    {
        return busyResources;
    }
    
    public void addFreeResource(Resource res)
    {
        this.freeResources.put(res.getResourceDescriptor(), res);
    }
    
    public void addBusyResource(Resource res)
    {
        this.busyResources.put(res.getResourceDescriptor(), res);
    }
    
    public Resource removeFreeResource(Resource res)
    {
        return this.freeResources.remove(res.getResourceDescriptor());
    }
    
    public Resource removeBusyResource(Resource res)
    {
        return this.busyResources.remove(res.getResourceDescriptor());
    }
    

    public Resource removeFirstFreeResource()
    {
        return freeResources.pop();
    }
    
    public void moveResourceFromFreeToBusy(Resource res)
    {
        Resource movingResource = freeResources.remove(res.getResourceDescriptor());
        busyResources.put(movingResource.getResourceDescriptor(), movingResource);
    }
    
}
