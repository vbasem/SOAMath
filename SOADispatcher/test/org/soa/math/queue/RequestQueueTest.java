/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;
import org.soa.math.executer.task.AdditionTask;
import org.soa.math.executer.task.Task;
import org.soa.math.request.RequestType;

/**
 *
 * @author Basem
 */
public class RequestQueueTest
{

    public RequestQueueTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of queueForFutureResult method, of class RequestQueue.
     */
    @Test
    public void testQueueForFutureResult()
    {
        System.out.println("queueForFutureResult");
        String result = "";
        final String expResult = "10";
        final Task t = new AdditionTask();
        t.setRequestType(RequestType.ADDITION);
        t.setResult(expResult);
        RequestQueue instance = new RequestQueue();
        Future submitedTask = instance.putToQueueAndGetFeatureObject(t);

        Thread thread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                synchronized(t)
                {
                    t.notify();
                }
            }
        });

        thread.start();
        try
        {
            result = (String) submitedTask.get();
        } catch (Exception ex)
        {
            Logger.getLogger(RequestQueueTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }

        assertEquals(expResult, result);
    }

    /**
     * Test of getQueueForRequestType method, of class RequestQueue.
     */
    @Test
    public void testGetQueueForRequestType()
    {
        Task t = new AdditionTask();
        t.setRequestType(RequestType.ADDITION);
        RequestQueue queue = new RequestQueue();
        queue.putToQueueAndGetFeatureObject(t);
        
        assertEquals(t, queue.getQueueForRequestType(RequestType.ADDITION).poll());
    }
}
