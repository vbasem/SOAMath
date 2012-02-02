/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Basem
 */
public class AlarmTest
{
    
    public AlarmTest()
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
     * Test of main method, of class Alarm.
     */
    @Test
    public void testMain() throws Exception
    {
        ExecutorService ser = Executors.newCachedThreadPool();
        final Sleeper s = new Sleeper();
        Future f = ser.submit(s);
        Thread th = new Thread(new Runnable() {

            @Override
            public void run()
            {
                synchronized(s)
                {
                    s.notify();
                }
            }
        });
        
        th.start();
        System.out.print(f.get());
    }
}
