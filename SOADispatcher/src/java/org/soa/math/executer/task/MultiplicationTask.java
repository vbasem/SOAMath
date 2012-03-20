/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer.task;

/**
 *
 * @author Basem
 */
public class MultiplicationTask<T> extends Task
{
    
    public MultiplicationTask(T paramA, T paramB)
    {
        super();
        this.requestType = requestType.MULTIPLICATION;
        this.addOperand(paramA);
        this.addOperand(paramB);
    }
}
