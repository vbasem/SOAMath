
package services.server;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import services.registry.RegistryServiceClient;
import utils.ArgumentParser;
import utils.networking.NetworkIpFinder;

import org.soa.service.registry.ServiceType;

public class ArithmaticStandaloneServer extends StandaloneServer
{
    private ArgumentParser parser;
    private RegistryServiceClient publisher;

    public static void main(String[] args)
    {
        try {
        	ArithmaticStandaloneServer server = new ArithmaticStandaloneServer(args);
			server.startServingForever();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public ArithmaticStandaloneServer(String[] args) throws Exception
    {
        abortIfNotEnoughArguments(args);
        parser = new ArgumentParser(args);
        publisher = new RegistryServiceClient(
        		parser.getServiceId(),
        		getServingUrl(),
        		parser.getServiceTypeEnumeration().toString());
        publisher.publish();
    }

    protected void abortIfNotEnoughArguments(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("not enough arguments to start server");
            System.exit(1);
        }
    }

    protected Object getServingObject()
    {
        try {
			return parser.getServiceMode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    protected String getServingUrl()
    {
    	NetworkIpFinder ipFinder = new NetworkIpFinder();
    	String servingUrl= null;
		try {
			servingUrl = "http://";
			servingUrl += ipFinder.getIpAssingedByDhcpServer(parser.getDhcpserver());			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return servingUrl;
    }

    @Override
    protected String getServiceId()
    {
    	String id = null;

    	try {
			id = parser.getServiceId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return id;
    }

}

