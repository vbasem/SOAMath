/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.Observable;
import org.soa.math.resource.clients.ResourceClient;
import org.soa.math.resource.clients.WebServiceClient;
import org.soa.math.resource.type.ResourceType;

/**
 *
 * @author Basem
 */
public abstract class WebServiceResource extends Resource
{
    public WebServiceResource(WebServiceClient client, ResourceType type)
    {
        super(client, type);
    }
}
