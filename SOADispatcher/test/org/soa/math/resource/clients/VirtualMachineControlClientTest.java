/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Basem
 */
public class VirtualMachineControlClientTest
{
    
    public VirtualMachineControlClientTest()
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
    public void stopService_shouldNotThrowException()
    {
        VirtualMachineControlClient client = new VirtualMachineControlClient();
        
        client.stopService("123");
    }
}
