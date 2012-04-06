package org.soa.clients;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.xml.namespace.QName;
import org.soa.math.config.SettingsRepository;
import org.soa.math.dispatch.ExecutionException_Exception;
import org.soa.math.dispatch.InterruptedException_Exception;
import org.soa.math.dispatch.ParseException_Exception;

public class DispatcherClient extends WebServiceClient
{
    protected org.soa.math.dispatch.MathDispatchService service;
    protected org.soa.math.dispatch.MathDispatch port;
    
    private String result;

    public DispatcherClient()
    {
        super(SettingsRepository.getServicesUrlSettings().getProperty("dispatcher_service"));
        service = new org.soa.math.dispatch.MathDispatchService(getEndPointUrl(), getQname());
    }


    protected void openPort()
    {
        port = service.getMathDispatchPort();
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

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public void setEndPoint(URL endPoint)
    {
        this.endPoint = endPoint;
    }

    @Override
    public QName getDefaultQname()
    {
        return new QName("http://dispatch.math.soa.org/", "MathDispatchService");
    }
}
