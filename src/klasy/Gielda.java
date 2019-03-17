package klasy;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gielda extends Rynek
{    
    Waluta waluta;
    String miasto;
    String kraj;
    String adresSiedziby;
    List <Indeks> listaIndeksow = new ArrayList<>();
    double marzaProc;

                   
    private void writeObject(ObjectOutputStream s) throws IOException {
            s.defaultWriteObject();
        }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();        
        }
    
    public Waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(Waluta waluta) {
        this.waluta = waluta;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getAdresSiedziby() {
        return adresSiedziby;
    }

    public void setAdresSiedziby(String adresSiedziby) {
        this.adresSiedziby = adresSiedziby;
    }

    public List<Indeks> getListaIndeksow() {
        return listaIndeksow;
    }

    public void setListaIndeksow(List<Indeks> listaIndeksow) {
        this.listaIndeksow = listaIndeksow;
    }

    public double getMarzaProc() {
        return marzaProc;
    }

    public void setMarzaProc(double marzaProc) {
        this.marzaProc = marzaProc;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
	 
        public Gielda() throws IOException
        {
            Random generator = new Random();
            int dl = generator.nextInt(Main.listaWalut.size());
            waluta=Main.listaWalut.get(dl);
            this.nazwa= Funkcje.losuj("src/losowania/gieldyNazwy.txt", 52);
            String wszystko = Funkcje.losuj("src/losowania/gieldy.txt", 99);
            String splited = wszystko;
            String[] splitedArray = null;
            splitedArray = splited.split("	");
            kraj=splitedArray[0];
            miasto=splitedArray[1];
            adresSiedziby= Funkcje.losuj("src/losowania/adresy.txt", 370);
            for(int i=0;i<4; i++)
            {
                Indeks id = new Indeks();
                listaIndeksow.add(id);                        
            }
            marzaProc=generator.nextDouble()*10;
            marzaProc=new BigDecimal(marzaProc).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            
        }
             
         
         
	 public Gielda(String nazwa, Waluta waluta, String miasto, String kraj, String adresSiedziby,
	 List <Indeks> listaIndeksow, double marzaProc)
	 {
		 super(nazwa);
		 this.waluta=waluta;
		 this.miasto=miasto;
		 this.adresSiedziby=adresSiedziby;
		 this.listaIndeksow = listaIndeksow;
		 this.marzaProc=marzaProc;
		 this.kraj=kraj;
	 }
         
         public String wypisz()
         {
             String lancuch="";
             for(int i=0; i<4; i++)
                 lancuch+=listaIndeksow.get(i).getNazwa()+", ";
             return lancuch;
         }
 
	public void usun(Gielda gielda)
	{
		gielda=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
}
