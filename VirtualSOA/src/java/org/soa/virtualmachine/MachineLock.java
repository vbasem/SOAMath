/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Basem
 */
public class MachineLock
{
    private static Map locks = new ConcurrentHashMap();
    
    synchronized public static void lock(String machineName)
    {
       locks.put(machineName, true);
    }
    
    synchronized public static void unlock(String machineName)
    {
        locks.remove(machineName);
    }
    
    synchronized public static boolean isLocked(String machineName)
    {
        return locks.containsKey(machineName);
    }
}
