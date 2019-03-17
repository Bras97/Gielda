package klasy;
import java.util.ArrayList;
import java.util.List;

public class Swiat 
{

	List <Gielda> listaGield = new ArrayList<>();
	List <RynekWalut> listaRynkowWalut = new ArrayList<>();
	List <RynekSurowcow> listaRynkowSurowcow = new ArrayList<>();
	
	public Swiat(List <Gielda> listaGield, List <RynekWalut> listaRynkowWalut,
			List <RynekSurowcow>listaRynkowSurowcow)
	{
		this.listaGield=listaGield;
		this.listaRynkowWalut=listaRynkowWalut;
		this.listaRynkowSurowcow=listaRynkowSurowcow;
	}
}
