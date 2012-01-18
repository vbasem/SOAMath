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
public class Sleeper implements Runnable {

    public static void main(String[] args) {
        Sleeper s = new Sleeper();
        Thread t = new Thread(s);
        t.start();

        Waker w = new Waker(s);
    }

    @Override
    public void run() {

        System.out.println("main thread started, going to sleep");
        snooze();
        System.out.println("woken up, resuming");
    }

    public synchronized void snooze() {
        try {
            this.wait();
                //Thread.currentThread().wait();
            }  catch (InterruptedException ex) {
            Logger.getLogger(Sleeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
