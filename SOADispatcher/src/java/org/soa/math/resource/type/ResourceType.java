/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.type;

/**
 *
 * @author Basem
 */
public class ResourceType
{
    protected String typeName;
    
    public ResourceType(String typeName)
    {
        this.typeName = typeName;
    }
    
    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

}
