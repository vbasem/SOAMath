/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.soa.math.executer.task.AdditionTask;
import org.soa.math.executer.task.Task;
import org.soa.math.request.RequestType;

/**
 *
 * @author Basem
 */
public class MathDispatch implements Observer
{
 
    private Thread dispatchThread = Thread.currentThread();
    
    public int add(int x, int y) throws InterruptedException, ExecutionException
    {
        Task t = new AdditionTask<Integer>((RequestType.ADDITION, x, y);
        String result = (String) dispatchTask(t).get();
        return Integer.getInteger(result);
    }
    
    public int multiply(int x, int y)
    {
        //Task t = createTask(RequestType.MULTIPLICATION, x, y);
        //dispatchTask(t);
        return 0;
    }
    
    public Future dispatchTask(Task t)
    {
        Future futureResult = QueueAccess.getRequestQueue().getAdditionQueue().queueForFutureResult(t, dispatchThread);
        return futureResult;
        
    }
}
