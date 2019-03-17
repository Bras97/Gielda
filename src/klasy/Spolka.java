package klasy;
import inwestujacy.Inwestor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.math.BigDecimal;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spolka  implements Runnable
{
	private String nazwa;
	private String dataPierwszejWyceny;
	private double kursOtwarcia;
	private double aktualnyKurs;
	private double minKurs;
	private double maxKurs;
	private int liczbaAkcji;
	private double zysk=0;
	private double przychod=0;
	private double kapitalWlasny;
	private double kapitalZakladowy;
	private int wolumen=0;
	private double obroty=0;
	public Double[] listaHistoria = new Double[10];
        public int idx=0;
        
                       
private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();        
    }
    
        public void run()
        {
                Random generator = new Random();
                
            while(true)
            {
                
            try {
                //sleep(generator.nextInt(100)*100);
                sleep(1000);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(Inwestor.class.getName()).log(Level.SEVERE, null, ex);
            }
                //System.out.println("Jestem fajny");
            ; //losuj
            if(generator.nextInt(100)<10) generujPrzychod();
            if(generator.nextInt(100)<20) wypuscAkcje();
            if(generator.nextInt(100)<15) wykupAkcje();
            //sprzedajAkcje();
            }
        }
        
	public Spolka() throws FileNotFoundException, IOException
	{
		nazwa=Funkcje.losuj("src/losowania/spolki.txt",4621);
		dataPierwszejWyceny=Funkcje.losuj("src/losowania/daty.txt",4022);
		Random generator = new Random();
                maxKurs=minKurs=aktualnyKurs=kursOtwarcia=generator.nextInt(6000)/100;
                zysk=generator.nextInt(300)*1000;
		liczbaAkcji=generator.nextInt(1000)*10;
		kapitalZakladowy=generator.nextInt(1000)*1000;
		kapitalWlasny=kapitalZakladowy+(generator.nextInt(1000)+1)*1000;
                listaHistoria[0]=aktualnyKurs;
                idx++;     
	}
	
	public Spolka(String nazwa,	String dataPierwszejWyceny,	double kursOtwarcia, double aktualnyKurs,
	double minKurs,	double maxKurs,	int liczbaAkcji, double zysk, double przychod, double kapitalWlasny,
	double kapitalZakladowy, int wolumen, double obroty)
	{
		this.nazwa=nazwa;
		this.dataPierwszejWyceny=dataPierwszejWyceny;
		this.kursOtwarcia=kursOtwarcia;
		this.aktualnyKurs=aktualnyKurs;
		this.minKurs=minKurs;
		this.maxKurs=maxKurs;
		this.liczbaAkcji=liczbaAkcji;
		this.zysk=zysk;
		this.przychod=przychod;
		this.kapitalWlasny=kapitalWlasny;
		this.kapitalZakladowy=kapitalZakladowy;
		this.wolumen=wolumen;
		this.obroty=obroty;
                listaHistoria[0]=aktualnyKurs;
                idx++;     
	}

    public Double[] getListaHistoria() {
        return listaHistoria;
    }

    public void setListaHistoria(Double[] listaHistoria) {
        this.listaHistoria = listaHistoria;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
	
        
        public Spolka szukaj(String nazwa)
        {
            for (Spolka inw: Main.listaSpolek)
                if (inw.getNazwa().equals(nazwa)) return inw;
            return null;
        }
        
	public void modyfikuj()
	{
		
	}
	
	public synchronized void wypuscAkcje()
	{
            Random generator = new Random();
            liczbaAkcji+=generator.nextInt(100)*10;
		
	}

	public synchronized void wykupAkcje()
	{
		
            Random generator = new Random();
            liczbaAkcji-=generator.nextInt(100)*10;            
	}	

	public void generujZysk()
	{
                Random generator = new Random();
                for(Spolka sp: Main.listaSpolek)
                    if(generator.nextInt(100)<10) sp.setZysk(zysk+przychod*0.7);
	}

	public void generujPrzychod()
	{
                Random generator = new Random();
                for(Spolka sp: Main.listaSpolek)
                    if(generator.nextInt(100)<10) 
                    {
                        sp.setPrzychod(przychod+generator.nextInt(50)*1000);
                        aktualnyKurs=Math.abs(new BigDecimal(aktualnyKurs+generator.nextDouble()*10).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        if(aktualnyKurs>maxKurs) maxKurs=aktualnyKurs;
                        sp.generujZysk();
                    }
                              
	}
	
	public void usun(Spolka spolka)
	{
		spolka=null;
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

    /**
     * @param nazwa the nazwa to set
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * @return the dataPierwszejWyceny
     */
    public String getDataPierwszejWyceny() {
        return dataPierwszejWyceny;
    }

    /**
     * @param dataPierwszejWyceny the dataPierwszejWyceny to set
     */
    public void setDataPierwszejWyceny(String dataPierwszejWyceny) {
        this.dataPierwszejWyceny = dataPierwszejWyceny;
    }

    /**
     * @return the kursOtwarcia
     */
    public double getKursOtwarcia() {
        return kursOtwarcia;
    }

    /**
     * @param kursOtwarcia the kursOtwarcia to set
     */
    public void setKursOtwarcia(double kursOtwarcia) {
        this.kursOtwarcia = kursOtwarcia;
    }

    /**
     * @return the aktualnyKurs
     */
    public double getAktualnyKurs() {
        return aktualnyKurs;
    }

    /**
     * @param aktualnyKurs the aktualnyKurs to set
     */
    public void setAktualnyKurs(double aktualnyKurs) {
        this.aktualnyKurs = aktualnyKurs;
    }

    /**
     * @return the minKurs
     */
    public double getMinKurs() {
        return minKurs;
    }

    /**
     * @param minKurs the minKurs to set
     */
    public void setMinKurs(double minKurs) {
        this.minKurs = minKurs;
    }

    /**
     * @return the maxKurs
     */
    public double getMaxKurs() {
        return maxKurs;
    }

    /**
     * @param maxKurs the maxKurs to set
     */
    public void setMaxKurs(double maxKurs) {
        this.maxKurs = maxKurs;
    }

    /**
     * @return the liczbaAkcji
     */
    public int getLiczbaAkcji() {
        return liczbaAkcji;
    }

    /**
     * @param liczbaAkcji the liczbaAkcji to set
     */
    public void setLiczbaAkcji(int liczbaAkcji) {
        this.liczbaAkcji = liczbaAkcji;
    }

    /**
     * @return the zysk
     */
    public double getZysk() {
        return zysk;
    }

    /**
     * @param zysk the zysk to set
     */
    public void setZysk(double zysk) {
        this.zysk = zysk;
    }

    /**
     * @return the przychod
     */
    public double getPrzychod() {
        return przychod;
    }

    /**
     * @param przychod the przychod to set
     */
    public void setPrzychod(double przychod) {
        this.przychod = przychod;
    }

    /**
     * @return the kapitalWlasny
     */
    public double getKapitalWlasny() {
        return kapitalWlasny;
    }

    /**
     * @param kapitalWlasny the kapitalWlasny to set
     */
    public void setKapitalWlasny(double kapitalWlasny) {
        this.kapitalWlasny = kapitalWlasny;
    }

    /**
     * @return the kapitalZakladowy
     */
    public double getKapitalZakladowy() {
        return kapitalZakladowy;
    }

    /**
     * @param kapitalZakladowy the kapitalZakladowy to set
     */
    public void setKapitalZakladowy(double kapitalZakladowy) {
        this.kapitalZakladowy = kapitalZakladowy;
    }

    /**
     * @return the wolumen
     */
    public int getWolumen() {
        return wolumen;
    }

    /**
     * @param wolumen the wolumen to set
     */
    public void setWolumen(int wolumen) {
        this.wolumen = wolumen;
    }

    /**
     * @return the obroty
     */
    
    public double getObroty() {
        return obroty;
    }

    /**
     * @param obroty the obroty to set
     */
    public void setObroty(double obroty) {
        this.obroty = obroty;
    }

    public void przesun() {
        for(int i=1; i<10; i++)
        {
            listaHistoria[i-1]=listaHistoria[i];
            listaHistoria[i]=null;
        }
    }
}
