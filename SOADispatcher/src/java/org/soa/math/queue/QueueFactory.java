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
    protected static QueueMonitor staticQueueMonitorInstance = null;
    
    public static QueueMonitor getStaticQueueMonitor()
    {
        if (isTestMode())
        {
            return null;
        }
        
        return instantiateQueueMonitorSingleton();
    }
    
    protected static QueueMonitor instantiateQueueMonitorSingleton()
    {
        if (staticQueueMonitorInstance == null)
        {
            staticQueueMonitorInstance = new QueueMonitor();
        }
        
        return staticQueueMonitorInstance;
    }
    
    
}
