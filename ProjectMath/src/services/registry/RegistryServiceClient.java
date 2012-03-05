package services.registry;


import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;


public class RegistryServiceClient implements Publishable
{

	@WebServiceRef(wsdlLocation="http://192.168.56.1:8080/SOARegistry/RegistryAndLookUpService?wsdl")
	private org.soa.service.registry.RegistryAndLookUpService service;

	private String serviceId;
	private String serviceUrl;
	private String serviceType;

	public RegistryServiceClient(String serviceId, String serviceUrl, String serviceType)
	{
		this.serviceId = serviceId;
		this.serviceUrl = serviceUrl;
		this.serviceType = serviceType;

	}

	protected QName getQname()
	{
		return new QName("http://registry.service.soa.org/", "RegistryAndLookUpService");
	}

	protected URL getUrl(String url)
	{
        URL endPoint = null;
		try {
			endPoint = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return endPoint;
	}

	@Override
	public void publish(String registryServer)
	{
		service = new org.soa.service.registry.RegistryAndLookUpService(getUrl("http://192.168.56.1:8080/SOARegistry/RegistryAndLookUpService"), getQname());
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
