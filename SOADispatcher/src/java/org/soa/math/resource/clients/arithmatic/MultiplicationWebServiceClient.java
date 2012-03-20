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
public class MultiplicationWebServiceClient extends ArithmaticWebServiceClient
{

    public MultiplicationWebServiceClient(String url)
    {
        super(url);
    }

    @Override
    public QName getDefaultQname()
    {
        return new QName("http://arithmatic.services/", "MultiplicationServiceService");
    }

    protected <T> Object calculate(T x, T y)
    {
        services.arithmatic.MultiplicationServiceService service =
                new services.arithmatic.MultiplicationServiceService(getEndPointUrl(), getQname());

        services.arithmatic.MultiplicationService port = service.getMultiplicationServicePort();


        Object result = port.calculate(x, y);

        closePort((Closeable) port);

        return result;
    }
}
