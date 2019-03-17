package klasy;
import inwestujacy.Fundusz;
import java.util.ArrayList;
import java.util.List;

public class RynekWalut extends Rynek
{
	List <Waluta> listaWalut = new ArrayList<>();
	
	public RynekWalut(String nazwa, List <Waluta> listaWalut)
	{
		super(nazwa);
		this.listaWalut=listaWalut;
	}
	
	public void usun(Fundusz fundusz)
	{
		fundusz=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
}
