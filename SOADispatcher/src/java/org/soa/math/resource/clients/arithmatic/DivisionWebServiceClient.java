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
public class DivisionWebServiceClient extends ArithmaticWebServiceClient
{

    public DivisionWebServiceClient(String url)
    {
        super(url);
    }

    @Override
    public QName getDefaultQname()
    {
        return new QName("http://arithmatic.services/", "DivisionServiceService");
    }

    protected <T> Object calculate(T x, T y)
    {
        services.arithmatic.DivisionServiceService service =
                new services.arithmatic.DivisionServiceService(getEndPointUrl(), getQname());

        services.arithmatic.DivisionService port = service.getDivisionServicePort();


        Object result = port.calculate(x, y);

        closePort((Closeable) port);

        return result;
    }
}
