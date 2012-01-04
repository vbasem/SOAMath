package utils.networking;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class IpSimilarityComparisionTests
{
    @Test
    public void test_compareFromOffset_twoSimilarIpAndZeroOffset_returnsGreaterThanZero()
    {
        String firstIp = "192.168.12.1";
        String secondIp = "192.168.12.2";

        IpSimilarityComparision comparer = new IpSimilarityComparision();
        int similarity = comparer.compareFromOffset(firstIp, secondIp, 0);

        assertEquals(11, similarity);
    }

    @Test
    public void test_compareFromOffset_unsimilarIpsZeroOffset_returnsZero()
    {
        String firstIp = "192.168.12.1";
        String secondIp = "234.168.12.2";

        IpSimilarityComparision comparer = new IpSimilarityComparision();
        int similarity = comparer.compareFromOffset(firstIp, secondIp, 0);

        assertEquals(similarity, 0);
    }

    @Test
    public void test_compareFromOffset_listOfIps_returnsClosestMatch()
    {
        String expectedReturnedIp = "192.168.0.2";
        List<String> ips = new ArrayList<String>();
        ips.add("127.0.0.1");
        ips.add("192.168.1.1");
        ips.add("237.3.0.1");
        ips.add(expectedReturnedIp);
        ips.add("0.0.0.0");

        IpSimilarityComparision comparer = new IpSimilarityComparision();
        String returnedIp = comparer.compareFromOffset("192.168.0.1", ips, 0);

        assertEquals(expectedReturnedIp, returnedIp);
    }
}
