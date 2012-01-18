/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.Observable;

/**
 *
 * @author Basem
 */
public abstract class Resource
{
    public abstract boolean isFree();
    public abstract String getResourceDescriptor();
}
