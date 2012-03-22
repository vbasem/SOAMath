/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import org.soa.virtualmachine.config.SettingsRepository;
import org.virtualbox_4_1.*;

public class VirtualMachineClient
{

    private static VirtualMachineClient singleton = null;
    private boolean keep_alive = false;
    private VirtualBoxManager manager;
    private IVirtualBox box;

    public static VirtualMachineClient getInstance()
    {
  //      if (VirtualMachineClient.singleton == null)
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
//            sleep(SettingsRepository
//                    .getServicesUrlSettings()
//                    .getNumericProperty("sleep_before_starting_commands_on_remote"));
            startProcess(nextAvailableMachineName,
                    getArithmaticServerProcessCommand(nextAvailableMachineName, mode));
        }
    }

    protected List<String> getArithmaticServerProcessCommand(String id, String mode)
    {
        String dhcpIp = box.getDHCPServers().get(0).getIPAddress();
        List<String> arithmaticCommand = new ArrayList<String>();
        arithmaticCommand.add("-cp");
        arithmaticCommand.add("/home/basemv/SOAMath/ProjectMath/bin/:/home/basemv/SOAMath/ProjectMath/jax-ws/:/home/basemv/SOAMath/ProjectMath/lib/Generic-Arithmetic-1.0.0.jar");
        arithmaticCommand.add("services.server.ArithmaticStandaloneServer");
        arithmaticCommand.add("--dhcp=" + dhcpIp);
        arithmaticCommand.add("--servicemode=" + mode);
        arithmaticCommand.add("--registerserver="
                + SettingsRepository.getServicesUrlSettings().getProperty("registry_service_url"));
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

        return null;
    }

    public String startVm(String machineName)
    {
        // wait max 10 sex for machine status to change to powered down before we start it
        try
        {
            waitForStatusChange(manager.getVBox().findMachine(machineName),
                    MachineState.PoweredOff,
                    1000,
                    10000);
        } catch (VirtualMachineStateError ex)
        {
            Logger.getLogger("vmcontrol").severe(ex.getMessage());
        }
        
        manager.startVm(machineName, null, 7000);

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
            session.getConsole().powerDown();
            session.getConsole().releaseRemote();
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
            try
            {
                box.releaseRemote();
                manager.cleanup();
                manager.disconnect();
            } catch (Exception ex)
            {
                Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, null, ex);
            } finally
            {
                manager = null;
                box = null;
            }
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
            int i = 0;
            while (!session.getConsole().getGuest().getAdditionsStatus(AdditionsRunLevelType.Userland) && i < 60)
            {
                i++;
                sleep(1000);
            }

            if (session.getConsole().getGuest().getAdditionsStatus(AdditionsRunLevelType.Userland))
            {
                IProgress prog = session.getConsole().getGuest().executeProcess("/usr/bin/java", new Long(1), args, env, "basemv", "basemv", new Long(7000), pid);
            }
            
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

        manager.connect(SettingsRepository.getServicesUrlSettings().getProperty("vbox_service_url"), null, null);
        box = manager.getVBox();
    }

    private void waitForStatusChange(IMachine machine,
            MachineState wantedState,
            int sleepMS,
            int maxSleepTime) throws VirtualMachineStateError
    {
        int waitedSoFar = 0;

        while (waitedSoFar < maxSleepTime)
        {
            if (machine.getState() != wantedState)
            {
                sleep(sleepMS);
                waitedSoFar += sleepMS;
            } else
            {
                break;
            }
        }

        if (machine.getState() != wantedState)
        {
            throw new VirtualMachineStateError("the machine " + machine.getName() + " did not reach the state " + wantedState.toString() + " after " + maxSleepTime + " ms;");
        }

    }

    private void sleep(int sleepMS)
    {
        try
        {
            Thread.sleep(sleepMS);

        } catch (InterruptedException ex)
        {
            Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
