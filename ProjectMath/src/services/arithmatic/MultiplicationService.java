package services.arithmatic;

import javax.jws.WebService;

import utls.logging.ServiceLogger;

import com.stefanmuenchow.arithmetic.Arithmetic;


@WebService()
public class MultiplicationService implements ArithmaticService
{
    private <T> T substract(T x, T y)
    {
    	Double firstToDouble = Double.valueOf(x.toString());
    	Double secondToDouble = Double.valueOf(y.toString());

		Arithmetic ar = new Arithmetic<Double>(firstToDouble);

		return (T) ar.mul(secondToDouble).value();
    }

    public <T> T calculate(T x, T y)
    {
    	ServiceLogger.getLogger().info("multiplying " +  x + " with " + y);

    	return substract(x, y);
    }
}
