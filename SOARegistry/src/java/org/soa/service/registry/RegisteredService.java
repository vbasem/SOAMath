/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.service.registry;

import org.soa.service.types.ServiceType;

/**
 *
 * @author Basem
 */
public abstract class RegisteredService 
{
    public String id;
    public String url;
    public String type;
    
    public abstract String getId();
    public abstract String getUrl();
    public abstract String getType();
}
