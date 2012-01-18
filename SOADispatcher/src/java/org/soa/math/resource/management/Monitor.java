/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.management;

import org.soa.math.resource.Resource;

/**
 *
 * @author Basem
 */
public interface Monitor 
{
    
    public void freeResource(Resource resource);
    public Resource acquireResource();
}
