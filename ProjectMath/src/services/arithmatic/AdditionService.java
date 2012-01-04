package services.arithmatic;

import javax.jws.WebService;


@WebService()
public class AdditionService implements ArithmaticService
{
    private int add(int x, int y)
    {
        return x+y;
    }

    public int calculate(int x, int y)
    {
    	System.out.println(String.format("adding %d + %d", x, y));
        return add(x, y);
    }
}
