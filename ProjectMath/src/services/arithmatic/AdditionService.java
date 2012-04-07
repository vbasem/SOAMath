package services.arithmatic;

import javax.jws.WebService;

import utls.logging.ServiceLogger;

import com.stefanmuenchow.arithmetic.Arithmetic;


@WebService()
public class AdditionService implements ArithmaticService
{
    private <T> T add(T x, T y)
    {
		Arithmetic ar = new Arithmetic<Double>((Double) x);

		return (T) ar.add((Double) y).value();
    }

    public <T> T calculate(T x, T y)
    {
    	ServiceLogger.getLogger().info("adding " +  x + " to " + y);

    	return add(x, y);
    }
}
