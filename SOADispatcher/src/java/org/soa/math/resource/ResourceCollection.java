/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Basem
 */
public abstract class ResourceCollection extends HashMap<String, Resource>
{

    /**
     * remove an item from the collection and returns it
     *
     * @return Resource
     */
    public Resource pop()
    {
        if (isEmpty())
        {
            throw new IllegalAccessError("no elements in collection to pop");
        }

        String randomElementKey = getRandomResourceKey();
        Resource resource = get(randomElementKey);
        remove(randomElementKey);

        return resource;
    }

    protected String getRandomResourceKey()
    {
        Iterator it = keySet().iterator();

        String randomElementKey = (String) it.next();

        return randomElementKey;
    }
}
