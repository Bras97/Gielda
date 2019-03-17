package klasy;
import inwestujacy.Inwestor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Surowiec extends SkladnikAktywow implements Serializable, Runnable
{
	private String nazwa;
	private String jednostkaHandlowa;
	private Waluta waluta;
	private double aktualnaWartosc=0;
	private double minWartosc=0;
	private double maxWartosc=0;
	public Double[] listaHistoria = new Double[10];
        //public int idx=0;
                
private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();        
    }
    
        
    
        public void run()
        {
            int idx=0;
            Random generator = new Random();
            while(true)
            {
                
            try {
                sleep(500);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Inwestor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(idx<9) idx++;
            for(Surowiec in: Main.listaSurowcow)
            {
                if(generator.nextInt(100)<65)    in.zmianaWartosci(idx);
               // System.out.println("Jest fajnie!");  
            } 
                //kupAkcje();
            //sprzedajAkcje();
            for(Surowiec s: Main.listaSurowcow)
            {
                if (s.listaHistoria[idx]==null) s.listaHistoria[idx]=s.listaHistoria[idx-1];
            }
        
        }
        }
        
	public Surowiec() throws FileNotFoundException, IOException
	{
		String wszystko = Funkcje.losuj("src/losowania/surowce.txt", 47);
		System.out.println(wszystko);
		String splited = wszystko; 
		String[] splitedArray = null;
		splitedArray = splited.split("	");
		nazwa=splitedArray[0];
		jednostkaHandlowa=splitedArray[1];
		aktualnaWartosc=Double.parseDouble(splitedArray[2]);
		minWartosc=new BigDecimal(Double.parseDouble(splitedArray[3])).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		maxWartosc=new BigDecimal(Double.parseDouble(splitedArray[4])).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//waluta=splitedArray[5];
                listaHistoria[0]=aktualnaWartosc;
        }
	
	
	public Surowiec(String nazwa, String jednostkaHandlowa, Waluta waluta, double aktualnaWartosc, double minWartosc, double maxWartosc)
	{
		this.nazwa=nazwa;
		this.jednostkaHandlowa=jednostkaHandlowa;
		this.waluta=waluta;
		this.aktualnaWartosc=aktualnaWartosc;
		this.minWartosc=minWartosc;
		this.maxWartosc=maxWartosc;	
                listaHistoria[0]=aktualnaWartosc;
                //idx++;        
			
	}
	
        public Surowiec szukaj(String nazwa)
        {
            for (Surowiec inw: Main.listaSurowcow)
                if (inw.getNazwa().equals(nazwa)) return inw;
            return null;
        }
                
	public void usun(Surowiec surowiec)
	{
		surowiec=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}

    /**
     * @return the nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * @param nazwa the nazwa to set
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * @return the jednostkaHandlowa
     */
    public String getJednostkaHandlowa() {
        return jednostkaHandlowa;
    }

    /**
     * @param jednostkaHandlowa the jednostkaHandlowa to set
     */
    public void setJednostkaHandlowa(String jednostkaHandlowa) {
        this.jednostkaHandlowa = jednostkaHandlowa;
    }

    /**
     * @return the waluta
     */
    public Waluta getWaluta() {
        return waluta;
    }

    /**
     * @param waluta the waluta to set
     */
    public void setWaluta(Waluta waluta) {
        this.waluta = waluta;
    }

    /**
     * @return the aktualnaWartosc
     */
    public double getAktualnaWartosc() {
        return aktualnaWartosc;
    }

    /**
     * @param aktualnaWartosc the aktualnaWartosc to set
     */
    public void setAktualnaWartosc(double aktualnaWartosc) {
        this.aktualnaWartosc = aktualnaWartosc;
    }

    /**
     * @return the minWartosc
     */
    public double getMinWartosc() {
        return minWartosc;
    }

    /**
     * @param minWartosc the minWartosc to set
     */
    public void setMinWartosc(double minWartosc) {
        this.minWartosc = minWartosc;
    }

    /**
     * @return the maxWartosc
     */
    public double getMaxWartosc() {
        return maxWartosc;
    }

    /**
     * @param maxWartosc the maxWartosc to set
     */
    public void setMaxWartosc(double maxWartosc) {
        this.maxWartosc = maxWartosc;
    }
    
    
    public void przesun() {
        for(int i=1; i<10; i++)
        {
            listaHistoria[i-1]=listaHistoria[i];
            listaHistoria[i]=null;
        }
    }
    
    public void zmianaWartosci(int idx)
    {
        Random generator = new Random();
        int znak=generator.nextInt(2);
        if (znak==0) znak=-1;
        aktualnaWartosc =  new BigDecimal(generator.nextDouble()/4*10+0.875).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        
        if(idx>=9) przesun(); //aktualizacja
        listaHistoria[idx]=aktualnaWartosc; //tworzenie historii
        if (aktualnaWartosc<minWartosc) minWartosc=aktualnaWartosc;
        if (aktualnaWartosc>maxWartosc) maxWartosc=aktualnaWartosc;
    }
}
