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
public class ArithmaticResource extends WebServiceResource
{
    public ArithmaticResource(ResourceClient client, ResourceType type)
    {
        super(client, type);
    }
}