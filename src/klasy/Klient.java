package klasy;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Klient {

	String imie=null;
	String nazwisko=null;
	
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

	public void pojawSie() throws FileNotFoundException, IOException
	{
            imie=Funkcje.losuj("src/losowania/imiona.txt",444);
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
}
