/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine.webservice;

import javax.jws.WebService;
import org.soa.virtualmachine.VirtualMachineClient;

/**
 *
 * @author Basem
 */
@WebService()
public class VirtualMachineControls
{
    public void startArithmaticServer(String arithmaticMode)
    {
        VirtualMachineClient.getInstance().startArithmaticServer("arithmatic", arithmaticMode);
    }
    
    public void stopServer(String serverIdentifier)
    {
        VirtualMachineClient.getInstance().stopVm(serverIdentifier);
    }
}
