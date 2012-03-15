package utls.logging;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServiceLogger extends Logger
{

	protected static final String LOG_DIRECTORY = "soa_services";
	protected static final String LOG_FILE_PREFIX = "service_";

	protected static ServiceLogger instance = null;

	protected ServiceLogger(String name, String resourceBundleName)
	{
		super(name, resourceBundleName);

		this.addHandler(getFileHandlerWithSimpleFormatter());
	}

	protected Handler getFileHandlerWithSimpleFormatter()
	{
		Handler logHandler = null;
		try {
			logHandler = new FileHandler(getLogFilePath());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			ServiceLogger.getLogger().warning(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ServiceLogger.getLogger().warning(e.toString());
		}

		logHandler.setFormatter(new SimpleFormatter());

		return logHandler;
	}

	public static ServiceLogger getLogger()
	{
		if (instance == null)
		{
			instance = new ServiceLogger(LOG_FILE_PREFIX, null);
		}

		return instance;
	}

	protected String getLogFileDirectory()
	{
		String directoryPath = System.getProperty("java.io.tmpdir") +
				File.separator + LOG_DIRECTORY  + File.separator;
		createLogDirectoryIfDoesntExists(directoryPath);

		return directoryPath;
	}

	protected void createLogDirectoryIfDoesntExists(String path)
	{
		File logDir = new File(path);

		if (!logDir.exists())
		{
			logDir.mkdir();
		}

	}

	public String getLogFilePath()
	{
        DateFormat df = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
        Date d = new Date();

		return getLogFileDirectory() + File.separator + LOG_FILE_PREFIX + df.format(d) + ".txt";
	}

}
