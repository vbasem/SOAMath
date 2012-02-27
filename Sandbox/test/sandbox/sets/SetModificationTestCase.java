/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Basem
 */
public class SetModificationTestCase
{
    @Test
    public void removeItemFromSet()
    {
        Set<String> set = new HashSet<String>();
        set.add("one");
        set.add("two");
        
        Iterator it = set.iterator();
        String x = (String) it.next();
        it.remove();
        
        assertEquals("one", x);
        assertFalse(set.contains(x));
        
        
               
    }
}
