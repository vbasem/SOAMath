/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.queue;

import java.util.Iterator;
import java.util.Set;
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
    public BlockingQueue getQueueForRequestType(RequestType type)
    {
        return (BlockingQueue) get(type);
    }
    
    private void instantiateQueueForTypeIfNotExists(RequestType requestType)
    {
        if (!containsKey(requestType))
        {
            put(requestType, new LinkedBlockingQueue());
        }
    }

    private Future createFutureForTask(Task task)
    {
        Future futureResult = taskFutureExecuter.submit(task);

        return futureResult;
    }

    private void addTaskToQueue(Task task)
    {
        BlockingQueue queue = (BlockingQueue) get(task.getRequestType());
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
        private RequestType nextIterationKey = null;
        
        public Itr(RequestQueue q)
        {
            queue = q;
            mapKeys = q.keySet();
            keySetIterator = mapKeys.iterator();
        }
        
        /**
         * check first if we have more request type queues
         * next check if that queue has any elements.
         * @return 
         */
        @Override
        public boolean hasNext()
        {
            boolean hasNext = false;
            boolean nextQueueAvailable = false;
            
            while(!hasNext)
            {
                nextQueueAvailable = keySetIterator.hasNext();
                
                if (nextQueueAvailable)
                {
                    nextIterationKey = (RequestType) keySetIterator.next();

                    if (queue.getQueueForRequestType(nextIterationKey).size() > 0)
                    {
                        hasNext = true;
                    }
                }
                else
                {
                    break;
                }
            }
            
            return hasNext;
        }

        @Override
        public Object next()
        {
            Object task = queue.getQueueForRequestType(nextIterationKey).poll();

            return task;
        }

        @Override
        public void remove()
        {
            throw new MethodNotFoundException("no remove for queue iterator, implement it if you want");
            //queue.getQueueForRequestType((RequestType) keySetIterator.next()).remove();
        }
        
    }

}

