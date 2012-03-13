/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import java.util.Observable;
import org.soa.math.executer.task.Task;
import org.soa.math.resource.Resource;
import org.soa.math.resource.ResourceMonitor;
import org.soa.math.resource.ResourcesFactory;

/**
 *
 * @author Basem
 */
public abstract class TaskExecutor extends Observable implements Runnable
{
    protected Task task;
    protected Resource resource;
    
        
    public abstract void execute();
    
    public TaskExecutor(Task task)
    {
        this.task = task;
    }
    
    public Resource acquireResource()
    {
        String wantedResourceType = task.requestType.toString();
        
        ResourceMonitor monitor = ResourcesFactory.getStaticArithmaticResourceMonitor();
        
        return monitor.acquireResource(wantedResourceType);
    }
    
    /**
     * when done informs the execution control (observer)
     * 
     */
    public void triggerTaskCompletedEvent()
    {
        this.setChanged();
        this.notifyObservers(task);
    }
}
