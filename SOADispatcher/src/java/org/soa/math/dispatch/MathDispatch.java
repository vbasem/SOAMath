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
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.soa.math.executer.task.Task;
import org.soa.math.queue.QueueFactory;
import org.soa.math.resource.ResourcesFactory;

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
    public int add(int x, int y) throws InterruptedException, ExecutionException
    {
        Task t = TaskFactory.createAdditionTask(x, y);
        String result = (String) getFutureResultFromTask(t).get();
        return Integer.getInteger(result);
    }
    
    @WebMethod(operationName="multiply")
    public int multiply(int x, int y)
    {
        //Task t = createTask(RequestType.MULTIPLICATION, x, y);
        //dispatchTask(t);
        return 0;
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
