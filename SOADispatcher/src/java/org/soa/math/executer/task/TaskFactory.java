/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer.task;

import org.soa.math.executer.task.AdditionTask;
import org.soa.math.executer.task.Task;
import org.soa.math.global.AbstractFactory;

/**
 *
 * @author Basem
 */
public class TaskFactory extends AbstractFactory
{
    public static <T> Task createAdditionTask(T x, T y)
    {
        return new AdditionTask(x, y);
    }
    
    public static <T> Task createMultiplicationTask(T x, T y)
    {
        return null;
    }
}
