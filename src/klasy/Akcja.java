package klasy;

import inwestujacy.Inwestor;
import inwestujacy.Klient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import static klasy.Main.listaInwestorow;

public class Akcja  extends SkladnikAktywow
{
    
	String nazwa;
	double wartosc;
	//Klient ktoKupil;
	int ileKupionychAkcji;
	Spolka ktoWypuscil;
        
private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

    }

  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        ktoWypuscil = (Spolka) s.readObject();
    }
    
        

        
        public Akcja(Spolka sp)
        {
            nazwa=Funkcje.losujString(5);
            
            wartosc= sp.getAktualnyKurs();
        }
	
	public Akcja(String nazwa, double wartosc, int ileKupionychAkcji, Spolka ktoWypuscil)
	{
		this.nazwa = nazwa;
		this.wartosc = wartosc;
		//this.ktoKupil = ktoKupil;
		this.ileKupionychAkcji = ileKupionychAkcji;
		this.ktoWypuscil = ktoWypuscil;
	}	

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public int getIleKupionychAkcji() {
        return ileKupionychAkcji;
    }

    public void setIleKupionychAkcji(int ileKupionychAkcji) {
        this.ileKupionychAkcji = ileKupionychAkcji;
    }

    public Spolka getKtoWypuscil() {
        return ktoWypuscil;
    }

    public void setKtoWypuscil(Spolka ktoWypuscil) {
        this.ktoWypuscil = ktoWypuscil;
    }
	
	public void usun(Akcja akcja)
	{
		akcja=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
}
