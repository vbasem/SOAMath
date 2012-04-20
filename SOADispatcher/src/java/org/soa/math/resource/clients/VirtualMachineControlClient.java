package org.soa.math.resource.clients;

import java.io.Closeable;
import java.io.IOException;
import javax.xml.namespace.QName;
import org.soa.math.properties.SettingsRepository;

public class VirtualMachineControlClient extends WebServiceClient
{

    private org.soa.virtualmachine.webservice.VirtualMachineControlsService service;
    private org.soa.virtualmachine.webservice.VirtualMachineControls port;

    public VirtualMachineControlClient()
    {
        super(SettingsRepository.getServicesUrlSettings().getProperty("vm_control_service"));
        service = new org.soa.virtualmachine.webservice.VirtualMachineControlsService(getEndPointUrl(), getQname());
    }

    public int startService(String operationMode)
    {
        int availableMachines;
        openPort();
        availableMachines = port.startArithmaticServer(operationMode);
        closePort();
        
        return  availableMachines;
    }
    
    public int stopService(String machineId)
    {
        int availableMachines;
        openPort();
        availableMachines = port.stopServer(machineId);
        closePort();
        
        return availableMachines;
    }
    
    protected void openPort()
    {
        port = service.getVirtualMachineControlsPort();
    }

    protected void closePort()
    {
        try
        {
            ((Closeable) port).close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public QName getDefaultQname()
    {
        return new QName("http://webservice.virtualmachine.soa.org/", "VirtualMachineControlsService");
    }
}
