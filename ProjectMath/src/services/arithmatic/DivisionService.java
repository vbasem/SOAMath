package services.arithmatic;

import javax.jws.WebService;

import utls.logging.ServiceLogger;

import com.stefanmuenchow.arithmetic.Arithmetic;


@WebService()
public class DivisionService implements ArithmaticService
{
    private <T> T multiply(T x, T y)
    {
    	Double firstToDouble = Double.valueOf(x.toString());
    	Double secondToDouble = Double.valueOf(y.toString());

		Arithmetic ar = new Arithmetic<Double>(firstToDouble);

		return (T) ar.div(secondToDouble).value();
    }

    public <T> T calculate(T x, T y)
    {
    	ServiceLogger.getLogger().info("dividing " +  x + " by " + y);

    	return multiply(x, y);
    }
}
