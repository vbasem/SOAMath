/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.threading;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basem
 */
public class Waker {

    private Thread t;
    private Sleeper s;
    
    public Waker(Sleeper s)
    {
        this.s = s;
        signal();
    }
    
    public void signal()
    {
        System.out.println("sleeping for 4 before waking sleeper up");
        try {
            Thread.sleep(4000);
            System.out.println("attempting to wake sleeper");
            synchronized(s) {
                s.notify();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Waker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Waker(Thread t) {
        this.t = t;
        System.out.println("sleeping for 2 before waking sleeper up");
        try {
            Thread.sleep(2000);
            System.out.println("attempting to wake sleeper");
            awaken();
        } catch (InterruptedException ex) {
            Logger.getLogger(Waker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void awaken() {
        synchronized (t) {
            t.notify();
        }
    }
}
