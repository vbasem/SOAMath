package services.arithmatic;

import javax.jws.WebService;

import utls.logging.ServiceLogger;

import com.stefanmuenchow.arithmetic.Arithmetic;


@WebService()
public class DivisionService implements ArithmaticService
{
    private <T> T multiply(T x, T y)
    {
		Arithmetic ar = new Arithmetic<Number>((Number) x);

		return (T) ar.mul((Number) y).value();
    }

    public <T> T calculate(T x, T y)
    {
    	ServiceLogger.getLogger().info("multiplying " +  x + " with " + y);

    	return multiply(x, y);
    }
}
