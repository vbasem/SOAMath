/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.queue;

import org.soa.math.global.AbstractFactory;

/**
 *
 * @author Basem
 */
public class QueueFactory extends AbstractFactory
{

    protected static final QueueMonitor staticQueueMonitorInstance = new QueueMonitor();
    private static final TaskQueue requestQueue = new RequestQueue();

    public static TaskQueue getStaticRequestQueue()
    {
        if (isTestMode())
        {
            return null;
        }
        
        return requestQueue;
    }

    public static QueueMonitor getStaticQueueMonitor()
    {
        if (isTestMode())
        {
            return null;
        }

        return staticQueueMonitorInstance;
    }
}
