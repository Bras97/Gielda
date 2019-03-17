package inwestujacy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import klasy.Akcja;
import klasy.Funkcje;
import klasy.Main;
import klasy.Spolka;

public class Klient implements Serializable{

	String imie=null;
	String nazwisko=null;
        List <Akcja> listaAkcji = new ArrayList<>();
	//listaAkcji.add(new Akcja());
	public Klient()
	{
		
	}
	
	public Klient(String imie, String nazwisko)
	{
		this.imie=imie;
		this.nazwisko=nazwisko;
	}
	
	public synchronized void kupAkcje()
	{
            
	}

	public synchronized void sprzedajAkcje()
	{
		
	}

	public synchronized void pojawSie() throws FileNotFoundException, IOException
	{
            imie=Funkcje.losuj("src/losowania/imiona.txt",443);
            if(imie.charAt(imie.length()-1) == 'a') nazwisko=Funkcje.losuj("src/losowania/nazwiskaz.txt",53);
            else nazwisko=Funkcje.losuj("src/losowania/nazwiskam.txt",43);
	}

	public void usun(Klient klient)
	{
		klient=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
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
        

        public List<Akcja> getListaAkcji() {
            return listaAkcji;
        }

        public void setListaAkcji(List<Akcja> listaAkcji) {
            this.listaAkcji = listaAkcji;
        }
}
