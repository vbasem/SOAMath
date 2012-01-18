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
    
    private Sleeper t;
    
    public Waker(Sleeper t) {
        this.t = t;
        awaken();
        System.out.println("sleeping for 2 before waking sleeper up");
        try {
            Thread.sleep(2000);
            System.out.println("attempting to wake sleeper");
            awaken();
        } catch (InterruptedException ex) {
            Logger.getLogger(Waker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized void awaken() {
        
        t.notify();
        
    }
}
