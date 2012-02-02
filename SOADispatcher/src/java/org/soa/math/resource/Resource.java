/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.Observable;
import org.soa.math.executer.task.Task;
import org.soa.math.resource.clients.ResourceClient;

/**
 *
 * @author Basem
 */
public abstract class Resource extends Observable
{
    private boolean busyStatus = false;
    private String resourceDescriptor;
    private ResourceClient client;

    public Resource(ResourceClient client)
    {
        this.client = client;
    }
    
    public boolean isBusy()
    {
        return busyStatus;
    }

    public void setBusyStatus(boolean busyStatus)
    {
        this.busyStatus = busyStatus;
    }
    
    public void setResourceDescriptor(String resourceDescriptor)
    {
        this.resourceDescriptor = resourceDescriptor;
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
