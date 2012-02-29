package org.soa.math.resource.clients;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import org.soa.math.properties.SettingsRepository;
import org.soa.service.registry.RegisteredService;


public class RegistryServiceClient extends WebServiceClient
{

    private org.soa.service.registry.RegistryAndLookUpService service;
    private org.soa.service.registry.RegistryAndLookUp port;

    public void setEndPoint(URL endPoint)
    {
        this.endPoint = endPoint;
    }

    public RegistryServiceClient()
    {
        setEndPointUrl(SettingsRepository.getServicesUrlSettings().getProperty("registry_service"));
        service = new org.soa.service.registry.RegistryAndLookUpService(getEndPointUrl(), getQname());
    }

    public List<RegisteredService> getAllRegisteredServices()
    {
        openPort();
        List<RegisteredService> services =  port.listAllRegisteredServices();
        closePort();
        
        return services;
    }

    public void unregisterService(String serviceId)
    {
        openPort();
        port.unregisterService(serviceId);
        closePort();
    }
    
    public List<RegisteredService> getAllRegisteredServicesOfType(String type)
    {
        openPort();
        List<RegisteredService> services = port.getAllServicesOfType(type);
        closePort();
        
        return services;
    }
    
    protected void openPort()
    {
        port = service.getRegistryAndLookUpPort();
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
        return new QName("http://registry.service.soa.org/", "RegistryAndLookUpService");
    }

}
