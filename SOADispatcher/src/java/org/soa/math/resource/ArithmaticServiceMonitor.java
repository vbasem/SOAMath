/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 *
 * @author Basem
 */
public class ArithmaticServiceMonitor implements ResourceMonitor
{
    protected Map resources = new HashMap();
    
    @Override
    public void freeResource(Resource resource)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Resource acquireResource()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void startMonitor()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void stopMonitor()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Observable o, Object arg)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
