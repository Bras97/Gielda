/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odswiezanie;

import aplikacja.GUIindeks;
import aplikacja.GUIinwestorzy;
import aplikacja.Pulpit;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elater
 */
public class OdswiezanieInd implements Runnable {

    private GUIindeks guiindeks;
     int x=0;

    public OdswiezanieInd(GUIindeks guiindeks) {
        this.guiindeks = guiindeks;
    }
    @Override
    public void run() {
        while(true)
        {
        try {
            sleep(100);
            guiindeks.fillData();
        } catch (InterruptedException ex) {
            Logger.getLogger(OdswiezanieInd.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (IOException ex) {
                Logger.getLogger(OdswiezanieInd.class.getName()).log(Level.SEVERE, null, ex);
            }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
    
    
}
