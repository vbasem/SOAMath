/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.dispatch.server;

import org.soa.math.dispatch.MathDispatch;
import org.soa.math.queue.QueueFactory;
import org.soa.math.resource.ResourcesFactory;
import org.soa.math.utils.logging.ServiceLogger;

/**
 *
 * @author Basem
 */
public class DispatcherServer extends StandaloneServer
{

    private String servingUrl = null;

    public static void main(String[] args)
    {
        try
        {
            DispatcherServer server = new DispatcherServer(args);

            QueueFactory.getStaticQueueMonitor().startMonitor();
            ResourcesFactory.getStaticArithmaticResourceMonitor().startMonitor();

            server.startServingForever();
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            ServiceLogger.getLogger().warning(e.toString());
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            ServiceLogger.getLogger().warning(e.toString());
        }
    }

    public DispatcherServer(String[] args) throws Exception
    {
        abortIfNotEnoughArguments(args);
        this.servingUrl = args[0];
    }

    protected void abortIfNotEnoughArguments(String[] args)
    {
        if (args.length < 1)
        {
            ServiceLogger.getLogger().severe("you need to give the server a url to serve");
            System.out.println("you need to give the server a url to serve");
            System.exit(1);
        }
    }

    protected Object getServingObject()
    {
        return new MathDispatch();
    }

    protected String getServingUrl()
    {
        return servingUrl;
    }

    @Override
    protected String getServiceId()
    {
        return "dispatcher";
    }
}
