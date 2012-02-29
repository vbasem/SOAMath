/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.Observable;
import org.soa.math.executer.task.Task;
import org.soa.math.resource.clients.ResourceClient;
import org.soa.math.resource.type.ResourceType;

/**
 *
 * @author Basem
 */
public abstract class Resource extends Observable
{

    private boolean busyStatus = false;
    private String resourceDescriptor;
    private ResourceClient client;
    private ResourceType type;

    public ResourceClient getClient()
    {
        return client;
    }

    public Resource(ResourceClient client, ResourceType type)
    {
        super();
        this.client = client;
        this.type = type;
    }

    public Resource()
    {
    }

    public void setClient(ResourceClient client)
    {
        this.client = client;
    }

    public ResourceType getType()
    {
        return type;
    }

    public void setType(ResourceType type)
    {
        this.type = type;
    }

    public boolean isBusy()
    {
        return busyStatus;
    }

    public void setBusyStatus(boolean busyStatus)
    {
        this.busyStatus = busyStatus;
    }

    public void setResourceDescriptors(String resourceId)
    {
        this.resourceDescriptor = resourceId;
    }

    public String getResourceDescriptor()
    {
        return this.resourceDescriptor;
    }

    public void assignTaskToResource(Task task)
    {
        this.setBusyStatus(true);
        this.client.execute(task);
        signalAvailableResource();
    }

    protected void signalAvailableResource()
    {
        this.setBusyStatus(false);
        this.notifyObservers();
    }
}
