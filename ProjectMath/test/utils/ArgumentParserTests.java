
package utils;

import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import services.arithmatic.AdditionService;
import services.arithmatic.ArithmaticServiceType;

public class ArgumentParserTests
{
    @Test
    public void test_parseRegisterServer_givenArgument_returnsRegisterServerString()
    {

        String[] args = {"--registerserver=192.168.0.1"};
        ArgumentParser ap = new ArgumentParser(args);

        try
        {
            assertEquals("192.168.0.1", ap.getRegisterServer());
        }
        catch(Exception e)
        {
            fail("cant get here");
        }
    }

    @Test
    public void test_parseRegisterServer_noArgument_throwsException()
    {
        String[] args = {};
        ArgumentParser ap = new ArgumentParser(args);

        try
        {
            ap.getRegisterServer();
            fail("should not get here");
        }
        catch(Exception e)
        {

        }
    }

    @Test
    public void test_getServiceMode_givenModeArgument_returnsObjectOfThatMode()
    {
        String[] args = {"--servicemode=addition"};
        ArgumentParser ap = new ArgumentParser(args);

        try
        {
            assertEquals(AdditionService.class, ap.getServiceMode().getClass());
        }
        catch(Exception e)
        {
            fail("should not get here");
        }
    }

    @Test
    public void test_getDhcpserver_givenDhcpArgument_returnsDhcpIp()
    {
    	String expectedDhcpIp = "192.168.0.1";
    	String[] args = {"--dhcp=" + expectedDhcpIp};

    	ArgumentParser  ap = new ArgumentParser(args);

    	try
    	{
    		assertEquals(expectedDhcpIp, ap.getDhcpserver());
    	}
    	catch (Exception e)
    	{
    		fail("Should not get here");
    	}
    }

    @Test
    public void test_getServiceIdentifier_givenIdentifier_returnsIdentifierString()
    {
    	String expectedIdentifier = "add1";
    	String[] args = {"--identifier=" + expectedIdentifier};

    	ArgumentParser ap  = new ArgumentParser(args);

    	try {
			assertEquals(expectedIdentifier, ap.getServiceId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("cant get here");
		}
    }

    @Test public void test_getServiceTypeEnumeration_givenIdentifier_returnsEnum()
    {
    	ArithmaticServiceType expectedEnum = ArithmaticServiceType.ADDITION;

    	String[] args = {"--servicemode=" + expectedEnum};

    	ArgumentParser ap  = new ArgumentParser(args);

    	try {
			assertEquals(expectedEnum, ap.getServiceTypeEnumeration());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("cant get here");
		}
    }
}
