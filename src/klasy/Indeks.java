package klasy;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Indeks 
{
	String nazwa;
	double wynikIndeksu;
	List <Spolka> listaSpolek = new ArrayList<>();
	               
private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();        
    }
    
        public Indeks() throws IOException
        {
            String cos=UUID.randomUUID().toString();
            nazwa=cos.substring(0, 5);
            Random generator = new Random();
            int x= generator.nextInt(10)+1;
            for(int i=0; i<x; i++)
            {
                Spolka sp = new Spolka();
                listaSpolek.add(sp);
                Main.listaSpolek.add(sp);
            }
            wynikIndeksu=obliczWynik();
        }
  	
        public Indeks(String id) throws IOException
        {
            nazwa=id;
            Random generator = new Random();
            int x= generator.nextInt(10)+1;
            for(int i=0; i<x; i++)
            {
                Spolka sp = new Spolka();
                listaSpolek.add(sp);
            }
            for(Spolka sp: listaSpolek)
            wynikIndeksu+=sp.getAktualnyKurs()*sp.getLiczbaAkcji();
        }
              
	public Indeks(String nazwa,	double wynikIndeksu, List <Spolka> listaSpolek)
	{
		this.nazwa=nazwa;
		this.wynikIndeksu=wynikIndeksu;
		this.listaSpolek=listaSpolek;
	}

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getWynikIndeksu() {
        return wynikIndeksu;
    }

    public void setWynikIndeksu(double wynikIndeksu) {
        this.wynikIndeksu = wynikIndeksu;
    }

    public List<Spolka> getListaSpolek() {
        return listaSpolek;
    }

    public void setListaSpolek(List<Spolka> listaSpolek) {
        this.listaSpolek = listaSpolek;
    }
	
    public void dodajListaSpolek(Spolka sp) {
        listaSpolek.add(sp);
    }
        
        
	public void usun(Indeks indeks)
	{
		indeks=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
        
      public double obliczWynik()
      {
            wynikIndeksu=0;
            for(Spolka sp: listaSpolek)
            wynikIndeksu+=sp.getAktualnyKurs()*sp.getLiczbaAkcji();
            return wynikIndeksu;
      }
}
