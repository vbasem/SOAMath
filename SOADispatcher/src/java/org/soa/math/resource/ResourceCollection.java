/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Basem
 */
public abstract class ResourceCollection extends ConcurrentHashMap<String, Resource> implements Comparable<ResourceCollection>
{
    private String identifier;

    public ResourceCollection(String identifier)
    {
        super();
        this.identifier = identifier;
    }

    public String getCollectionIdentifier()
    {
        return this.identifier;
    }

    @Override
    public int compareTo(ResourceCollection o)
    {
        // default is reverse ordering
        return -Integer.compare(this.size(), o.size());
    }

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

    public Resource getFirstAvailableElementOrNull()
    {
        synchronized (this)
        {
            if (this.size() > 0)
            {
                return this.pop();
            }
        }

        return null;
    }

    protected String getRandomResourceKey()
    {
        Iterator it = keySet().iterator();

        String randomElementKey = (String) it.next();

        return randomElementKey;
    }
}
