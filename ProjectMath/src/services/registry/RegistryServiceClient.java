package services.registry;


import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;

import utls.logging.ServiceLogger;


public class RegistryServiceClient implements Publishable, Runnable
{

	//@WebServiceRef(wsdlLocation="http://192.168.56.1:8080/SOARegistry/RegistryAndLookUpService?wsdl")
	private org.soa.service.registry.RegistryAndLookUpService service;
	private boolean heartBeat = true;
	private String serviceId;
	private String serviceUrl;
	private String serviceType;

	public RegistryServiceClient(String serviceId, String serviceUrl, String serviceType)
	{
		this.serviceId = serviceId;
		this.serviceUrl = serviceUrl + "?wsdl";
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
			ServiceLogger.getLogger().warning(e.toString());
		}

		return endPoint;
	}

	@Override
	public void publish(String registryServer)
	{
		Thread publisher = new Thread(this);
		publisher.start();
	}

	public org.soa.service.registry.RegistryAndLookUpService getService() {
		return service;
	}

	public void setService(org.soa.service.registry.RegistryAndLookUpService service) {
		this.service = service;
	}

	@Override
	public void run() {

		while (heartBeat)
		{
			service = new org.soa.service.registry.RegistryAndLookUpService(getUrl("http://192.168.56.1:8080/SOARegistry/RegistryAndLookUpService"), getQname());
			org.soa.service.registry.RegistryAndLookUp port = service.getRegistryAndLookUpPort();
			port.registerArithmaticService(serviceId, serviceUrl, serviceType);

			try {
				((Closeable) port).close();
				sleepTillNextHeartBeat();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ServiceLogger.getLogger().warning(e.toString());
			}
		}
	}

	private void sleepTillNextHeartBeat()
	{
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
