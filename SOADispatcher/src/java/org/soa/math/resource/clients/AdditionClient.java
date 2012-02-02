/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import org.soa.math.executer.task.Task;

/**
 *
 * @author Basem
 */
public class AdditionClient implements ResourceClient
{
    // @WebServiceRef(wsdlLocation =
    // "http://192.168.56.1:8080/VirtualSOA/RegistryAndLookUpService?wsdl")

    private services.arithmatic.AdditionServiceService service;

    @Override
    public void execute(Task task)
    {
        Iterator itr = task.getTaskOperands().iterator();
        int result = add((Integer) itr.next(), (Integer) itr.next());
        task.setResult(result);
    }

    protected int add(int x, int y)
    {
        service = new services.arithmatic.AdditionServiceService();

        services.arithmatic.AdditionService port = service.getAdditionServicePort();
        int additionResult = port.calculate(x, y);

        try
        {
            ((Closeable) port).close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return additionResult;
    }
}
