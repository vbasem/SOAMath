/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.soa.virtualmachine.commands.*;
import org.soa.virtualmachine.config.SettingsRepository;
import org.virtualbox_4_1.*;

public class VirtualMachineClient
{

    private boolean keep_alive = false;
    private VirtualBoxManager manager;
    private IVirtualBox box;
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

    protected IVirtualMachineCommand getArithmaticServerProcessCommand(String id, String mode)
    {
        CommandFromConfig com = new CommandFromConfig("java_arithmatic");
        String dhcpIp = box.getDHCPServers().get(0).getIPAddress();
        List<String> arithmaticCommand = com.getCommandArguments();

        arithmaticCommand.add("--dhcp=" + dhcpIp);
        arithmaticCommand.add("--servicemode=" + mode);
        arithmaticCommand.add("--registerserver="
                + SettingsRepository.getServicesUrlSettings().getProperty("registry_service_url"));
        arithmaticCommand.add("--id=" + id);

        Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, arithmaticCommand.toString(), arithmaticCommand);

        return com;
    }

    protected String nameOfNextFreeMachine(String operationPrefix)
    {
        String machineName = null;

        boolean waitForMAchinesToPowerOff = true;

        for (int i = 0; i < 10; i++)
        {
            for (IMachine mach : box.getMachines())
            {
                if (mach.getName().indexOf(operationPrefix) != -1)
                {
                    if (mach.getState() == MachineState.PoweredOff)
                    {
                        machineName = mach.getName();
                        break;
                    }
                }
            }

            sleep(1000);
        }

        return machineName;
    }

    public String startVm(String machineName)
    {
        // wait max 10 sex for machine status to change to powered down before we start it
//        try
//        {
//            waitForStatusChange(manager.getVBox().findMachine(machineName),
//                    MachineState.PoweredOff,
//                    1000,
//                    10000);
//        } catch (VirtualMachineStateError ex)
//        {
//            Logger.getLogger("vmcontrol").severe(ex.getMessage());
//        }

        manager.startVm(machineName, null, 7000);

        return "Attempting to start : " + machineName + "<br />";
    }

    public void shutdown(String machineName)
    {
        resetVm("");
        connectToVBox("");

        try
        {
            CommandFromConfig command = new CommandFromConfig("shutdown");
            ISession session = getMachineSession(machineName);
            Holder<Long> pid = new Holder<Long>();
            IProgress prog = session.getConsole().getGuest().executeProcess(
                    command.getCommand(),
                    new Long(ExecuteProcessFlag.WaitForStdOut.value()),
                    command.getCommandArguments(),
                    null,
                    command.getUser().getUserName(),
                    command.getUser().getUserPassword(),
                    new Long(7000),
                    pid);
            
            int i = 0;
            while (!prog.getCompleted() && i++ < 10)
            {
                sleep(1000);
            }
            session.getConsole().releaseRemote();
            session.unlockMachine();

        } catch (Exception ex)
        {
            Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetVm("");
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
        if (manager != null && box != null)
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
                session = null;
            }
        }

        return "resetting vm <br />";
    }

    protected ISession getMachineSession(String machineName) throws Exception
    {
        ISession session = manager.openMachineSession(box.findMachine(machineName));

        return session;
    }

    public void startProcess(String machineName, IVirtualMachineCommand command)
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
                IProgress prog = session.getConsole().getGuest().executeProcess(
                        command.getCommand(),
                        new Long(1),
                        command.getCommandArguments(),
                        env,
                        command.getUser().getUserName(),
                        command.getUser().getUserPassword(),
                        new Long(7000),
                        pid);
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

        waitForUnlockedSession();

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

    private void waitForUnlockedSession()
    {
        int waitedSoFar = 0;
        SessionState state = null;

        do
        {
            sleep(1000);
            state = manager.getSessionObject().getState();
            waitedSoFar++;
        } while (state != SessionState.Unlocked && waitedSoFar < 10);

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
