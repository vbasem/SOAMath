/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.soa.math.properties.SettingsRepository;

/**
 * This class allows to keep count of pending requests of similar type; the main
 * purpose of this class is to allow us to monitor requests.
 *
 * The counter could be used to give priority to resource aquisitions and
 * servers available for services fulfilling this type of request.
 *
 * @author Basem
 */
public class ResourceSpace extends ServiceResourcesAndRequests implements Comparable<ResourceSpace>
{

    public static final double MAX_LOAD = SettingsRepository.getConcurrencySettings().
            getNumericProperty("max_number_of_tasks_being_executed");
    public static final double MIN_LOAD = 0;
    
    private static final int MAX_ACTIVITY = 10;
    private static final int MIN_ACTIVITY = 1;
    private static final int ACTIVITY_STEP = 1;
    
    private int activity = MIN_ACTIVITY;
    private int requestCounter = 0;
    
    private Queue<Thread> threadsPending = new ConcurrentLinkedQueue<Thread>();
    private Map resourceBookedForThread = new HashMap();

    public ResourceSpace(String pendingForResourceType)
    {
        super(pendingForResourceType);
    }

    public void addRequestThreadToPendingAndIncrement(Thread thread)
    {
        increaseActivity();
        threadsPending.add(thread);
        increment();
    }

    public void increaseActivity()
    {
        if (activity != MAX_ACTIVITY)
        {
            activity += ACTIVITY_STEP;
        }
    }

    public void reduceActivity()
    {
        if (activity != MIN_ACTIVITY)
        {
            activity -= ACTIVITY_STEP;
        }
    }

    public void updateActivity()
    {
        if (!isAnyRequestPending())
        {
            reduceActivity();
        }
    }

    public double getLoad()
    {
        double activityFactor = 1;
        // no free resources : always max load
        if (freeResources.isEmpty())
        {
            return MAX_LOAD;
        }

        if (this.getRequestCounter() != 0)
        {
            activityFactor = (double) this.getRequestCounter();
        }

        double baseLoad = activityFactor * (double) this.getActivity();

        return baseLoad / (double) freeResources.size();

    }

    public Thread getPendingThreadAndDecrement()
    {
        decrement();
        return threadsPending.poll();
    }

    public void assignResourceToThread(Resource res, Thread t)
    {
        busyResources.put(res.getResourceDescriptor(), res);
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
        return threadsPending.isEmpty() ? false : true;
    }

    public int getActivity()
    {
        return this.activity;
    }

    public void setActivity(int activityValue)
    {
        this.activity = activityValue;
    }

    @Override
    public int compareTo(ResourceSpace o)
    {
        return Double.compare(this.getLoad(), o.getLoad());
    }
}
