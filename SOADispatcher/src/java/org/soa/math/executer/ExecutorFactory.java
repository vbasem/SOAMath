/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import org.soa.math.executer.task.Task;
import org.soa.math.global.AbstractFactory;
import org.soa.math.queue.QueueFactory;

/**
 *
 * @author Basem
 */
public class ExecutorFactory extends AbstractFactory
{

    private static final ExecutionControl control = new ExecutionControl();

    public static ExecutionControl getStaticExecutionControl()
    {
        if (!isTestMode())
        {
            return control;
        } else
        {
            return null;
        }
    }
    
    public static TaskExecutor getThreadedTaskExecutor(Task t)
    {
        return (TaskExecutor) decideWhichObjectBasedOnEnv(new ThreadedTaskExecutor(t), null);
    }
}
