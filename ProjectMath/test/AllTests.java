import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import utils.networking.*;
import utils.ArgumentParserTests;

@RunWith(Suite.class)
@SuiteClasses({ NetworkIpFinderTests.class, IpSimilarityComparisionTests.class, ArgumentParserTests.class })
public class AllTests {

}
