/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Basem
 */
public class ExecutionControl extends Observable implements Observer 
{
    
    private static ExecutionControl control = null;
    
    public ExecutionControl getInstance()
    {
        if (control == null)
        {
            control = new ExecutionControl();
        }
        
        return control;
    }
    
    public void startExecuterWithTask(Task task)
    {
        Executer exec = new SoaExecuter(task);
        exec.addObserver(this);
        Thread t = new Thread();
        t.start();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyAll();
    }
    
}
