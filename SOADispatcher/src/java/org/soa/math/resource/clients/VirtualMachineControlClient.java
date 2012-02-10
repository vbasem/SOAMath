package org.soa.math.resource.clients;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;
import org.soa.math.properties.SettingsRepository;
import org.soa.service.registry.RegisteredService;

import org.soa.service.registry.RegistryAndLookUpService;

public class VirtualMachineControlClient extends WebServiceClient
{

    private org.soa.virtualmachine.webservice.VirtualMachineControlsService service;
    private org.soa.virtualmachine.webservice.VirtualMachineControls port;

    public VirtualMachineControlClient()
    {
        setEndPointUrl(SettingsRepository.getServicesUrlSettings().getProperty("vm_control_servic"));
        service = new org.soa.virtualmachine.webservice.VirtualMachineControlsService(getEndPointUrl(), getQname());
    }

    public void startArithmaticService(String operationMode)
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
