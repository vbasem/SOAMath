/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.math.resource.clients.arithmatic;

import java.io.Closeable;
import javax.xml.namespace.QName;

/**
 *
 * @author Basem
 */
public class SubstractionWebServiceClient extends ArithmaticWebServiceClient
{

    public SubstractionWebServiceClient(String url)
    {
        super(url);
    }

    @Override
    public QName getDefaultQname()
    {
        return new QName("http://arithmatic.services/", "SubstractionServiceService");
    }

    protected <T> Object calculate(T x, T y)
    {
        services.arithmatic.SubstractionServiceService service =
                new services.arithmatic.SubstractionServiceService(getEndPointUrl(), getQname());

        services.arithmatic.SubstractionService port = service.getSubstractionServicePort();


        Object result = port.calculate(x, y);

        closePort((Closeable) port);

        return result;
    }
}
