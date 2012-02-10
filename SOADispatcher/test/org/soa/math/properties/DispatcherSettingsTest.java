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
public class DispatcherSettingsTest {
    
    private static final String PROPERTIES_FILE_NAME = "properties_for_tests.ini";
    private static final String TEST_PROPERTY_KEY = "test_key";
    private static final String TEST_PROPERTY_VALUE = "test_value";
    
    public DispatcherSettingsTest() {
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
        Settings.resetProperties();
    }

    /**
     * Test of getProperty method, of class Settings.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        String key = TEST_PROPERTY_KEY;
        String expResult = TEST_PROPERTY_VALUE;
        Settings.setPropertiesFileName(PROPERTIES_FILE_NAME);
        String result = Settings.getProperty(key);
        assertEquals(expResult, result);
    }
}
