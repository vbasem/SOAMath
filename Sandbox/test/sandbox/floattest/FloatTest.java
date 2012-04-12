/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.floattest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Basem
 */
public class FloatTest
{
    @Test
    public void testSomeFloatsWithDetla()
    {
        double x = 1.3;
        double y = 1.28;
        
        assertEquals(x, y, 0.03);
    }
    
    @Test
    public void testLongToDouble()
    {
        double result = 2;
        long input = 2;
        
        System.out.println(numberEater(0));
        assertEquals(result, numberEater("2.0"), 0.001 );
    }
            
    
    private <T> Double numberEater(T a)
    {

        Double x = Double.valueOf(a.toString());    
        return x;
    }
}
