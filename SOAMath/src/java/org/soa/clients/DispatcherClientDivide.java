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
public class DispatcherClientDivide extends DispatcherClient
{

    public DispatcherClientDivide(String operandA, String operandB)
    {
        super();
        setResult(divide(operandA, operandB));
    }

    public String divide(String a, String b)
    {
        openPort();
        String result = null;
        try
        {
            result = port.divide(a, b);
        } catch (ExecutionException_Exception ex)
        {
            Logger.getLogger(DispatcherClientDivide.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException_Exception ex)
        {
            Logger.getLogger(DispatcherClientDivide.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex)
        {
            Logger.getLogger(DispatcherClientDivide.class.getName()).log(Level.SEVERE, null, ex);
        }
        closePort();

        setResult(result);
        return result;
    }
}
