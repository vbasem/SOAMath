/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer.task;

/**
 *
 * @author Basem
 */
public class SubstractionTask<T> extends Task
{
    
    public SubstractionTask(T paramA, T paramB)
    {
        super();
        this.requestType = requestType.SUBSTRACTION;
        this.addOperand(paramA);
        this.addOperand(paramB);
    }
}
