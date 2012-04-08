package services.arithmatic;

import javax.jws.WebService;

import utls.logging.ServiceLogger;

import com.stefanmuenchow.arithmetic.Arithmetic;


@WebService()
public class SubstractionService implements ArithmaticService
{
    private <T> T divide(T x, T y)
    {
    	Double firstToDouble = Double.valueOf(x.toString());
    	Double secondToDouble = Double.valueOf(y.toString());

		Arithmetic ar = new Arithmetic<Double>(firstToDouble);

		return (T) ar.sub(secondToDouble).value();
    }

    public <T> T calculate(T x, T y)
    {
    	ServiceLogger.getLogger().info("Substracting " +  x + " from " + y);

    	return divide(x, y);
    }
}
