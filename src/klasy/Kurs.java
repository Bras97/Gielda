package klasy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Kurs
{
	Waluta waluta1;
	Waluta waluta2;
	double cenaKupna;
	double cenaSprzedazy;
	               
    private void writeObject(ObjectOutputStream s) throws IOException {
            s.defaultWriteObject();
        }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();        
        }

	public Kurs(Waluta waluta1, Waluta waluta2, double cenaKupna, double cenaSprzedazy)
	{
		this.waluta1=waluta1;
		this.waluta2=waluta2;
		this.cenaKupna=cenaKupna;
		this.cenaSprzedazy=cenaSprzedazy;		
	}
	
	public void usun(Kurs kurs)
	{
		kurs=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}	
}
