/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.global;

/**
 *
 * @author Basem
 */
public class AbstractFactory
{
    protected static boolean testMode = false;
    
    protected static Object decideWhichObjectBasedOnEnv(Object normalModeObj, Object testModeObj)
    {
        if (isTestMode())
        {
            return testModeObj;
        }
        else
        {
            return normalModeObj;
        }
    }
    
    public static void turnOnTestMode()
    {
        testMode = true;
    }
    
    public static void turnOffTestMode()
    {
        testMode = false;
    }
    
    public static boolean isTestMode()
    {
        return testMode;
    }
    
}
