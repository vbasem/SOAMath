/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dic;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Basem
 */
public class TestStrings
{
 
    @Test
    public void testingStringOperations()
    {
        String bigText = "arithmatic1 big text";
        String subText = "arithmatic";
        System.out.println(bigText.indexOf(subText));
        //assertTrue(bigText.indexOf(subText) > -1);
    }
}
