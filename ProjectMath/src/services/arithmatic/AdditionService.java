package services.arithmatic;

import javax.jws.WebService;

import utls.logging.ServiceLogger;

import com.stefanmuenchow.arithmetic.Arithmetic;


@WebService()
public class AdditionService implements ArithmaticService
{
    private <T> T add(T x, T y)
    {
    	Double firstToDouble = Double.valueOf(x.toString());
    	Double secondToDouble = Double.valueOf(y.toString());

		Arithmetic ar = new Arithmetic<Double>(firstToDouble);

		return (T) ar.add(secondToDouble).value();
    }

    public <T> T calculate(T x, T y)
    {
    	ServiceLogger.getLogger().info("adding " +  x + " to " + y);

    	return add(x, y);
    }
}
