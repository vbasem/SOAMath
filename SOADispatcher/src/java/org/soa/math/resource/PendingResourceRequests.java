/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class allows to keep count of pending requests of similar type; the main
 * purpose of this class is to allow us to monitor requests.
 *
 * The counter could be used to give priority to resource aquisitions and
 * servers available for services fulfilling this type of request.
 *
 * @author Basem
 */
public class PendingResourceRequests implements Comparable<PendingResourceRequests>
{

    private String resourceType;
    private int requestCounter = 0;
    private Queue<Thread> threadsPending = new ConcurrentLinkedQueue<Thread>();
    private Map resourceBookedForThread = new HashMap();

    public void addRequestThreadToPendingAndIncrement(Thread thread)
    {
        threadsPending.add(thread);
        increment();
    }
    
    public Thread getPendingThreadAndDecrement()
    {
        decrement();
        return threadsPending.poll();
    }
    
    public void assignResourceToThread(Resource res, Thread t)
    {
        resourceBookedForThread.put(t, res);
    }
    
    public Resource getResourceAssignedToPendingThread(Thread t)
    {
        Resource resource = (Resource) resourceBookedForThread.remove(t);
        
        return resource;
    }

    public void increment()
    {
        requestCounter++;
    }

    public void decrement()
    {
        requestCounter--;
    }

    public int getRequestCounter()
    {
        return requestCounter;
    }

    public void setRequestCounter(int requestCounter)
    {
        this.requestCounter = requestCounter;
    }

    public String getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(String resourceType)
    {
        this.resourceType = resourceType;
    }
    
    public boolean isAnyRequestPending()
    {
        return getRequestCounter() > 0 ? true : false;
    }

    @Override
    public int compareTo(PendingResourceRequests o)
    {
        return Integer.compare(this.getRequestCounter(), o.getRequestCounter());
    }
}
