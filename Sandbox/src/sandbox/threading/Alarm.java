/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox.threading;

/**
 *
 * @author Basem
 */
public class Alarm {

    public static void main(String[] args) throws InterruptedException {
        Sleeper s = new Sleeper();
        

        
        
        Thread t = new Thread(s);
        t.start();
        
                synchronized(t) {
            t.wait();
        }
        
        //System.out.println(s.x);
        Waker w = new Waker(s);
        System.out.println(s.x);
        
    }
}
