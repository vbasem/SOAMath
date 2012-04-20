
package services.server;

import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;

public abstract class StandaloneServer
{
	private static final String SERVER_PORT = "9002";

    public void startServingForever() throws InterruptedException
    {

        runServerWithUrlAndServingObject();
        loopForever();
    }

    private void loopForever() throws InterruptedException
    {
        while (true)
        {
            Thread.sleep(10000);
        }
    }

    public void startServerFor(Long seconds) throws InterruptedException
    {
        runServerWithUrlAndServingObject();
        Thread.sleep(seconds * 1000);
    }

    protected void runServerWithUrlAndServingObject()
    {
        System.out.println("=========Starting Server=========");

        Endpoint serverEndPoint = Endpoint.create( getServingObject());
        serverEndPoint.setExecutor(Executors.newCachedThreadPool());
        serverEndPoint.publish(getCompleteServerUrl());
    }

    protected String getCompleteServerUrl()
    {
    	String completeServerUrl = "http://" + getServingUrl() + ":" + SERVER_PORT + "/";

    	return completeServerUrl;
    }

    protected abstract String getServingUrl();

    protected abstract Object getServingObject();

    protected abstract String getServiceId();
}
