package inwestujacy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.math.BigDecimal;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import klasy.Akcja;
import klasy.Funkcje;
import klasy.Main;
import klasy.Spolka;

public class Fundusz extends Klient {
	private String nazwa=null;
	    
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
            for(Inwestor in: Main.listaInwestorow)
            {
                if(generator.nextInt(100)<25)    in.kupAkcje();
               // System.out.println("Jest fajnie!");  
               
                if(generator.nextInt(100)<30)
                {
                   in.zwiekszBudzet();
                }
             }          
               if(generator.nextInt(100)<7)
               {
                   int x=generator.nextInt(4);
                   for(int i=0; i<x; i++)
                   {
                       try {
                           Inwestor inw=new Inwestor();
                           Main.listaInwestorow.add(inw);
                       } catch (IOException ex) {
                           Logger.getLogger(Inwestor.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
               }
            if (idx<9) idx++;
            for(Spolka s: Main.listaSpolek)
            {
                if (s.listaHistoria[idx]==null) s.listaHistoria[idx]=s.listaHistoria[idx-1];
            }
        }
        }
	public Fundusz() throws FileNotFoundException, IOException
	{
		pojawSie();
	}
	
	public Fundusz(String imie, String nazwisko, String nazwa)
	{
		super(imie,nazwisko);
		this.nazwa=nazwa;
	}

	public void wyswietl()
	{
		System.out.println(imie + " " + nazwisko + " " + getNazwa());
	}
	
	public synchronized void kupAkcje()
	{/*
		int dl=Main.listaSpolek.size();
                Random generator = new Random();
                int ktora=generator.nextInt(dl)+1;
                 int ile = generator.nextInt(Main.listaSpolek.get(ktora).getLiczbaAkcji())+1;                    
                
                int ile2=Main.listaSpolek.get(ktora).getLiczbaAkcji()-ile;
                Main.listaSpolek.get(ktora).setLiczbaAkcji(ile2);
                Main.listaSpolek.get(ktora).setKapitalWlasny(Main.listaSpolek.get(ktora).getKapitalWlasny()+ile*Main.listaSpolek.get(ktora).getAktualnyKurs());
                Main.listaSpolek.get(ktora).setKapitalZakladowy(Main.listaSpolek.get(ktora).getKapitalZakladowy()+ile*Main.listaSpolek.get(ktora).getAktualnyKurs());
                Main.listaSpolek.get(ktora).setObroty(Main.listaSpolek.get(ktora).getObroty()+ile);*/
            Random generator = new Random();

            int dl=Main.listaSpolek.size();
            int ktoraSpolka=generator.nextInt(dl); //wybor spolki
            int ile=0;
            boolean unikat=true; //czy akcja jest na liscie akcji
            String nazwaAkcji="";
            nazwaAkcji=Funkcje.losujString(6); 
            double podbicie = 0; //+ do nowego kursu
            int odKogo = generator.nextInt(10); // spolka/inwestor
            Spolka spolka = Main.listaSpolek.get(ktoraSpolka);
            System.out.println("kupuje: " + this.getImie() + " "  + this.getNazwisko() + " " + odKogo);
            int x=0; //aby się nie zapetlilo
                    
                    if (generator.nextInt(2) ==0)  podbicie=generator.nextDouble()*10;
                    else podbicie=generator.nextDouble()*(-10);
                     podbicie=new BigDecimal(podbicie).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    ile = generator.nextInt(spolka.getLiczbaAkcji())+1; //ile kupic     
                    //double koszt= new BigDecimal(ile*(spolka.getAktualnyKurs()+podbicie)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    //System.out.println("Wylosowałem: " + koszt + "Budzet: " + budzetInwestycyjny);  
                    
                
            spolka.setAktualnyKurs(spolka.getAktualnyKurs()+podbicie); //nowy kurs
            if (spolka.getMaxKurs() < spolka.getAktualnyKurs()) spolka.setMaxKurs(spolka.getAktualnyKurs());
            if (spolka.getMinKurs() > spolka.getAktualnyKurs()) spolka.setMinKurs(spolka.getAktualnyKurs());

            spolka.setLiczbaAkcji(spolka.getLiczbaAkcji()-ile); //pomniejszona l akcji
            spolka.setWolumen(spolka.getWolumen()+ile); //zwiekszony wolumen
            spolka.setKapitalWlasny(spolka.getKapitalWlasny()+ile*spolka.getAktualnyKurs()); //zwiekszony kapital wlasny
            spolka.setKapitalZakladowy(spolka.getKapitalZakladowy()+ile*spolka.getAktualnyKurs()); //zwiekszony kapital zakladowy
                //.out.println(this.budzetInwestycyjny+ " " + (this.budzetInwestycyjny-ile*spolka.getAktualnyKurs()));
                
          
                              
                   
              
                 
           // System.out.println(this.budzetInwestycyjny);
            spolka.setObroty(spolka.getObroty()+ile*(spolka.getAktualnyKurs())); // obroty zwiekszone
            double obrot = new BigDecimal(spolka.getObroty()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            spolka.setObroty(obrot);
            for(Akcja a: listaAkcji) //dodanie akcji na liste
                if (a.getNazwa().equals(nazwaAkcji)) 
                { 
                    unikat=false;
                    a.setIleKupionychAkcji(a.getIleKupionychAkcji()+ile); //zmiana liczby
                    a.setWartosc(a.getWartosc()+podbicie); //zmiana wartosci akcji
                }
            if (unikat==true)
            listaAkcji.add(new Akcja(nazwaAkcji, spolka.getAktualnyKurs(),ile, spolka)); //stworz nowa
            
            if(spolka.getIdx()>=9) spolka.przesun(); //aktualizacja
            else
            {            
            spolka.setIdx(spolka.getIdx()+1);
            }
            spolka.listaHistoria[spolka.getIdx()]=spolka.getAktualnyKurs()+podbicie; //tworzenie historii
            //spolka.setIdx(spolka.getIdx()+1);
            } 
            

		
	
	public void pojawSie() throws FileNotFoundException, IOException
	{
		super.pojawSie();
		nazwa=Funkcje.losuj("src/losowania/fundusze.txt",283);
	}

	public void usun(Fundusz fundusz)
	{
		fundusz=null;
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
    
        public Fundusz szukaj(String nazwa) {
         for (Fundusz inw: Main.listaFunduszow)
                if (inw.getImie().equals(nazwa)) return inw;
            return null;
    }
}
