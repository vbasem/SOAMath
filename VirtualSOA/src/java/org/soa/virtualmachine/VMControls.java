/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.virtualmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.virtualbox_4_1.Holder;
import org.virtualbox_4_1.IMachine;
import org.virtualbox_4_1.IProgress;
import org.virtualbox_4_1.ISession;
import org.virtualbox_4_1.IVirtualBox;
import org.virtualbox_4_1.ProcessOutputFlag;
import org.virtualbox_4_1.VBoxException;
import org.virtualbox_4_1.VirtualBoxManager;

public class VMControls extends Thread
{

    private static VMControls singleton = null;
    private boolean keep_alive = false;
    public static final String VBOX_HOST = "http://localhost";
    public static final String VBOX_PORT = "18083";
    private VirtualBoxManager manager;
    private IVirtualBox box;
    private Map runningMachines = new HashMap();
    private Map runningSessions = new HashMap<String, ISession>();

    public static VMControls getInstance()
    {
        if (VMControls.singleton == null)
        {
            singleton = new VMControls();
        }

        return singleton;
    }
    private ISession session;

    public String startVm(String machineName)
    {
        try
        {
            manager.startVm(machineName, null, 7000);
        } catch (VBoxException e)
        {
            return "failed to start " + machineName + "<br />The reason was: " + e.getMessage() + "<br />";
        }

        runningMachines.put(machineName, true);
        return "Attempting to start : " + machineName + "<br />";
    }

    public String stopVm(String machineName)
    {
        String out = "attempting to stop " + machineName + "<br />";
        try
        {
            ISession session = getMachineSession(machineName);
            //IMachine machiene = session.getMachine();
            session.getConsole().powerDown();
            session.unlockMachine();

        } catch (Exception ex)
        {
            Logger.getLogger(VMControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public String resetVm(String flag)
    {
        manager.cleanup();;
        manager.disconnect();
        manager = null;

        return "resetting vm <br />";
    }

    protected ISession getMachineSession(String machineName) throws Exception
    {
        ISession session = manager.openMachineSession(box.findMachine(machineName));

        return session;
    }

    public String startProcess(String processName)
    {
        ISession session = null;
        String out = "";
        try
        {
            IMachine machine = box.findMachine("small_ubuntu");
            session = manager.openMachineSession(machine);
            String ip = box.getDHCPServers().get(0).getIPAddress();
            
            out += ip + " = dhcp ip <br />";
            Holder<Long> pid = new Holder<Long>();
            out += session.getConsole().getGuest().getAdditionsRunLevel() + " = value for run lvl<br />";
            List<String> args = new ArrayList<String>();
            args.add("-cp");
            args.add("/home/basemv/test/build/");
            args.add("Ahoy");
            //args.add("/home/basemv/pinger.py");
            //args.add("-classpath /home/basemv/uniWorkspace/ProjectMath/bin/ services.server.ArithmaticStandaloneServer --dhcp=192.168.0.1 --servicemode=addition --registerserver=192.168.56.1 --id=add1");
 
            
            
            List<String> env = new ArrayList<String>();
            
            IProgress prog = session.getConsole().getGuest().executeProcess("/usr/bin/java", new Long(1), args, env, "basemv", "basemv", new Long(15000), pid);
            out += pid.value + "  = value for pid<br />" ;
            out += prog.getDescription() + " = value for get descr <br />" ;
            out += prog.getOperationDescription() + " = value for get opr desc<br />" ; 
            out += prog.getCompleted() + " = value for get completed<br />" ;
            out += prog.getCanceled()+ "  = value for is canceled<br />" ;
           
            int i = 0;
            while (!prog.getCompleted() && i < 20) {
                i++;
                out += prog.getOperationPercent() + " = value for get operation precent at " + i + "<br />" ;;
                sleep(1000);
            }
            out += prog.getCompleted() + " = value for get completed<br />" ;
            out += prog.getResultCode() + " = value for get result code<br />" ;
            out += prog.getErrorInfo() + " = value for error info<br />" ;
            out += session.getConsole().getGuest().getProcessOutput(pid.value, new Long(ProcessOutputFlag.None.value()), new Long(10000), new Long(64)).toString() + " = output of process <br />";
            session.releaseRemote();;
        } catch (Exception ex)
        {
            Logger.getLogger(VMControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out + "<br />";
    }

    public String connectToVBox(String boxName)
    {
        String out = "";

        if (manager != null)
        {
            return "Already connected, ignoring establishing connection phase <br />";
        }
        out += "Establishing connection<br />";

        manager = VirtualBoxManager.createInstance(null);

        manager.connect(VBOX_HOST + ":" + VBOX_PORT, null, null);
        box = manager.getVBox();

        if (box == null)
        {
            out += "failed to started box<br />";
        } else
        {
            out += "vbox connected<br />";
        }

        return out;
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
                Logger.getLogger(VMControls.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

}
