/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import java.util.Observable;
import org.soa.math.resource.Resource;

/**
 *
 * @author Basem
 */
public abstract class Executer extends Observable implements Runnable
{
    private Task task;
    private Resource resource;
    
    public Executer(Task task)
    {
        this.task = task;
    }
    
    public Resource acquireResource(RequestType type)
    {
        
    }
    
    public void execute()
    {   
        
        
    }

    @Override
    public void run()
    {
        resource = acquireResource(task.getType());
        task.setResult(resource.getResourceClient().executeTask(task));
        
        this.setChanged();
        this.notifyObservers(task);
    }
}
