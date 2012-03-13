/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.properties;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Basem
 */
public class DispatcherSettingsTest
{

    private static final String PROPERTIES_FILE_NAME = "properties_for_tests.ini";
    private static final String TEST_PROPERTY_KEY = "test_key";
    private static final String TEST_PROPERTY_VALUE = "test_value";
    private Settings settingsInstance;

    public DispatcherSettingsTest()
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
        settingsInstance = new Settings();
    }

    @After
    public void tearDown()
    {
        settingsInstance.resetProperties();
    }

    /**
     * Test of getProperty method, of class Settings.
     */
    @Test
    public void testGetProperty()
    {
        System.out.println("getProperty");
        String key = TEST_PROPERTY_KEY;
        String expResult = TEST_PROPERTY_VALUE;

        settingsInstance.setPropertyFileName(PROPERTIES_FILE_NAME);
        
        String result = settingsInstance.getProperty(key);
        
        assertEquals(expResult, result);
    }
}
