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
public class RegisteredServiceImpl extends RegisteredService
{
    public RegisteredServiceImpl(String id, String url, String type)
    {
        this.id = id;
        this.url = url;
        this.type = type;
    }

    @Override
    public String getId() 
    {
        return id;
    }

    @Override
    public String getUrl() 
    {
        return url;
    }

    @Override
    public String getType() 
    {
        return type;
    }
    
    
    
}
