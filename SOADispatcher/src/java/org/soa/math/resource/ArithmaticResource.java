/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import org.soa.math.resource.clients.WebServiceClient;
import org.soa.math.resource.type.ResourceType;

/**
 *
 * @author Basem
 */
public class ArithmaticResource extends WebServiceResource
{
    public ArithmaticResource(WebServiceClient client, ResourceType type)
    {
        super(client, type);
    }
}