/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.threading;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basem
 */
public class Sleeper implements Callable {

    @Override
    public Object call() throws Exception
    {
        synchronized(this)
        {
            this.wait();
        }
        
        return "hi";
    }
}
