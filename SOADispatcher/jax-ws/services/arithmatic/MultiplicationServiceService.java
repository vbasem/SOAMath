
package services.arithmatic;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MultiplicationServiceService", targetNamespace = "http://arithmatic.services/", wsdlLocation = "http://192.168.56.101:9002/?wsdl")
public class MultiplicationServiceService
    extends Service
{

    private final static URL MULTIPLICATIONSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException MULTIPLICATIONSERVICESERVICE_EXCEPTION;
    private final static QName MULTIPLICATIONSERVICESERVICE_QNAME = new QName("http://arithmatic.services/", "MultiplicationServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.56.101:9002/?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MULTIPLICATIONSERVICESERVICE_WSDL_LOCATION = url;
        MULTIPLICATIONSERVICESERVICE_EXCEPTION = e;
    }

    public MultiplicationServiceService() {
        super(__getWsdlLocation(), MULTIPLICATIONSERVICESERVICE_QNAME);
    }

    public MultiplicationServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MULTIPLICATIONSERVICESERVICE_QNAME, features);
    }

    public MultiplicationServiceService(URL wsdlLocation) {
        super(wsdlLocation, MULTIPLICATIONSERVICESERVICE_QNAME);
    }

    public MultiplicationServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MULTIPLICATIONSERVICESERVICE_QNAME, features);
    }

    public MultiplicationServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MultiplicationServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MultiplicationService
     */
    @WebEndpoint(name = "MultiplicationServicePort")
    public MultiplicationService getMultiplicationServicePort() {
        return super.getPort(new QName("http://arithmatic.services/", "MultiplicationServicePort"), MultiplicationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MultiplicationService
     */
    @WebEndpoint(name = "MultiplicationServicePort")
    public MultiplicationService getMultiplicationServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://arithmatic.services/", "MultiplicationServicePort"), MultiplicationService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MULTIPLICATIONSERVICESERVICE_EXCEPTION!= null) {
            throw MULTIPLICATIONSERVICESERVICE_EXCEPTION;
        }
        return MULTIPLICATIONSERVICESERVICE_WSDL_LOCATION;
    }

}
