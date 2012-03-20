/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.executer.task;

/**
 *
 * @author Basem
 */
public class DivisionTask<T> extends Task
{
    
    public DivisionTask(T paramA, T paramB)
    {
        super();
        this.requestType = requestType.DIVISION;
        this.addOperand(paramA);
        this.addOperand(paramB);
    }
}
