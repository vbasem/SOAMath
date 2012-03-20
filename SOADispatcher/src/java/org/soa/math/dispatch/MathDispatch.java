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
    @WebMethod(operationName = "add")
    public String add(@WebParam(name="operand1") String operand1, @WebParam(name="operand2") String operand2) throws InterruptedException, ExecutionException, ParseException
    {
        Task t = TaskFactory.createAdditionTask(getNumber(operand1), getNumber(operand2));

        return getFutureResultFromTask(t).get().toString();
    }

    @WebMethod(operationName = "multiply")
    public String multiply(String x, String y) throws InterruptedException, ExecutionException, ParseException
    {
        Task t = TaskFactory.createMultiplicationTask(getNumber(x), getNumber(y));

        return getFutureResultFromTask(t).get().toString();
    }

    @WebMethod(operationName = "divide")
    public String divide(String x, String y) throws InterruptedException, ExecutionException, ParseException
    {
        Task t = TaskFactory.createDivisionTask(getNumber(x), getNumber(y));

        return getFutureResultFromTask(t).get().toString();
    }

    @WebMethod(operationName = "substract")
    public String substract(String x, String y) throws InterruptedException, ExecutionException, ParseException
    {
        Task t = TaskFactory.createSubstractionTask(getNumber(x), getNumber(y));

        return getFutureResultFromTask(t).get().toString();
    }

    private Number getNumber(String stringNumber) throws ParseException
    {
        return NumberFormat.getInstance().parse(stringNumber);
    }

    private Future getFutureResultFromTask(Task t)
    {
        Future futureResult =
                QueueFactory.getStaticRequestQueue().
                putToQueueAndGetFeatureObject(t);

        return futureResult;

    }
    
    @WebMethod(exclude=true)
    @Override
    public void update(Observable o, Object arg)
    {
        throw new UnsupportedOperationException("Not sure if i need update on dispatch yet.");
    }
}
