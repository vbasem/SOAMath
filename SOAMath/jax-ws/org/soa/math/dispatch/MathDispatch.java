
package org.soa.math.dispatch;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MathDispatch", targetNamespace = "http://dispatch.math.soa.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MathDispatch {


    /**
     * 
     * @param operand2
     * @param operand1
     * @return
     *     returns java.lang.String
     * @throws InterruptedException_Exception
     * @throws ParseException_Exception
     * @throws ExecutionException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://dispatch.math.soa.org/", className = "org.soa.math.dispatch.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://dispatch.math.soa.org/", className = "org.soa.math.dispatch.AddResponse")
    @Action(input = "http://dispatch.math.soa.org/MathDispatch/addRequest", output = "http://dispatch.math.soa.org/MathDispatch/addResponse", fault = {
        @FaultAction(className = InterruptedException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/add/Fault/InterruptedException"),
        @FaultAction(className = ExecutionException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/add/Fault/ExecutionException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/add/Fault/ParseException")
    })
    public String add(
        @WebParam(name = "operand1", targetNamespace = "")
        String operand1,
        @WebParam(name = "operand2", targetNamespace = "")
        String operand2)
        throws ExecutionException_Exception, InterruptedException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws InterruptedException_Exception
     * @throws ParseException_Exception
     * @throws ExecutionException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "multiply", targetNamespace = "http://dispatch.math.soa.org/", className = "org.soa.math.dispatch.Multiply")
    @ResponseWrapper(localName = "multiplyResponse", targetNamespace = "http://dispatch.math.soa.org/", className = "org.soa.math.dispatch.MultiplyResponse")
    @Action(input = "http://dispatch.math.soa.org/MathDispatch/multiplyRequest", output = "http://dispatch.math.soa.org/MathDispatch/multiplyResponse", fault = {
        @FaultAction(className = InterruptedException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/multiply/Fault/InterruptedException"),
        @FaultAction(className = ExecutionException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/multiply/Fault/ExecutionException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/multiply/Fault/ParseException")
    })
    public String multiply(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws ExecutionException_Exception, InterruptedException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws InterruptedException_Exception
     * @throws ParseException_Exception
     * @throws ExecutionException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "divide", targetNamespace = "http://dispatch.math.soa.org/", className = "org.soa.math.dispatch.Divide")
    @ResponseWrapper(localName = "divideResponse", targetNamespace = "http://dispatch.math.soa.org/", className = "org.soa.math.dispatch.DivideResponse")
    @Action(input = "http://dispatch.math.soa.org/MathDispatch/divideRequest", output = "http://dispatch.math.soa.org/MathDispatch/divideResponse", fault = {
        @FaultAction(className = InterruptedException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/divide/Fault/InterruptedException"),
        @FaultAction(className = ExecutionException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/divide/Fault/ExecutionException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/divide/Fault/ParseException")
    })
    public String divide(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws ExecutionException_Exception, InterruptedException_Exception, ParseException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws InterruptedException_Exception
     * @throws ParseException_Exception
     * @throws ExecutionException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "substract", targetNamespace = "http://dispatch.math.soa.org/", className = "org.soa.math.dispatch.Substract")
    @ResponseWrapper(localName = "substractResponse", targetNamespace = "http://dispatch.math.soa.org/", className = "org.soa.math.dispatch.SubstractResponse")
    @Action(input = "http://dispatch.math.soa.org/MathDispatch/substractRequest", output = "http://dispatch.math.soa.org/MathDispatch/substractResponse", fault = {
        @FaultAction(className = InterruptedException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/substract/Fault/InterruptedException"),
        @FaultAction(className = ExecutionException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/substract/Fault/ExecutionException"),
        @FaultAction(className = ParseException_Exception.class, value = "http://dispatch.math.soa.org/MathDispatch/substract/Fault/ParseException")
    })
    public String substract(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws ExecutionException_Exception, InterruptedException_Exception, ParseException_Exception
    ;

}