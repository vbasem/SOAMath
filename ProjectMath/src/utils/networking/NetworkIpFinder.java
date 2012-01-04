
package utils.networking;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetworkIpFinder
{
	private IpSimilarityComparision ipComparer;

	public NetworkIpFinder()
	{
		ipComparer = new IpSimilarityComparision();
	}

    public String getIpAssingedByDhcpServer(String server) throws SocketException
    {
    	return ipComparer.compareFromOffset(server, getNetworkInterfacesIps(), 0);
    }

    protected List getNetworkInterfacesIps() throws SocketException
    {
        List<String> ipList= new ArrayList<String>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements())
        {
        	ipList.addAll(readIpsFromInterface(interfaces.nextElement()));
        }

        return ipList;
    }

    private List readIpsFromInterface(NetworkInterface ifc) throws SocketException
    {
    	List<String> ips = new ArrayList<String>();

        if (ifc.isUp())
        {
        	List<InterfaceAddress> addresses = ifc.getInterfaceAddresses();

        	for (InterfaceAddress addr : addresses)
        	{
        		ips.add(addr.getAddress().getHostAddress());

        	}
        }

        return ips;
    }
}
