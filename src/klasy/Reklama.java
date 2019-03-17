/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasy;

import aplikacja.Pulpit;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elater
 */
public class Reklama implements Runnable {

    private Pulpit pulpit;
     int x=0;

    public Reklama(Pulpit pulpit) {
        this.pulpit = pulpit;
    }
    @Override
    public void run() {
        while(true)
        {
        try {
            sleep(8000);
            pulpit.reklama();
        } catch (InterruptedException ex) {
            Logger.getLogger(Reklama.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    
    
}
