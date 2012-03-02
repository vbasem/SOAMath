/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import org.soa.math.executer.task.Task;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Basem
 */
public class ExecutionControl extends Observable implements Observer
{
    public void startExecuterWithTask(Task task)
    {
        TaskExecutor exec = new ThreadedTaskExecutor(task);
        exec.addObserver(this);
        Thread t = new Thread(exec);
        t.start();
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
