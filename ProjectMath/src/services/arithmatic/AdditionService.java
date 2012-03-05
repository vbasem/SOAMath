package services.arithmatic;

import javax.jws.WebService;
import com.stefanmuenchow.arithmetic.Arithmetic;


@WebService()
public class AdditionService implements ArithmaticService
{
    private <T> T add(T x, T y)
    {
		Arithmetic ar = new Arithmetic<Number>((Number) x);

		return (T) ar.add((Number) y).value();
    }

    public <T> T calculate(T x, T y)
    {
    	System.out.println("adding " +  x + " to " + y);

    	return add(x, y);
    }
}
