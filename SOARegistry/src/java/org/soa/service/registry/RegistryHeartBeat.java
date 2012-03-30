/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.service.registry;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basem
 */
public class RegistryHeartBeat implements Runnable
{

    public static final long TIME_TO_DEATH_OF_SERVICE = 60000;
    public static final long TIME_TO_SLEEP_BEFORE_NEXT_CHECK = 60000;
    
    protected static Thread beatMonitor = null;
    protected boolean keepChecking = true;

    public static void startMonitoringHeartBeats()
    {
        if (beatMonitor == null)
        {
            beatMonitor = new Thread(new RegistryHeartBeat());
            beatMonitor.start();
        }
    }

    @Override
    public void run()
    {
        while (keepChecking)
        {
            removeDeadServices();
            sleepTillNextCheck();
        }
    }

    protected void removeDeadServices()
    {
        long currentTime = System.currentTimeMillis();
        
        synchronized (Register.class)
        {
            for (RegisteredService service : Register.listAllRegisteredServices())
            {
                Logger.getLogger("heartbeat").severe("current time = " + currentTime + " ; and service time: " + service.getRegisteredAt());
                if ((currentTime - service.getRegisteredAt()) > TIME_TO_DEATH_OF_SERVICE)
                {
                    Register.unregisterService(service.getId());
                }
            }
        }
    }
    
    protected void sleepTillNextCheck()
    {
        try
        {
            Thread.sleep(TIME_TO_SLEEP_BEFORE_NEXT_CHECK);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(RegistryHeartBeat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
