package sandbox;

public class Args
{
    ArithmaticServiceType ast;
    
	public static void main(String[] args)
	{
		for(String arg : args)
		{
			System.out.println(arg);
			
			if (arg.matches("^-{1,}registerserver.*"))
			{
			    System.out.println("this arg had register server: " + arg);
			
			}
		}
		
		System.out.println("===========");
		String x = "--ip=23";
	    
	    System.out.println(ArithmaticServiceType.valueOf("addition".toUpperCase()));
		
	}
	

    public enum ArithmaticServiceType
    {
        ADDITION, SUBSTRACTION, MULTIPLICATION, DIVISION;
        
        public static ArithmaticServiceType fromString(String mode)
        {
            String lowerdCaseMode = mode.toLowerCase();
            if (lowerdCaseMode == "addition")
                return ADDITION;
            else if (lowerdCaseMode == "multiplication")
                return MULTIPLICATION;
            else if (lowerdCaseMode == "substraction")
                return SUBSTRACTION;
            else if (lowerdCaseMode == "division")
                return DIVISION;
            else 
                throw new EnumConstantNotPresentException(ArithmaticServiceType.class, "the mode " + mode + " is not defined");
        }
    }

}
