/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.service.registry;

/**
 *
 * @author Basem
 */
public class RegisteredService 
{
    public String id;
    public String url;
    public String type;
    
    public RegisteredService()
    {
        
    }
    
    public RegisteredService(String id, String url, String type)
    {
        this.id = id;
        this.url = url;
        this.type = type;
    }

    public String getId() 
    {
        return id;
    }

    public String getUrl() 
    {
        return url;
    }

    public String getType() 
    {
        return type;
    }
}
