package functional;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestArithmaticServer
{

    // @WebServiceRef(wsdlLocation =
    // "http://192.168.56.1:8080/VirtualSOA/RegistryAndLookUpService?wsdl")
    private services.arithmatic.AdditionServiceService service;

    @Test
    public void performArithmaticOperation_intType()
    {
        int a = 2;
        int b = 3;
        Integer result = 5;
        
        assertEquals(result, add(a, b));
    }
    
    @Test
    public void performArithmaticOperation_doubleType()
    {
        Double a = 2.04;
        Double b = 2.06;
        Double result = 4.1;
        double y = (Double) add(a, b);
        assertEquals(result, (Double) add(a, b));
    }

    protected <T> T add(T x, T y)
    {
        try
        {
            URL endPoint = new URL("http://192.168.56.105:9002/");
            QName gname = new QName("http://arithmatic.services/", "AdditionServiceService");
            service = new services.arithmatic.AdditionServiceService(endPoint, gname);
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(TestArithmaticServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        services.arithmatic.AdditionService port = service.getAdditionServicePort();
        Object additionResult =  port.calculate(x, y);

        try
        {
            ((Closeable) port).close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return (T) additionResult;
    }
}
