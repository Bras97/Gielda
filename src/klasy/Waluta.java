package klasy;
import inwestujacy.Inwestor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Waluta extends SkladnikAktywow implements Runnable
{
	private String nazwa;
	private List <String> listaKrajow = new ArrayList<>();
        private double wartoscKupna;
        private double wartoscSprzedazy;
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
            for(Waluta in: Main.listaWalut)
            {
                if(generator.nextInt(100)<45)    in.zmianaKursu(idx);
               // System.out.println("Jest fajnie!");  
            } 
                //kupAkcje();
            //sprzedajAkcje();
            for(Waluta s: Main.listaWalut)
            {
                if (s.listaHistoria[idx]==null) s.listaHistoria[idx]=s.listaHistoria[idx-1];
            }
        
        }
        }
        
        public Waluta() throws IOException
        {
            losuj();
            listaHistoria[0]=wartoscKupna;
                
        }
        
	public Waluta(String nazwa, List <String> listaKrajow, double wartoscKupna, double wartoscSprzedazy)
	{
		this.nazwa = nazwa; 
		this.listaKrajow = listaKrajow;
                this.wartoscKupna=wartoscKupna;
                this.wartoscSprzedazy=wartoscSprzedazy;
                listaHistoria[0]=wartoscKupna;
                //idx++;
	}
	
        	
        public Waluta szukaj(String nazwa)
        {
            for (Waluta inw: Main.listaWalut)
                if (inw.getNazwa().equals(nazwa)) return inw;
            return null;
        }
        
	public void usun(Waluta waluta)
	{
		waluta=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
        public void losuj() throws IOException
        {
            setNazwa(Funkcje.losuj("src/losowania/waluty.txt", 158));
            Random generator = new Random();
            int x=generator.nextInt(5)+1;
            for(int i=0; i<x; i++)
            {
                getListaKrajow().add(Funkcje.losuj("src/losowania/kraje.txt", 243));
            }
            
            wartoscKupna=((generator.nextInt(1000))*0.001)*4;
            
           // System.out.format("%.3f%n", wartoscSprzedazy);
            wartoscSprzedazy=((wartoscKupna/4*1000 - 1 - (generator.nextInt(20)))*0.001)*4;
            // for(Kraj k: listaKrajow)  System.out.println(k.nazwa);
             wartoscKupna=new BigDecimal(wartoscKupna).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
             wartoscSprzedazy=new BigDecimal(wartoscSprzedazy).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            
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
     * @return the listaKrajow
     */
    public List <String> getListaKrajow() {
        return listaKrajow;
    }

    /**
     * @param listaKrajow the listaKrajow to set
     */
    public void setListaKrajow(List <String> listaKrajow) {
        this.listaKrajow = listaKrajow;
    }

    /**
     * @return the wartoscKupna
     */
    public double getWartoscKupna() {
        return wartoscKupna;
    }

    /**
     * @param wartoscKupna the wartoscKupna to set
     */
    public void setWartoscKupna(double wartoscKupna) {
        this.wartoscKupna = wartoscKupna;
    }

    /**
     * @return the wartoscSprzedazy
     */
    public double getWartoscSprzedazy() {
        return wartoscSprzedazy;
    }

    public Double[] getListaHistoria() {
        return listaHistoria;
    }

    public void setListaHistoria(Double[] listaHistoria) {
        this.listaHistoria = listaHistoria;
    }

  

    /**
     * @param wartoscSprzedazy the wartoscSprzedazy to set
     */
    public void setWartoscSprzedazy(double wartoscSprzedazy) {
        this.wartoscSprzedazy = wartoscSprzedazy;
    }
    
    public void przesun() {
        for(int i=1; i<10; i++)
        {
            listaHistoria[i-1]=listaHistoria[i];
            listaHistoria[i]=null;
        }
    }
       
    public void zmianaKursu(int idx)
    {
        Random generator = new Random();
        int znak=generator.nextInt(2);
        if (znak==0) znak=-1;
        double x =  new BigDecimal(generator.nextDouble()/30*znak).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        wartoscKupna+=x;
        wartoscSprzedazy+=x;
        listaHistoria[idx]=wartoscKupna;
        
            if(idx>=9) przesun(); //aktualizacja
            listaHistoria[idx]=wartoscKupna; //tworzenie historii
        
    }
}
