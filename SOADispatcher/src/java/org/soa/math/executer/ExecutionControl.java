/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import java.util.Observable;
import java.util.Observer;
import org.soa.math.executer.task.Task;

/**
 *
 * @author Basem
 */
public class ExecutionControl extends Observable implements Observer
{
    public void startExecuterWithTask(Task task)
    {
        TaskExecutor exec = ExecutorFactory.getThreadedTaskExecutor(task);
        exec.addObserver(this);
        exec.execute();
    }
    
    /**
     * notify the task object that its result has been set and it can return
     * the value from its feature get()
     * @param o
     * @param Task task 
     */
    @Override
    public void update(Observable o, Object arg)
    {
        synchronized(arg)
        {
            arg.notify();
        }
        setChanged();
        notifyObservers();
    }
    
}
