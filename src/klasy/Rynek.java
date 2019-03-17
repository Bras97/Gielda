package klasy;

public class Rynek 
{
	String nazwa;
	
        public Rynek()
        {
            
        }
        
	public Rynek(String nazwa)
	{
		this.nazwa=nazwa;
	}
	
	public double pobierzMarze()
	{
		return 0;
	}
	
	public void usun(Rynek rynek)
	{
		rynek=null;
	}	
	protected void finalize() throws Throwable
	{
		super.finalize();
	}
}
