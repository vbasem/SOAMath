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
public class ResourceSpaceTests
{
    private final double LOAD_DELTA = 0.0001;

    public ResourceSpaceTests()
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
        ResourceSpace pending = new ResourceSpace("test");
        pending.addRequestThreadToPendingAndIncrement(Thread.currentThread());

        assertEquals(pending.MAX_LOAD, pending.getLoad(), LOAD_DELTA);
    }

    @Test
    public void testGetLoad_noFreeResourceOneUsedResourceWithPendingRequests_returnsMaxLoad()
    {
        ResourceSpace pending = new ResourceSpace("test");
        
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
        ResourceSpace pending = new ResourceSpace("test");

        assertEquals(pending.MAX_LOAD, pending.getLoad(), LOAD_DELTA);
    }

    @Test
    public void testGetLoad_freeResourcesAndSomeActivityNoPending_returnsCalculatedValueBetweenMinAndMax()
    {
        int numberOfFreeResources = 2;
        int pendingActivity = 3;
        double expectedValue = (double) pendingActivity / (double) numberOfFreeResources;

        ResourceSpace pending = new ResourceSpace("test");
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
        int numberOfFreeResources = 1;
        int numberOfPendings = 3;
        int pendingActivity = 4;
        
        int expectedValue = numberOfPendings * pendingActivity / numberOfFreeResources;

        ResourceSpace pending = new ResourceSpace("test");
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
