/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.*;

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
        final Integer a = 3;
        final Integer b = 1;
        final Integer result = 4;

        final MathDispatch dispatcher = new MathDispatch();

        waitTillApplicationBootstraps();

        Thread t1 = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                for (int i = 0; i < 100; i++)
                {
                    try
                    {
                        assertEquals(result, dispatcher.add(a, b));
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
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        assertEquals(result, dispatcher.add(a, b));
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
        try
        {
            t1.join();
             t2.join();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    private void waitTillApplicationBootstraps()
    {
        try
        {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MathDispatchFunctionalTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
