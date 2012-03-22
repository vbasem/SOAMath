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
    public void startArithmaticServer(final String arithmaticMode)
    {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run()
            {
                VirtualMachineClient.getInstance().startArithmaticServer("arithmatic", arithmaticMode);
            }
        });
        
        t.start();
    }
    
    public void stopServer(final String serverIdentifier)
    {
                Thread t = new Thread(new Runnable() {

            @Override
            public void run()
            {
               VirtualMachineClient.getInstance().stopVm(serverIdentifier);
            }
        });
        
        t.start();
    }
}
