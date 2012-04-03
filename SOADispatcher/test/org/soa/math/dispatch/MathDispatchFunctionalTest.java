/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch;

import java.text.ParseException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.soa.math.queue.QueueFactory;
import org.soa.math.resource.ResourcesFactory;

/**
 *
 * @author Basem
 */
public class MathDispatchFunctionalTest
{

    public MathDispatchFunctionalTest()
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

    @Test
    public void add_validNumbers_additionResult()
    {
        final String a = "2";
        final String b = "2";
        final String result = "4";
        final int numberOfIterations = 100;
        //    final MathDispatch dispatcher = new MathDispatch();
        final MathDispatch dispatcher = new MathDispatch();
        waitTillApplicationBootstraps();

        Thread t1 = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < numberOfIterations; i++)
                {
                    try
                    {
                        try
                        {

                            assertEquals(result, dispatcher.add(a, b));
                        } catch (ParseException ex)
                        {
                            Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex)
                    {
                        Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < numberOfIterations; i++)
                {
                    try
                    {
                        try
                        {
                            assertEquals(result, dispatcher.add(a, b));
                        } catch (ParseException ex)
                        {
                            Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex)
                    {
                        Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });


        Thread t3 = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < numberOfIterations; i++)
                {
                    try
                    {
                        try
                        {
                            assertEquals(result, dispatcher.add(a, b));
                        } catch (ParseException ex)
                        {
                            Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex)
                    {
                        Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        Thread t4 = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < numberOfIterations; i++)
                {
                    try
                    {
                        try
                        {
                            assertEquals(result, dispatcher.add(a, b));
                        } catch (ParseException ex)
                        {
                            Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex)
                    {
                        Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try
        {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    @Ignore
    public void testSingleOperation() throws InterruptedException, ExecutionException, ParseException
    {
        final String a = "2";
        final String b = "2";
        final String result = "0";
        final int numberOfIterations = 100;
        //    final MathDispatch dispatcher = new MathDispatch();

        waitTillApplicationBootstraps();
        MathDispatch dispatcher = new MathDispatch();
        assertEquals(result, dispatcher.add(a, b));

    }

    @Test
    @Ignore
    public void testDispatcherStartup() throws InterruptedException
    {
        waitTillApplicationBootstraps();
        Thread.sleep(10000000);
    }

    private void waitTillApplicationBootstraps()
    {
        try
        {
            QueueFactory.getStaticQueueMonitor().startMonitor();
            ResourcesFactory.getStaticArithmaticResourceMonitor().startMonitor();

            Thread.currentThread().sleep(3000);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
