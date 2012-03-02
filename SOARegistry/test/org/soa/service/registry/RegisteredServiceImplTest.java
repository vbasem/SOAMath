/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.service.registry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.soa.service.types.ServiceType;

/**
 *
 * @author Basem
 */
public class RegisteredServiceImplTest {
    
    public static final String TEST_ID = "123";
    public static final String TEST_URL = "http://wonderland.com";
    //public static final ServiceType TEST_SERVICE_TYPE = mock(ServiceType.class);
    public static final String TEST_SERVICE_TYPE = "ADDITION";
    
    private RegisteredService service;
          
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp()
    {
        service = new RegisteredService(TEST_ID, TEST_URL, TEST_SERVICE_TYPE);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class RegisteredServiceImpl.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String expResult = TEST_ID;
        String result = service.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUrl method, of class RegisteredServiceImpl.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        String expResult = TEST_URL;
        String result = service.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class RegisteredServiceImpl.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = TEST_SERVICE_TYPE;
        String result = service.getType();
        assertEquals(expResult, result);
    }
}
