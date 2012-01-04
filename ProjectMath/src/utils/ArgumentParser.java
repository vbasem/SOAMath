package utils;

import services.arithmatic.*;

public class ArgumentParser
{

	private static String baseRegularExpressionToFindArguments = "^-{1,}%s.*";
    private static String REGISTER_SERVER_IDENTIFIER = "registerserver";
    private static String SERVICE_MODE_IDENTIFIER = "servicemode";
    private static String DHCP_SERVER_IDENTIFIER = "dhcp";
    private static String SERVICE_ID_IDENTIFIER = "id";
    private String[] args;

    public ArgumentParser(String[] args)
    {
        this.args = args;
    }

    public String getRegisterServer() throws Exception
    {
        String arg = getArgumentIfPresent(REGISTER_SERVER_IDENTIFIER);

        return arg;
    }

    public String getDhcpserver() throws Exception
    {
    	String dhcpServer = getArgumentIfPresent(DHCP_SERVER_IDENTIFIER);

    	return dhcpServer;
    }

    public ArithmaticService getServiceMode() throws Exception
    {
         String mode = getArgumentIfPresent(SERVICE_MODE_IDENTIFIER);

        return instantiateServiceFromEnum(ArithmaticServiceType.valueOf(mode.toUpperCase()));
    }


    public String getServiceId() throws Exception
    {
    	String identifier = getArgumentIfPresent(SERVICE_ID_IDENTIFIER);

    	return identifier;
    }

    private String getArgumentIfPresent(String argumentIdentifier) throws Exception
    {
        String argumentMatcher = String.format(baseRegularExpressionToFindArguments, argumentIdentifier);

        for (String arg: args)
        {
        	if (arg.toLowerCase().matches(argumentMatcher))
			{
			    return getArgumentValueAfterEqualSign(arg);
			}
		}

		throw new Exception("no argument presetn for " + argumentIdentifier);
    }

    private String getArgumentValueAfterEqualSign(String argument)
    {
        int position_of_first_letter_after_equal_sign = argument.indexOf("=") + 1;

        return argument.substring(position_of_first_letter_after_equal_sign);
    }

    private ArithmaticService instantiateServiceFromEnum(ArithmaticServiceType service_type)
    {
        switch (service_type)
        {
            case ADDITION:
                return new AdditionService();
            default:
                return null;
        }

    }

}
