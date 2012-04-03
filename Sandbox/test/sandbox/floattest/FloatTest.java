/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.floattest;

import sandbox.sets.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

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
}
