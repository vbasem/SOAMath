/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;
import org.soa.math.executer.task.Task;
import org.soa.math.queue.QueueAccess;

/**
 *
 * @author Basem
 */
public class MathDispatchFunctionalTest {
    
    public MathDispatchFunctionalTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void add_validNumbers_additionResult()
    {
        Integer a = 3;
        Integer b = 1;
        Integer result = 4;
        
        MathDispatch dispatcher = new MathDispatch();
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
