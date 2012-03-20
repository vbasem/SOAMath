/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients;

import org.soa.math.resource.clients.arithmatic.ArithmaticWebServiceClient;
import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;

/**
 *
 * @author Basem
 */
public abstract class WebServiceClient
{

    protected URL endPoint = null;
    protected QName qname = null;

    public abstract QName getDefaultQname();

    public WebServiceClient(String url)
    {
        this.setEndPointUrl(url);
    }

    public void setQname(QName qname)
    {
        this.qname = qname;
    }

    public QName getQname()
    {
        if (qname == null)
        {
            qname = getDefaultQname();
        }

        return qname;
    }

    public void setEndPointUrl(String url)
    {
        try
        {
            this.endPoint = new URL(url);
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(WebServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public URL getEndPointUrl()
    {
        if (endPoint == null)
        {
            throw new InstantiationError("the url needs to be set before service can be used");
        }

        return endPoint;
    }

    protected void closePort(Closeable port)
    {
        try
        {
            port.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ArithmaticWebServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
