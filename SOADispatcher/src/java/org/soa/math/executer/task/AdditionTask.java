/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer.task;

/**
 *
 * @author Basem
 */
public class AdditionTask<T> extends Task
{
    
    public AdditionTask(T paramA, T paramB)
    {
        super();
        this.requestType = requestType.ADDITION;
        this.addOperand(paramA);
        this.addOperand(paramB);
    }
}
