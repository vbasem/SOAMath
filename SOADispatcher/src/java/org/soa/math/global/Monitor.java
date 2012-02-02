/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.global;

import java.util.Observer;

/**
 *
 * @author Basem
 */
public interface Monitor extends Observer
{
    public void startMonitor();
    public void stopMonitor();

}
