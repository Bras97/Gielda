package klasy;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Fundusz extends Klient {
	private String nazwa=null;
	
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
	{
		
	}

	public synchronized void sprzedajAkcje()
	{
		
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
}
