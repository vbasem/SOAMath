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
import org.soa.math.global.Monitor;
import org.soa.math.properties.SettingsRepository;

/**
 *
 * @author Basem
 */
public class QueueMonitor implements Monitor, Runnable
{

    private int slotsUsed = 0;
    private int slotsAvailable = SettingsRepository.
                getConcurrencySettings().
                getNumericProperty("max_number_of_tasks_being_executed");
    
    private Iterator itr = null;
    private final Thread monitoringThread = new Thread(this);
    private boolean stopMonitorThread = false;
    

    @Override
    public void run()
    {
        while (!stopMonitorThread)
        {
            fetchNewTaskRoundRobin();
            try
            {
                //monitoringThread.sleep(20);
                synchronized (monitoringThread)
                {
                    monitoringThread.wait();
                }
            } catch (InterruptedException ex)
            {
                Logger.getLogger(QueueMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void startMonitor()
    { 
        if (!monitoringThread.isAlive())
        {
            Logger.getLogger(QueueMonitor.class.getName()).log(Level.WARNING, "starting queue monitor");
            monitoringThread.start();
            ExecutorFactory.getStaticExecutionControl().addObserver(this);
        }
        else
        {
            synchronized (monitoringThread)
            {
                monitoringThread.notify();
            }
        }

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
        // TODO: use a wake up system
        startMonitor();
    }
    
    protected void fetchNewTaskRoundRobin()
    {
        Iterator iterator = getNewIteratorForQueuesIfNeeded();
        
        while (areFreeSlotsAvailable() && iterator.hasNext())
        {
            Task task = (Task) iterator.next();
            
            if (task == null)
            {
                continue;
            }

            occupySlot();
            putTaskFromQueueForExecution(task);
        }
                    
    }
    
    protected Iterator getNewIteratorForQueuesIfNeeded()
    {
        if (itr == null || !itr.hasNext())
        {
            itr = QueueFactory.getStaticRequestQueue().iterator();
        }
        
        return itr;
    }

    protected boolean areFreeSlotsAvailable()
    {
        boolean answer;
        
        if (slotsUsed < slotsAvailable)
        {
            answer = true;
        }
        else
        {
            answer = false;
        }
        
        return answer;
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
