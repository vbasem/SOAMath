/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import org.soa.math.executer.task.Task;

/**
 *
 * @author Basem
 */
public class ThreadedTaskExecutor extends TaskExecutor implements Runnable
{

    public ThreadedTaskExecutor(Task t)
    {
        super(t);
    }
    
    @Override
    public void execute()
    {
        new Thread(this).start();
    }
    
    @Override
    public void run()
    {
        resource = acquireResource();
        resource.getClient().execute(task);
        //we are done, time to notifier observers
        triggerTaskCompletedEvent();
    }
    
}
