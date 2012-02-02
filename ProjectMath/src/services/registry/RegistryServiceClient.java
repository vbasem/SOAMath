package services.registry;


import java.io.Closeable;
import java.io.IOException;
import javax.xml.ws.WebServiceRef;

import org.soa.service.registry.RegistryAndLookUpService;
import org.soa.service.registry.ServiceType;


public class RegistryServiceClient implements Publishable
{

	@WebServiceRef(wsdlLocation="http://192.168.56.1:8080/SOARegistry/RegistryAndLookUpService?wsdl")
	private org.soa.service.registry.RegistryAndLookUpService service;

	private String serviceId;
	private String serviceUrl;
	private String serviceType;

	public RegistryServiceClient(String serviceId, String serviceUrl, String serviceType)
	{
		service = new RegistryAndLookUpService();
		this.serviceId = serviceId;
		this.serviceUrl = serviceUrl;
		this.serviceType = serviceType;

	}

	@Override
	public void publish()
	{

		org.soa.service.registry.RegistryAndLookUp port = service.getRegistryAndLookUpPort();
		port.registerArithmaticService(serviceId, serviceUrl, serviceType);

		try {
			((Closeable) port).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public org.soa.service.registry.RegistryAndLookUpService getService() {
		return service;
	}

	public void setService(org.soa.service.registry.RegistryAndLookUpService service) {
		this.service = service;
	}
}
