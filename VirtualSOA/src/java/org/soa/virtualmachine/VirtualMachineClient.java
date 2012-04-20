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

    public boolean startArithmaticServer(String operationPrefix, String mode)
    {
        boolean result = false;

        connectToVBox("");
        String nextAvailableMachineName = nameOfNextFreeMachine(operationPrefix);

        if (nextAvailableMachineName != null)
        {
            try
            {
                MachineLock.lock(nextAvailableMachineName);
                result = startVm(nextAvailableMachineName);
                startProcess(nextAvailableMachineName,
                        getArithmaticServerProcessCommand(nextAvailableMachineName, mode));
            } finally
            {
                MachineLock.unlock(nextAvailableMachineName);
            }


        }

        return result;
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
                    if (!MachineLock.isLocked(mach.getName()) && mach.getState() == MachineState.PoweredOff)
                    {
                        machineName = mach.getName();
                        break;
                    }
                }
            }

            //sleep(100);
        }
        Logger.getLogger("vmname").warning("found available machine: " + machineName);
        return machineName;
    }

    public boolean startVm(String machineName)
    {
        return manager.startVm(machineName, null, 7000);
    }

    public boolean shutdown(String machineName)
    {
        boolean result = false;
        //resetVm("");
        connectToVBox("");
        // abort if machine is not running
        if (!MachineLock.isLocked(machineName) && box.findMachine(machineName).getState() != MachineState.Running)
        {
            Logger.getLogger("vm").severe("The machine " + machineName + " is not running to be shutdown.");
            return result;
        }
        MachineLock.lock(machineName);
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
                    new Long(10000),
                    pid);

            session.unlockMachine();
            while (!prog.getCompleted())
            {
                sleep(1000);
            }

            waitForStatusChange(box.findMachine(machineName), MachineState.PoweredOff, 1000, 100000);
            MachineLock.unlock(machineName);
            //session.getConsole().releaseRemote();

            result = true;

        } catch (Exception ex)
        {
            Logger.getLogger(VirtualMachineClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
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

            session.unlockMachine();

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

    public int getNumberOfAvailableMachinesForServiceType(String serviceType)
    {
        int availableCount = 0;

        for (IMachine mach : box.getMachines())
        {
            if (mach.getName().indexOf(serviceType) != -1)
            {
                if (mach.getState() == MachineState.PoweredOff)
                {
                    availableCount++;
                }
            }
        }

        return availableCount;
    }

    public int getNumberOfAvailableMachinesForServiceTypeFromServerIdentifier(String serverIdentifier)
    {
        // remove id number to get serviceType
        String serviceType = serverIdentifier.substring(0, serverIdentifier.length() - 1);

        return getNumberOfAvailableMachinesForServiceType(serviceType);
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
