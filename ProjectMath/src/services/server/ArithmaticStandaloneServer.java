
package services.server;

import java.net.SocketException;

import services.registry.RegistryServiceClient;
import utils.ArgumentParser;
import utils.networking.NetworkIpFinder;
import utls.logging.ServiceLogger;

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
			ServiceLogger.getLogger().warning(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ServiceLogger.getLogger().warning(e.toString());
		}
    }

    public ArithmaticStandaloneServer(String[] args) throws Exception
    {
        abortIfNotEnoughArguments(args);
        parser = new ArgumentParser(args);
        publisher = new RegistryServiceClient(
        		parser.getServiceId(),
        		getCompleteServerUrl(),
        		parser.getServiceTypeEnumeration().toString());

        publisher.publish(parser.getRegisterServer());
    }

    protected void abortIfNotEnoughArguments(String[] args)
    {
        if (args.length < 2)
        {
            ServiceLogger.getLogger().severe("not enough arguments to start server");
            System.exit(1);
        }
    }

    protected Object getServingObject()
    {
        try {
			return parser.getServiceMode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ServiceLogger.getLogger().warning(e.toString());
		}
		return null;
    }

    protected String getServingUrl()
    {
    	NetworkIpFinder ipFinder = new NetworkIpFinder();
    	String servingUrl= null;
		try {
			servingUrl = ipFinder.getIpAssingedByDhcpServer(parser.getDhcpserver());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			ServiceLogger.getLogger().warning(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ServiceLogger.getLogger().warning(e.toString());
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
			ServiceLogger.getLogger().warning(e.toString());
		}

    	return id;
    }

}

