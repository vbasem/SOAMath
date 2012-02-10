/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.type;

/**
 *
 * @author Basem
 */
public class ArithmaticWebServiceResourceType extends WebServiceResourceType
{
    public static final ResourceType ADDITION = new ResourceType("addition");
    public static final ResourceType MULTIPLICATION = new ResourceType("multiplication");
    public static final ResourceType SUBSTRACTION = new ResourceType("substraction");
    public static final ResourceType DIVISION = new ResourceType("division");
    
    public ArithmaticWebServiceResourceType(String typeName)
    {
        super(typeName);
    }
    
}
