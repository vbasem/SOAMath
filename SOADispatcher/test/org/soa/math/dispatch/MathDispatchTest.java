/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch;

import org.junit.*;
import static org.junit.Assert.*;
import org.soa.math.queue.QueueAccess;

/**
 *
 * @author Basem
 */
public class MathDispatchTest {
    
    public MathDispatchTest() {
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

    /**
     * Test of add method, of class MathDispatch.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        int x = 0;
        int y = 0;
        MathDispatch instance = new MathDispatch();
        Task task = QueueAccess.getRequestQueue().getWaitingTask();
        int expResult = 0;
        task.setResult(expResult);
        int result = instance.add(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class MathDispatch.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        int x = 0;
        int y = 0;
        MathDispatch instance = new MathDispatch();
        int expResult = 0;
        int result = instance.multiply(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dispatchTak method, of class MathDispatch.
     */
    @Test
    public void testDispatchTak() {
        System.out.println("dispatchTak");
        Task t = null;
        MathDispatch instance = new MathDispatch();
        instance.dispatchTask(t);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
