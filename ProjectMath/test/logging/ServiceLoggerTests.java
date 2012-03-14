package logging;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import utls.logging.ServiceLogger;

import static org.junit.Assert.*;

public class ServiceLoggerTests {

	@Test
	public void logEvent_fileExists()
	{
	//	System.out.println(System.getProperty("java.io.tmpdir") + "java_services"  + File.separator);
		//System.exit(1);
		ServiceLogger.getLogger().fine("testing");
		File f = new File(ServiceLogger.getLogger().getLogFilePath());

		assertTrue(f.exists());
		assertTrue(f.isFile());

	}

	@Test
	public void logEvent_fileContaintsLoggedText()
	{
		String logPhrase = "test log should have this line";
		ServiceLogger.getLogger().warning(logPhrase);

		try {
			assertFileContaintsPhrase(ServiceLogger.getLogger().getLogFilePath(), logPhrase);
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}

	public void assertFileContaintsPhrase(String fileName, String phrase) throws IOException
	{
		boolean isPassing = false;

		Scanner fileScanner = new Scanner(new File(fileName));


		Pattern pattern = Pattern.compile(phrase);// ,Pattern.CASE_INSENSITIVE);
		Matcher matcher = null;

		while (fileScanner.hasNextLine())
		{
			String line = fileScanner.nextLine();
			matcher = pattern.matcher(line);

			if (matcher.find())
			{
				isPassing = true;
				break;
			}

		}

		assertTrue("file should containt the phrase:" + phrase, isPassing);
	}
}
