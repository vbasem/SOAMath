/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import org.soa.math.executer.task.Task;

/**
 *
 * @author Basem
 */
public class MockExecutor extends TaskExecutor
{
    
    public MockExecutor(Task task)
    {
        super(task);
    }

    @Override
    public void execute()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
