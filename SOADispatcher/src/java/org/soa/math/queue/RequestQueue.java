/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.queue;

import java.util.*;
import java.util.concurrent.*;
import javax.el.MethodNotFoundException;
import org.soa.math.executer.task.Task;
import org.soa.math.properties.Settings;
import org.soa.math.request.RequestType;

/**
 *
 * @author Basem
 */
public class RequestQueue extends HashMap implements TaskQueue, Iterable
{

    private ExecutorService taskFutureExecuter = Executors.newCachedThreadPool();

    @Override
    public Future putToQueueAndGetFeatureObject(Task t)
    {
        instantiateQueueForTypeIfNotExists(t.getRequestType());
        Future futureResult = createFutureForTask(t);
        addTaskToQueue(t);

        return futureResult;
    }

    @Override
    public BlockingQueue getQueueForRequestType(RequestType type)
    {
        return (BlockingQueue) get(type);
    }
    
    private void instantiateQueueForTypeIfNotExists(RequestType requestType)
    {
        if (containsKey(requestType))
        {
            put(requestType, new LinkedBlockingQueue());
        }

    }

    private Future createFutureForTask(Task t)
    {
        Future futureResult = taskFutureExecuter.submit(t);

        return futureResult;
    }

    private void addTaskToQueue(Task t)
    {
        BlockingQueue queue = (BlockingQueue) get(t.getRequestType());
        queue.add(t);
    }

    @Override
    public Iterator iterator()
    {
        return new Itr(this);
    }
    
    private class Itr implements Iterator
    {
        private Set mapKeys;
        private Iterator keySetIterator;
        private RequestQueue queue; 
        
        public Itr(RequestQueue q)
        {
            queue = q;
            mapKeys = q.keySet();
            keySetIterator = mapKeys.iterator();
        }
        
        @Override
        public boolean hasNext()
        {
            return keySetIterator.hasNext();
        }

        @Override
        public Object next()
        {
            return queue.getQueueForRequestType((RequestType) keySetIterator.next()).poll();
        }

        @Override
        public void remove()
        {
            throw new MethodNotFoundException("no remove for queue iterator, implement it if you want");
            //queue.getQueueForRequestType((RequestType) keySetIterator.next()).remove();
        }
        
    }

}

