/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine.config;

import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author Basem
 */
public class SettingsTest
{
    
    public SettingsTest()
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
    public void getProperty_testPropertyName_returnsTestPropertyValue()
    {
        String expected = "http://test_service:8080";
        Settings s = new Settings();
        String value = s.getProperty("test_service");
        assertEquals(expected, value);
    }
}
