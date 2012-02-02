/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.queue;

/**
 *
 * @author Basem
 */
public class QueueAccess
{
    private static TaskQueue requestQueue = null;
    
    public static TaskQueue getRequestQueue()
    {
        if (requestQueue == null)
        {
            requestQueue = new RequestQueue();
        }
        
        return requestQueue;
    }
}
