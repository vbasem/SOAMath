/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import org.soa.math.global.Monitor;

/**
 *
 * @author Basem
 */
public interface ResourceMonitor extends Monitor
{
    public void freeResource(Resource resource);
    public Resource acquireResource(String resourceType);   
}
