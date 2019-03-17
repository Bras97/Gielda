package klasy;
import java.util.ArrayList;
import java.util.List;

public class RynekSurowcow extends Rynek
{
	List <Surowiec> listaSurowcow = new ArrayList<>();
	
	public RynekSurowcow(String nazwa, List <Surowiec> listaSurowcow)
	{
		super(nazwa);
		this.listaSurowcow=listaSurowcow;
	}
	
	public void usun(RynekSurowcow rynekSurowcow)
	{
		rynekSurowcow=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
}
