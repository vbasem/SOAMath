/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients.arithmatic;

import java.util.Iterator;
import org.soa.math.executer.task.Task;
import org.soa.math.resource.clients.ResourceClient;
import org.soa.math.resource.clients.WebServiceClient;

/**
 *
 * @author Basem
 */
public abstract class ArithmaticWebServiceClient extends WebServiceClient implements ResourceClient
{
    public ArithmaticWebServiceClient(String url)
    {
        super(url);
    }
       

    @Override
    public void execute(Task task)
    {
        Iterator itr = task.getTaskOperands().iterator();
        task.setResult(
                calculate(itr.next(), itr.next())
                );
    }
    
     protected abstract <T> Object calculate(T x, T y); 
}
