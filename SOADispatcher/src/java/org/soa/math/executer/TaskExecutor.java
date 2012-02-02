/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import org.soa.math.executer.task.Task;
import java.util.Observable;
import org.soa.math.request.RequestType;
import org.soa.math.resource.Resource;

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
    
    public Resource acquireResource(RequestType type)
    {
        return ResourceManager.getServiceResource(type);
    }
    
    public void triggerTaskCompletedEvent()
    {
        this.setChanged();
        this.notifyObservers(task);
    }

}
