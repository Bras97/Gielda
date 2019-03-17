package klasy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

public class Funkcje {

    
	public static String losuj(String sciezka, int ile) throws FileNotFoundException, IOException
	{            
            Random generator = new Random();
            int x=generator.nextInt( ile )+1;
            
            FileReader fileReader = new FileReader(sciezka);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String textLine = bufferedReader.readLine();
            for(int i=0; i<x; i++)      textLine = bufferedReader.readLine();

            //System.out.println(textLine);
            bufferedReader.close();
        /*
            String nazwa=null;
            File file = new File(sciezka);
            Scanner odczyt = new Scanner(file);
            for(int i=0; i<x; i++)			nazwa = odczyt.nextLine();
            odczyt.close();	
*/
            return textLine;
	}
        
        public static String losujString(int i) {
            String generatedString="";
            Random generator = new Random();
            for(int j=0; j<i; j++)
            {
                generatedString+=(char)(generator.nextInt(25)+65);
            }/*
            Character.toString ((char) i);
        byte[] array = new byte[i]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));*/

        return generatedString;
        }
}
