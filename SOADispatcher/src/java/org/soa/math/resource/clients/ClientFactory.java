/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients;

import org.soa.math.global.AbstractFactory;

/**
 *
 * @author Basem
 */
public class ClientFactory extends AbstractFactory
{
    public static ResourceClient getAdditionClient()
    {
        return (ResourceClient) decideWhichObjectBasedOnEnv(new AdditionClient(), null);
    }
}
