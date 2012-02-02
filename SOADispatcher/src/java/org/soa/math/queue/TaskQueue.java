/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.queue;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import org.soa.math.executer.task.Task;
import org.soa.math.request.RequestType;

/**
 *
 * @author Basem
 */
public interface TaskQueue extends Map, Iterable
{
    public Future putToQueueAndGetFeatureObject(Task t);
    public BlockingQueue getQueueForRequestType(RequestType type);
}
