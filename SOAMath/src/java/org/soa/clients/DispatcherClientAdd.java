/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.clients;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.soa.math.dispatch.ExecutionException_Exception;
import org.soa.math.dispatch.InterruptedException_Exception;
import org.soa.math.dispatch.ParseException_Exception;

/**
 *
 * @author Basem
 */
public class DispatcherClientAdd extends DispatcherClient
{

    public DispatcherClientAdd(String operandA, String operandB)
    {
        super();
        setResult(add(operandA, operandB));
    }

    public String add(String a, String b)
    {
        openPort();
        String result = null;
        
        try
        {
            result = port.add(a, b);
        } catch (ExecutionException_Exception ex)
        {
            Logger.getLogger(DispatcherClientAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException_Exception ex)
        {
            Logger.getLogger(DispatcherClientAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex)
        {
            Logger.getLogger(DispatcherClientAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        closePort();

        setResult(result);
        return result;
    }
}
