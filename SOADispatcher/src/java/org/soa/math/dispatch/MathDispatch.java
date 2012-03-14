/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch;

import org.soa.math.executer.task.TaskFactory;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.soa.math.executer.task.Task;
import org.soa.math.queue.QueueFactory;
import org.soa.math.resource.ResourcesFactory;
import sun.text.normalizer.UCharacter;

/**
 *
 * @author Basem
 */
@WebService()
public class MathDispatch implements Observer
{
    public MathDispatch()
    {
        QueueFactory.getStaticQueueMonitor().startMonitor();
        ResourcesFactory.getStaticArithmaticResourceMonitor().startMonitor();
    }
    
    @WebMethod(operationName="add")
    public <T> T add( T x, T y) throws InterruptedException, ExecutionException
    {
        Number  s = (Number)(x);
        Number  z = (Number)(y);
        Task t = TaskFactory.createAdditionTask(s, z);
        return (T) getFutureResultFromTask(t).get();
    }
    
    @WebMethod(operationName="multiply")
    public <T> T multiply(T x, T y)
    {
        //Task t = createTask(RequestType.MULTIPLICATION, x, y);
        //dispatchTask(t);
        return null;
    }
    
    private Future getFutureResultFromTask(Task t)
    {
        Future futureResult = 
                QueueFactory.
                getStaticRequestQueue().
                putToQueueAndGetFeatureObject(t);
        
        return futureResult;
        
    }

    @Override
    public void update(Observable o, Object arg)
    {
        throw new UnsupportedOperationException("Not sure if i need update on dispatch yet.");
    }
}
