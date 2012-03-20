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
public class AdditionWebServiceClient extends ArithmaticWebServiceClient
{

    public AdditionWebServiceClient(String url)
    {
        super(url);
    }

    @Override
    public QName getDefaultQname()
    {
        return new QName("http://arithmatic.services/", "AdditionServiceService");
    }

    protected <T> Object calculate(T x, T y)
    {
        services.arithmatic.AdditionServiceService service =
                new services.arithmatic.AdditionServiceService(getEndPointUrl(), getQname());

        services.arithmatic.AdditionService port = service.getAdditionServicePort();


        Object result = port.calculate(x, y);

        closePort((Closeable) port);

        return result;
    }
}
