/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.queue;

import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.soa.math.executer.ExecutorFactory;
import org.soa.math.executer.task.Task;
import org.soa.math.properties.DispatcherSettings;
import org.soa.math.global.Monitor;

/**
 *
 * @author Basem
 */
public class QueueMonitor implements Monitor, Runnable
{

    private int slotsUsed = 0;
    private Iterator itr = null;
    private final Thread monitoringThread = new Thread(this);
    private boolean stopMonitorThread = false;
    

    @Override
    public void run()
    {
        while (!stopMonitorThread)
        {
            fetchNewTaskRoundRobin();
            synchronized(monitoringThread)
            {
                try
                {
                    monitoringThread.wait();
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(QueueMonitor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void startMonitor()
    {
        monitoringThread.run();
    }
    
    @Override
    public void stopMonitor()
    {
        this.stopMonitorThread = true;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        freeSlot();
        synchronized(monitoringThread)
        {
            monitoringThread.notify();
        }
    }
    
    protected void fetchNewTaskRoundRobin()
    {
        Iterator iterator = getNewIteratorForQueuesIfNeeded();
        while (areFreeSlotsAvailable() && iterator.hasNext())
        {
            occupySlot();
            Task task = (Task) iterator.next();
            putTaskFromQueueForExecution(task);
        }
                    
    }
    
    protected Iterator getNewIteratorForQueuesIfNeeded()
    {
        if (itr == null || !itr.hasNext())
        {
            itr =  QueueAccess.getRequestQueue().iterator();
        }
        
        return itr;
    }

    protected boolean areFreeSlotsAvailable()
    {
        if (slotsUsed < DispatcherSettings.getNumericProperty("max_number_of_tasks_being_executed"))
        {
            return true;
        }
        
        return false;
    }
    
    protected void freeSlot()
    {
        --slotsUsed;
    }
    
    protected void occupySlot()
    {
        ++slotsUsed;
    }
    
    protected void putTaskFromQueueForExecution(Task task)
    {
        ExecutorFactory.getStaticExecutionControl().startExecuterWithTask(task);
    }
}
