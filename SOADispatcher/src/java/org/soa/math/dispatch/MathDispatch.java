/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch;

import java.text.NumberFormat;
import java.text.ParseException;
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

    }
    
    @WebMethod(operationName="add")
    public String add(String x, String y) throws InterruptedException, ExecutionException, ParseException
    {
        Number a = NumberFormat.getInstance().parse(x);
        Number b = NumberFormat.getInstance().parse(y);

        Logger.getLogger("asd").severe("" + x.toString() + " " + y.toString());

        Task t = TaskFactory.createAdditionTask(a, b);
        
        return getFutureResultFromTask(t).get().toString();
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
