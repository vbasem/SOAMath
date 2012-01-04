package utils.networking;

import java.net.SocketException;

import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class NetworkIpFinderTests
{
    @Test
    public void test_getIpAssingedByDhcpServer_DhcpServerIp_ReturnsIpAssignedByDhcpServer()
    {
        String expectedAssignedIp = "192.168.56.101";
        String dhcpServerIp = "192.168.1.101";
        NetworkIpFinder nif  = new NetworkIpFinder();
        String assignedIp;
		try {
			assignedIp = nif.getIpAssingedByDhcpServer(dhcpServerIp);
			assertEquals(expectedAssignedIp, assignedIp);
		} catch (SocketException e) {

			fail("cant get here");
		}


    }
}
