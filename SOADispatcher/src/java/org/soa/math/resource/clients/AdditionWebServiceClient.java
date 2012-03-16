/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import javax.xml.namespace.QName;
import org.soa.math.executer.task.Task;

/**
 *
 * @author Basem
 */
public class AdditionWebServiceClient extends WebServiceClient implements ResourceClient
{

    private services.arithmatic.AdditionServiceService service;

    public AdditionWebServiceClient(String url)
    {
        super(url);
    }
       
    @Override
    public QName getDefaultQname()
    {
        return new QName("http://arithmatic.services/", "AdditionServiceService");
    }

    @Override
    public void execute(Task task)
    {
        Iterator itr = task.getTaskOperands().iterator();
        task.setResult(
                add(itr.next(), itr.next())
                );
    }

    protected <T> Object add(T x, T y)
    {
        service = new services.arithmatic.AdditionServiceService(getEndPointUrl(), getQname());

        services.arithmatic.AdditionService port = service.getAdditionServicePort();

        Object result = port.calculate(x, y);
        
        try
        {
            ((Closeable) port).close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return result;
    }
}
