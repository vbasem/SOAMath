/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Basem
 */
public class MathDispatch implements Observer
{
 
    private Thread dispatchThread = Thread.currentThread();
    
    public int add(int x, int y)
    {
        Task t = createTask(RequestType.ADDITION, x, y);
        dispatchTak(t);
        return t.getResult();
    }
    
    public int multiply(int x, int y)
    {
        Task t = createTask(RequestType.MULTIPLICATION, x, y);
        dispatchTak(t);
        return t.getResult();
    }
    
    public void dispatchTak(Task t)
    {
        QueueAccess.getRequestQueue().getAdditionQueue().queue(t, dispatchThread);
        dispatchThread.wait();
        
    }

}
