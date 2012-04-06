/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.clients;

/**
 *
 * @author Basem
 */
public class ClientFactory
{
    private static final DispatcherClient registryService = new DispatcherClient();
    private static boolean testMode = false;
    
    public static void setTestMode(boolean mode)
    {
        testMode = mode;
    }
    
    public static boolean isTestMode()
    {
        return testMode;
    }

    public static DispatcherClient getDispatcherClient()
    {
        if (isTestMode())
        {
            return null;
        }

        return registryService;
    }
}
