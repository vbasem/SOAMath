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

    public void startArithmaticServer(final String arithmaticMode)
    {
        Logger.getLogger("vmControl").info("starting machine for mode: " + arithmaticMode);
        Thread t = new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                VirtualMachineClient client = new VirtualMachineClient();
                client.startArithmaticServer("arithmatic", arithmaticMode);
            }
        });
        
        t.start();
    }
    
    public void stopServer(final String serverIdentifier)
    {
        Logger.getLogger("vmControl").info("stoping machine: " + serverIdentifier);
        Thread t = new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                VirtualMachineClient client = new VirtualMachineClient();
                client.shutdown(serverIdentifier);
            }
        });
        
        t.start();
    }
}
