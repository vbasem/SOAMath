/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import org.soa.math.resource.clients.ResourceClient;
import org.soa.math.resource.type.ResourceType;

/**
 *
 * @author Basem
 */
public abstract class WebServiceResource extends Resource
{
    public WebServiceResource(ResourceClient client, ResourceType type, String resourceDescriptor)
    {
       super(client, type, resourceDescriptor);
    }
}
