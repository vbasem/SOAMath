/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.decomposer;

import org.soa.clients.DispatcherClientAdd;
import org.soa.clients.DispatcherClientMultiply;
import org.soa.clients.FutureCalculate;
import org.soa.clients.DispatcherClientDivide;
import org.soa.clients.DispatcherClientSubstract;
import org.soa.clients.DispatcherClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Basem
 */
public class PrimitiveMath
{
    private static ExecutorService futureExecuter = Executors.newCachedThreadPool();

    public static Future<String> add(String a, String b)
    {
        DispatcherClient client  = new DispatcherClientAdd(a, b);
        FutureCalculate calculate = new FutureCalculate(client);

        return futureExecuter.submit(calculate);
    }

    public static Future<String> substract(String a, String b)
    {
        FutureCalculate calculate = new FutureCalculate(new DispatcherClientSubstract(a, b));

        return futureExecuter.submit(calculate);
    }

    public static Future<String> multiply(String a, String b)
    {
        FutureCalculate calculate = new FutureCalculate(new DispatcherClientMultiply(a, b));

        return futureExecuter.submit(calculate);
    }

    public static Future<String> divide(String a, String b)
    {
        FutureCalculate calculate = new FutureCalculate(new DispatcherClientDivide(a, b));

        return futureExecuter.submit(calculate);
    }
}
