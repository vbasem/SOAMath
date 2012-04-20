
package org.soa.virtualmachine.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.soa.virtualmachine.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StopServer_QNAME = new QName("http://webservice.virtualmachine.soa.org/", "stopServer");
    private final static QName _StartArithmaticServer_QNAME = new QName("http://webservice.virtualmachine.soa.org/", "startArithmaticServer");
    private final static QName _StartArithmaticServerResponse_QNAME = new QName("http://webservice.virtualmachine.soa.org/", "startArithmaticServerResponse");
    private final static QName _StopServerResponse_QNAME = new QName("http://webservice.virtualmachine.soa.org/", "stopServerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.soa.virtualmachine.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StopServerResponse }
     * 
     */
    public StopServerResponse createStopServerResponse() {
        return new StopServerResponse();
    }

    /**
     * Create an instance of {@link StartArithmaticServerResponse }
     * 
     */
    public StartArithmaticServerResponse createStartArithmaticServerResponse() {
        return new StartArithmaticServerResponse();
    }

    /**
     * Create an instance of {@link StartArithmaticServer }
     * 
     */
    public StartArithmaticServer createStartArithmaticServer() {
        return new StartArithmaticServer();
    }

    /**
     * Create an instance of {@link StopServer }
     * 
     */
    public StopServer createStopServer() {
        return new StopServer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.virtualmachine.soa.org/", name = "stopServer")
    public JAXBElement<StopServer> createStopServer(StopServer value) {
        return new JAXBElement<StopServer>(_StopServer_QNAME, StopServer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartArithmaticServer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.virtualmachine.soa.org/", name = "startArithmaticServer")
    public JAXBElement<StartArithmaticServer> createStartArithmaticServer(StartArithmaticServer value) {
        return new JAXBElement<StartArithmaticServer>(_StartArithmaticServer_QNAME, StartArithmaticServer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartArithmaticServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.virtualmachine.soa.org/", name = "startArithmaticServerResponse")
    public JAXBElement<StartArithmaticServerResponse> createStartArithmaticServerResponse(StartArithmaticServerResponse value) {
        return new JAXBElement<StartArithmaticServerResponse>(_StartArithmaticServerResponse_QNAME, StartArithmaticServerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.virtualmachine.soa.org/", name = "stopServerResponse")
    public JAXBElement<StopServerResponse> createStopServerResponse(StopServerResponse value) {
        return new JAXBElement<StopServerResponse>(_StopServerResponse_QNAME, StopServerResponse.class, null, value);
    }

}
