/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import org.virtualbox_4_1.*;

public class VirtualMachineClient extends Thread
{

    private static VirtualMachineClient singleton = null;
    private boolean keep_alive = false;
    public static final String VBOX_HOST = "http://localhost";
    public static final String VBOX_PORT = "18083";
    private VirtualBoxManager manager;
    private IVirtualBox box;

    public static VirtualMachineClient getInstance()
    {
        if (VirtualMachineClient.singleton == null)
        {
            Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.INFO, "client was null, instantiating", singleton);
            singleton = new VirtualMachineClient();
        }

        return singleton;
    }
    private ISession session;

    public void startArithmaticServer(String operationPrefix, String mode)
    {
        resetVm("");
        connectToVBox("");
        String nextAvailableMachineName = nameOfNextFreeMachine(operationPrefix);

        if (nextAvailableMachineName != null)
        {
            startVm(nextAvailableMachineName);
            waitForMachineToBootUp();

            startProcess(nextAvailableMachineName, getArithmaticServerProcessCommand(nextAvailableMachineName, mode));

        }
    }

    protected List<String> getArithmaticServerProcessCommand(String id, String mode)
    {
        String dhcpIp = box.getDHCPServers().get(0).getIPAddress();
        List<String> arithmaticCommand = new ArrayList<String>();
        arithmaticCommand.add("-cp");
        arithmaticCommand.add("/home/basemv/SOAMath/ProjectMath/bin/:/home/basemv/SOAMath/ProjectMath/jax-ws/");
        arithmaticCommand.add("services.server.ArithmaticStandaloneServer");
        arithmaticCommand.add("--dhcp=" + dhcpIp);
        arithmaticCommand.add("--servicemode=" + mode);
        arithmaticCommand.add("--registerserver=" + "192.168.56.1");
        arithmaticCommand.add("--id=" + id);

        Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, arithmaticCommand.toString(), arithmaticCommand);

        return arithmaticCommand;
    }

    protected String nameOfNextFreeMachine(String operationPrefix)
    {
        for (IMachine mach : box.getMachines())
        {
            if (mach.getName().indexOf(operationPrefix) != -1)
            {
                if (mach.getState() == MachineState.PoweredOff)
                {
                    return mach.getName();
                }
            }
        }
//        Set<String> keys = runningMachines.keySet();
//        Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, runningMachines.entrySet().toString(), runningMachines.entrySet());
//        for (String key : keys)
//        {
//            if (runningMachines.get(key) == null)
//            {
//                return key;
//            }
//        }

        return null;
    }

    public String startVm(String machineName)
    {
        try
        {
            manager.startVm(machineName, null, 7000);
        } catch (VBoxException e)
        {
            return "failed to start " + machineName + "<br />The reason was: " + e.getMessage() + "<br />";
        }
        return "Attempting to start : " + machineName + "<br />";
    }

    public String stopVm(String machineName)
    {
        resetVm("");
        connectToVBox("");
        String out = "attempting to stop " + machineName + "<br />";
        try
        {
            ISession session = getMachineSession(machineName);
            //IMachine machiene = session.getMachine();
            session.getConsole().powerDown();
            session.unlockMachine();

        } catch (Exception ex)
        {
            Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetVm("");

        return out;
    }

    public String resetVm(String flag)
    {
        if (manager != null)
        {
            manager.cleanup();;
            manager.disconnect();
            manager = null;
        }

        return "resetting vm <br />";
    }

    protected ISession getMachineSession(String machineName) throws Exception
    {
        ISession session = manager.openMachineSession(box.findMachine(machineName));

        return session;
    }

    public void startProcess(String machineName, List<String> args)
    {
        ISession session = null;

        try
        {
            IMachine machine = box.findMachine(machineName);
            session = manager.openMachineSession(machine);

            Holder<Long> pid = new Holder<Long>();

            List<String> env = new ArrayList<String>();

            IProgress prog = session.getConsole().getGuest().executeProcess("/usr/bin/java", new Long(1), args, env, "basemv", "basemv", new Long(15000), pid);
        } catch (Exception ex)
        {
            Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connectToVBox(String boxName)
    {
        String out = "";

        if (manager != null)
        {
            return;
        }

        manager = VirtualBoxManager.createInstance(null);

        manager.connect(VBOX_HOST + ":" + VBOX_PORT, null, null);
        box = manager.getVBox();
    }

    @Override
    public void run()
    {
        while (keep_alive)
        {
            try
            {
                this.wait();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void waitForMachineToBootUp()
    {
        try
        {
            Thread.sleep(60000);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
