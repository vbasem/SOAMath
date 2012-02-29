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
public class RegistryServiceClientTest
{
    
    public RegistryServiceClientTest()
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
    public void getAllRegisteredServices_shouldNotThrowException()
    {
        RegistryServiceClient client = new RegistryServiceClient();
        
        client.getAllRegisteredServices();
    }

}
