package inwestujacy;
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
import klasy.*;
import static klasy.Main.listaInwestorow;


public class Inwestor extends Klient implements Runnable, Serializable
{    
    
    
    int czekaj=0;
	String PESEL;
	double budzetInwestycyjny;
    
private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        //s.writeObject(czekaj);
       // s.writeObject(PESEL);
       // s.writeObject(budzetInwestycyjny);
    }

  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        //czekaj = (int) s.readObject();
       // PESEL = (String) s.readObject();
        //budzetInwestycyjny = (double) s.readObject();
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
                //kupAkcje();
            //sprzedajAkcje();
            if(idx<9) idx++;
            for(Spolka s: Main.listaSpolek)
            {
                if (s.listaHistoria[idx]==null) s.listaHistoria[idx]=s.listaHistoria[idx-1];
            }
        }
        }
	
	public Inwestor() throws FileNotFoundException, IOException
	{
            //File myFile = new File("word.txt");
        //System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());
            pojawSie();
	}
	
	public Inwestor(String imie, String nazwisko, String PESEL, double budzetInwestycyjny)
	{
		super(imie,nazwisko);
		this.PESEL=PESEL;
		this.budzetInwestycyjny=budzetInwestycyjny;
	}


	public void wyswietl()
	{
		System.out.println(imie + " " + nazwisko + " " + PESEL + " " + budzetInwestycyjny);
	}
	
	public synchronized void kupAkcje()
	{
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
            //System.out.println("kupuje: " + this.getImie() + " "  + this.getNazwisko() + " " + odKogo);
            int x=0; //aby się nie zapetlilo
           double koszt=this.budzetInwestycyjny;
            for(int i=0; i<1; i++) //pojedyncza petla aby mozna bylo ja opuscic funkcja break
            {
            if(odKogo<7)
            { //od spolki
                //System.out.println("od spolki");                
                while((this.budzetInwestycyjny-koszt)<=0) //suma nie moze przekraczac budzetu
                {
                    x++;
                    if (generator.nextInt(2) ==0)  podbicie=generator.nextDouble()*10;
                    else podbicie=generator.nextDouble()*(-10);
                     podbicie=new BigDecimal(podbicie).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                     try{
                    ile = generator.nextInt(spolka.getLiczbaAkcji())+1; //ile kupic                              
                     }
                     catch (Exception ex) 
                     {
                        break;
                     }
                    koszt= new BigDecimal(ile*(spolka.getAktualnyKurs()+podbicie)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    //System.out.println("Wylosowałem: " + koszt + "Budzet: " + budzetInwestycyjny);  
                    if(x>50) break;
                }
            
            if (x>50) break;
                //budzet inwestora pomniejszony 
              this.setBudzetInwestycyjny(new BigDecimal(this.budzetInwestycyjny-ile*spolka.getAktualnyKurs()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            
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
             
            //spolka.setIdx(spolka.getIdx()+1);
            } //czekać aby było od kogo kupować
            
            
            else if(czekaj >3)
            { //od inwestora
               // System.out.println("Szukam inwestora");
                dl=Main.listaInwestorow.size();
                boolean powtorz;
                Inwestor inw;
                
                do
                {
                powtorz=false;
                int ktoryInwestor=generator.nextInt(dl); //wybor inwestora
                inw = Main.listaInwestorow.get(ktoryInwestor);          
                    //System.out.println(inw.getImie());
                //for (Akcja a: inw.listaAkcji)                    
                   // System.out.println(a.getNazwa());
                if(inw.listaAkcji.isEmpty()) powtorz=true;
                if (inw.getPESEL().equals(PESEL)) powtorz=true;  
                    
                }
                while(powtorz==true);

                    int ktoraAkcja=0;

                
                while((budzetInwestycyjny-koszt)<=0)
                {
                    if (generator.nextInt(2) ==0)  podbicie=generator.nextDouble()*10; //podbicie
                    else podbicie=generator.nextDouble()*(-10);
                    podbicie=new BigDecimal(podbicie).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    try{
                    ktoraAkcja=generator.nextInt(inw.getListaAkcji().size()); //wybor ktore akcje kupic
                    ile = generator.nextInt(inw.getListaAkcji().get(ktoraAkcja).getIleKupionychAkcji());
                    }
                    catch (Exception ex)
                    {
                        break;
                    }
                    koszt=ile*(inw.getListaAkcji().get(ktoraAkcja).getWartosc()+podbicie);
                }
                inw.getListaAkcji().get(ktoraAkcja).setWartosc(inw.getListaAkcji().get(ktoraAkcja).getWartosc()+podbicie);
                inw.setBudzetInwestycyjny(inw.getBudzetInwestycyjny()+koszt);  
                this.budzetInwestycyjny-=koszt;
                
                //Main.listaInwestorow.get(ktoryInwestor)=inw;
                }
            czekaj++;
            if(this.budzetInwestycyjny<0) this.budzetInwestycyjny=0; //zabezpieczenie
        }
        }
        
	public synchronized void sprzedajAkcje()
        {
            //Sprzedaż realizowana jest poprzez kupno
            /*
                if(!listaAkcji.isEmpty())
                {
                    Random generator = new Random();
                    int ktoreAkcje=generator.nextInt(listaAkcji.size());
                    int kto=generator.nextInt(10);
                    int ile=generator.nextInt(listaAkcji.get(ktoreAkcje).getIleKupionychAkcji())+1;
                    budzetInwestycyjny+=ile*listaAkcji.get(ktoreAkcje).getWartosc();
                    if(kto<3)
                    {                        
                        int doKogo= generator.nextInt(Main.listaFunduszow.size());
                        Main.listaFunduszow.get(doKogo).listaAkcji.add(new Akcja(listaAkcji.get(ktoreAkcje).getNazwa(),
                        listaAkcji.get(ktoreAkcje).getWartosc(),ile,listaAkcji.get(ktoreAkcje).getKtoWypuscil() ));
                        listaAkcji.get(ktoreAkcje).setIleKupionychAkcji(listaAkcji.get(ktoreAkcje).getIleKupionychAkcji()-ile);                  
                    }
                    else
                    {
                        int doKogo= generator.nextInt(Main.listaFunduszow.size());
                        
                    }
                    if(listaAkcji.get(ktoreAkcje).getIleKupionychAkcji()==0) listaAkcji.remove(ktoreAkcje);     
                }*/
	}

	public synchronized void zwiekszBudzet()
	{
		Random generator = new Random();
		budzetInwestycyjny+=generator.nextInt(500)*100;
	}


    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
	
	public String getPESEL() {
		return PESEL;
	}

	public void setPESEL(String pESEL) {
		PESEL = pESEL;
	}

	public double getBudzetInwestycyjny() {
		return budzetInwestycyjny;
	}

	public void setBudzetInwestycyjny(double budzetInwestycyjny) {
		this.budzetInwestycyjny = budzetInwestycyjny;
	}

	public void pojawSie() throws FileNotFoundException, IOException
	{
		super.pojawSie();
		Random generator = new Random();
		budzetInwestycyjny=new BigDecimal((generator.nextInt(995)+5)*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		int x1=generator.nextInt(899999)+100000;
		int x2=generator.nextInt(899999)+100000;
		String xx1= Integer.toString(x1);
		String xx2= Integer.toString(x2);
		PESEL = xx1+xx2;
		//Main.listaInwestorow.add(this);		
	}

	public void usun(Inwestor inwestor)
	{
		inwestor=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}

    public Inwestor szukaj(String nazwa) {
         for (Inwestor inw: Main.listaInwestorow)
                if (inw.getImie().equals(nazwa)) return inw;
            return null;
    }
}
