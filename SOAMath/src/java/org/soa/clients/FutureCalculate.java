/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.clients;

import java.util.concurrent.Callable;
import org.soa.math.decomposer.*;

/**
 *
 * @author Basem
 */
public class FutureCalculate implements Callable
{

    private DispatcherClient operationClient;

    public FutureCalculate(DispatcherClient operationClient)
    {
        this.operationClient = operationClient;
    }

    @Override
    public Object call() throws Exception
    {
        return operationClient.getResult();
    }
}
