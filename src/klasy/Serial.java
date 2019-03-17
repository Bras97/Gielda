/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasy;

import inwestujacy.Inwestor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
//import static klasy.Main.listaInwestorow;
import klasy.*;
import inwestujacy.*;

/**
 *
 * @author Elater
 */
public class Serial {
    
    public void wczytaj() throws IOException {

        String nazwaPliku ="src/serializacja.simi";
        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(nazwaPliku)));
        out.writeObject(Main.listaInwestorow);   
        out.close();
    }
     public void zapisz() throws IOException, ClassNotFoundException{

        String nazwaPliku = "src/serializacja.simi";
        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(nazwaPliku)));
      
            Main.listaInwestorow = (ArrayList<Inwestor>) in.readObject();
      
            in.close();
     }

}
/*
    public void zapisz() throws FileNotFoundException
    {               
        PrintWriter zapis = new PrintWriter("src/serializacja.txt");
        String napis="";
        String napisListy="";
        String napisHistoria="";
        zapis.println("Inwestorzy");
        for(Inwestor in: Main.listaInwestorow)
        {
            napis=""; napisListy=""; napisHistoria="";
            for(Akcja a: in.getListaAkcji())
                napisListy=a.getNazwa() + " " + Double.toString(a.getWartosc()) + " " + Integer.toString(a.getIleKupionychAkcji()) + " " + a.getKtoWypuscil().getNazwa();
            napis=in.getImie() + " " + in.getNazwisko() + " " + in.getPESEL() + " " + Double.toString(in.getBudzetInwestycyjny()) + " " + in.getListaAkcji() + " " + napisListy;
            zapis.println(napis);
        }
        zapis.println("Fundusze");
        for(Fundusz in: Main.listaFunduszow)
        {
            napis=""; napisListy=""; napisHistoria="";
            for(Akcja a: in.getListaAkcji())
                napisListy=a.getNazwa() + " " + Double.toString(a.getWartosc()) + " " + Integer.toString(a.getIleKupionychAkcji()) + " " + a.getKtoWypuscil().getNazwa();
            napis=in.getImie() + " " + in.getNazwisko() + " " + in.getNazwa()+ " " + napisListy;
            zapis.println(napis);
        }
        
        zapis.println("Waluty");
        for(Waluta in: Main.listaWalut)
        {
            napis=""; napisListy=""; napisHistoria="";
            for(String s: in.getListaKrajow())
                napisListy+=s + " ";
            for(int i=0; i<10; i++)
            {                
                if(in.getListaHistoria()[i]==null) break;
                napisHistoria+=Double.toString(in.getListaHistoria()[i]) + " ";                
            }
            napis=in.getNazwa() + " " + Double.toString(in.getWartoscKupna()) + " " + Double.toString(in.getWartoscSprzedazy()) 
                    + " " + napisListy + " " + Integer.toString(in.getIdx()) + " "  + napisHistoria;
            zapis.println(napis);
        }
        
       
        zapis.println("Spolki");
        for(Spolka in: Main.listaSpolek)
        {
            napis=""; napisListy=""; napisHistoria="";
            for(String s: in.getListaKrajow())
                napisListy+=s + " ";
            for(int i=0; i<10; i++)
            {                
                if(in.getListaHistoria()[i]==null) break;
                napisHistoria+=Double.toString(in.getListaHistoria()[i]) + " ";                
            }
            napis=in.getNazwa() + " " + Double.toString(in.getWartoscKupna()) + " " + Double.toString(in.getWartoscSprzedazy()) 
                    + " " + napisListy + " " + Integer.toString(idx) + " "  + napisHistoria;
            zapis.println(napis);
        }

        
        zapis.println("KONIEC");
        zapis.close();
  }
    

//public void wczytaj() throws FileNotFoundException
//{
/*
            FileReader fileReader = new FileReader("src/serializacja.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String textLine = bufferedReader.readLine();
            for(int i=0; i<x; i++)      textLine = bufferedReader.readLine();

            //System.out.println(textLine);
            bufferedReader.close();
            }*/
    /*
    <T extends Serializable> void save(T object)
    {
        try {
            FileOutputStream fos = new FileOutputStream( "src/serializacja.txt");
            ObjectOutputStream output = new ObjectOutputStream(fos);
            output.writeObject(object);
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    <T> T read(String fileName){
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            T readObject = (T) ois.readObject();

            return readObject;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
*/