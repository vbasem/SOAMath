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
        super();
        setEndPointUrl(SettingsRepository.getServicesUrlSettings().getProperty("vm_control_servic"));
        service = new org.soa.virtualmachine.webservice.VirtualMachineControlsService(getEndPointUrl(), getQname());
    }

    public void startService(String operationMode)
    {
        openPort();
        port.startArithmaticServer(operationMode);
        closePort();
    }
    
    public void stopService(String machineId)
    {
        openPort();
        port.stopServer(machineId);
        closePort();
        
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
