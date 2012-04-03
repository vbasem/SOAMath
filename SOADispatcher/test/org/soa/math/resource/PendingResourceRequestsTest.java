/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import org.junit.*;
import static org.junit.Assert.*;
import org.soa.math.properties.SettingsRepository;

/**
 *
 * @author Basem
 */
public class PendingResourceRequestsTest
{
    private final double LOAD_DELTA = 0.01;

    public PendingResourceRequestsTest()
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
    public void testGetLoad_noFreeResourceOneUsedResourcesWithPendingRequests_returnsMaxLoad()
    {
        ActiveResourceSpace pending = new ActiveResourceSpace("test");
        pending.addRequestThreadToPendingAndIncrement(Thread.currentThread());

        assertEquals(pending.MAX_LOAD, pending.getLoad(), LOAD_DELTA);
    }

    @Test
    public void testGetLoad_noFreeResourceOneUsedResourceWithPendingRequests_returnsMaxLoad()
    {
        ActiveResourceSpace pending = new ActiveResourceSpace("test");
        
        pending.addRequestThreadToPendingAndIncrement(Thread.currentThread());
        pending.addRequestThreadToPendingAndIncrement(Thread.currentThread());
        pending.assignResourceToThread(new Resource(null, null, "1")
        {
        }, Thread.currentThread());


        assertEquals(pending.MAX_LOAD, pending.getLoad(), LOAD_DELTA);
    }

    @Test
    public void testGetLoad_allEmpty_returnsMaxLoad()
    {
        ActiveResourceSpace pending = new ActiveResourceSpace("test");

        assertEquals(pending.MAX_LOAD, pending.getLoad(), LOAD_DELTA);
    }

    @Test
    public void testGetLoad_freeResourcesAndSomeActivityNoPending_returnsCalculatedValueBetweenMinAndMax()
    {
        double numberOfFreeResources = 2;
        double pendingActivity = 0.3;
        double expectedValue = pendingActivity / numberOfFreeResources;

        ActiveResourceSpace pending = new ActiveResourceSpace("test");
        pending.setActivity(pendingActivity);
        pending.addFreeResource(new Resource(null, null, "1")
        {
            
        });
        pending.addFreeResource(new Resource(null, null, "2")
        {
        });


        assertEquals(expectedValue, pending.getLoad(), LOAD_DELTA);
    }

    @Test
    public void testGetLoad_withFreeResourceSomeActivityAndSomePending_returnsCalculatedValue()
    {
        double numberOfFreeResources = 1.0;
        double numberOfPendings = 3;
        double pendingActivity = 0.4;
        
        double expectedValue = numberOfPendings * pendingActivity / numberOfFreeResources;

        ActiveResourceSpace pending = new ActiveResourceSpace("test");
        pending.addRequestThreadToPendingAndIncrement(new Thread());
        pending.addRequestThreadToPendingAndIncrement(new Thread());
        pending.addRequestThreadToPendingAndIncrement(new Thread());
        pending.setActivity(pendingActivity);
        pending.addFreeResource(new Resource(null, null, "1")
        {
        });

        assertEquals(expectedValue, pending.getLoad(), LOAD_DELTA);
    }
}
