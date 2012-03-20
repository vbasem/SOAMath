package services.arithmatic;

import javax.jws.WebService;

import utls.logging.ServiceLogger;

import com.stefanmuenchow.arithmetic.Arithmetic;


@WebService()
public class SubstractionService implements ArithmaticService
{
    private <T> T divide(T x, T y)
    {
		Arithmetic ar = new Arithmetic<Number>((Number) x);

		return (T) ar.sub((Number) y).value();
    }

    public <T> T calculate(T x, T y)
    {
    	ServiceLogger.getLogger().info("Substracting " +  x + " from " + y);

    	return divide(x, y);
    }
}
