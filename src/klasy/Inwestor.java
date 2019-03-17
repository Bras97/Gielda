package klasy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Inwestor extends Klient
{
	String PESEL;
	int budzetInwestycyjny;
	
	public Inwestor() throws FileNotFoundException, IOException
	{
            //File myFile = new File("word.txt");
        //System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());
            //pojawSie();
	}
	
	public Inwestor(String imie, String nazwisko, String PESEL, int budzetInwestycyjny)
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
		
	}

	public synchronized void sprzedajAkcje()
	{
		
	}

	public synchronized void zwiekszBudzet()
	{
		Random generator = new Random();
		budzetInwestycyjny+=generator.nextInt(100)*100;
	}

	public void kupOdFunduszu()
	{
		
	}
	
	public String getPESEL() {
		return PESEL;
	}

	public void setPESEL(String pESEL) {
		PESEL = pESEL;
	}

	public int getBudzetInwestycyjny() {
		return budzetInwestycyjny;
	}

	public void setBudzetInwestycyjny(int budzetInwestycyjny) {
		this.budzetInwestycyjny = budzetInwestycyjny;
	}

	public void pojawSie() throws FileNotFoundException, IOException
	{
		super.pojawSie();
		Random generator = new Random();
		budzetInwestycyjny=(generator.nextInt(995)+5)*100;
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
}
