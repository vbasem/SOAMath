/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.queue;

import java.util.*;
import java.util.concurrent.*;
import javax.el.MethodNotFoundException;
import org.soa.math.executer.task.Task;
import org.soa.math.request.RequestType;

/**
 *
 * @author Basem
 */
public class RequestQueue extends ConcurrentHashMap implements TaskQueue, Iterable
{
    private ExecutorService taskFutureExecuter = Executors.newCachedThreadPool();

    @Override
    public Future putToQueueAndGetFeatureObject(Task t)
    {
        instantiateQueueForTypeIfNotExists(t.getRequestType());
        Future futureResult = createFutureForTask(t);
        addTaskToQueue(t);
        notifyQueueMonitor();
        
        return futureResult;
    }

    @Override
    public Queue getQueueForRequestType(RequestType type)
    {
        return (Queue) get(type);
    }
    
    private void instantiateQueueForTypeIfNotExists(RequestType requestType)
    {
        if (!containsKey(requestType))
        {
            put(requestType, new ConcurrentLinkedQueue());
        }
    }

    private Future createFutureForTask(Task task)
    {
        Future futureResult = taskFutureExecuter.submit(task);

        return futureResult;
    }

    private void addTaskToQueue(Task task)
    {
        Queue queue = (ConcurrentLinkedQueue) get(task.getRequestType());
        queue.add(task);
    }

    @Override
    public Iterator iterator()
    {
        return new Itr(this);
    }

    private void notifyQueueMonitor()
    {
        QueueFactory.getStaticQueueMonitor().startMonitor();
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
            RequestType nextType = (RequestType) keySetIterator.next();
            
            Object task = queue.getQueueForRequestType(nextType).poll();

            return task;
        }

        @Override
        public void remove()
        {
            throw new MethodNotFoundException("no remove for queue iterator, implement it if you want");
        }
        
    }

}

