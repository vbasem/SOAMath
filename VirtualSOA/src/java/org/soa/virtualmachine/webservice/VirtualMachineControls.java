/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine.webservice;

import java.util.logging.Logger;
import javax.jws.WebService;
import org.soa.virtualmachine.VirtualMachineClient;

/**
 *
 * @author Basem
 */
@WebService()
public class VirtualMachineControls
{
    private static final String ARITHMETIC_PREFIX = "arithmetic";
    
    public int startArithmaticServer(final String arithmaticMode)
    {

        Logger.getLogger("vmControl").info("starting machine for mode: " + arithmaticMode);
        VirtualMachineClient client = new VirtualMachineClient();
        client.startArithmaticServer(ARITHMETIC_PREFIX, arithmaticMode);

        return client.getNumberOfAvailableMachinesForServiceType(ARITHMETIC_PREFIX);
    }

    public int stopServer(final String serverIdentifier)
    {
        int result = 0;
        Logger.getLogger("vmControl").info("stoping machine: " + serverIdentifier);

        VirtualMachineClient client = new VirtualMachineClient();
        client.shutdown(serverIdentifier);
        
        result = client.getNumberOfAvailableMachinesForServiceTypeFromServerIdentifier(serverIdentifier);
        
        client.resetVm("");
        
        return result;
    }
}
