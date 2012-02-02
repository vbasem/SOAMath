/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.soa.math.request.RequestType;

/**
 *
 * @author Basem
 */
public class Task<T> implements Callable
{

    public RequestType requestType;
    public T result;
    private List<T> taskOperands = new ArrayList<T>();

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

    public RequestType getRequestType()
    {
        return requestType;
    }

    public void setRequestType(RequestType requestType)
    {
        this.requestType = requestType;
    }
    
    public void addOperand(T operands)
    {
        taskOperands.add(operands);
    }
    
    public List<T> getTaskOperands()
    {
        return taskOperands;
    }

    @Override
    public Object call() throws Exception
    {
        synchronized (this)
        {
            this.wait();
        }

        return getResult();
    }
}
