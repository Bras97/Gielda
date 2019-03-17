package klasy;

import inwestujacy.Fundusz;
import inwestujacy.Inwestor;
import inwestujacy.Klient;
import aplikacja.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import sun.applet.Main;
//import grafika.*;

public class Main implements Serializable {

    /*
    public static ObservableList<Inwestor> listaInwestorow = FXCollections.observableArrayList();
    public static ObservableList<Fundusz> listaFunduszow = FXCollections.observableArrayList();
    public static ObservableList<String> listaKrajow = FXCollections.observableArrayList();
    public static ObservableList<Waluta> listaWalut = FXCollections.observableArrayList();
    public static ObservableList<Spolka> listaSpolek = FXCollections.observableArrayList();
    public static ObservableList<Spolka> listaSZU20 = FXCollections.observableArrayList();
    public static ObservableList<Indeks> listaIndeksow = FXCollections.observableArrayList();
    public static ObservableList<Surowiec> listaSurowcow = FXCollections.observableArrayList();
    public static ObservableList<String> listaKoron = FXCollections.observableArrayList();
    public static ObservableList<Gielda> listaGield = FXCollections.observableArrayList();
    public static ObservableList<String> listaZlotowek = FXCollections.observableArrayList();
    //public static ObservableList<Inwestor>  listaInwestorow;
    */
    public static List<Inwestor> listaInwestorow = new ArrayList<>();
    public static List<Fundusz> listaFunduszow = new ArrayList<>();
    public static List<String> listaKrajow = new ArrayList<>();
    public static List<Waluta> listaWalut = new ArrayList<>();
    public static List<Spolka> listaSpolek = new ArrayList<>();
    public static List<Spolka> listaSZU20 = new ArrayList<>();
    public static List<Indeks> listaIndeksow = new ArrayList<>();
    public static List<Surowiec> listaSurowcow = new ArrayList<>();
    public static List<String> listaKoron = new ArrayList<>();
    public static List<Gielda> listaGield = new ArrayList<>();
    public static List<String> listaZlotowek = new ArrayList<>();


    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */

    public static void main(String[] args
    ) throws FileNotFoundException, IOException, InterruptedException, ClassNotFoundException {
        // TODO Auto-generated method stub
        ////Serializator serializable = new Serializator();
        //listaInwestorow = serializable.read();

        
       //for(Inwestor i: listaInwestorow)
           // System.out.println(i.getNazwisko());
       
       //serial.wczytaj();
       
        Thread threadInwestor = new Thread(new Inwestor());
        threadInwestor.start();

        Thread threadSpolka = new Thread(new Spolka());
        threadSpolka.start();

        Thread threadWaluta = new Thread(new Waluta());
        threadWaluta.start();

        Thread threadSurowiec = new Thread(new Surowiec());
        threadSurowiec.start();
        
        
        
        
        Klient kl = new Klient();
        kl.pojawSie();

        String[] kraje = new String[243];
        FileReader fileReader = new FileReader("src/losowania/kraje.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        for (int i = 0; i < 243; i++) {
            String textLine = bufferedReader.readLine();
            listaKrajow.add(textLine);
        }

        //for(Kraj k: kraje) listaKrajow.add(k);
        listaZlotowek.add(listaKrajow.get(160));
        listaKoron.add(listaKrajow.get(15));
        listaKoron.add(listaKrajow.get(35));

        Waluta zloty = new Waluta("Zloty", listaZlotowek, 2.58, 2.573);
        Waluta korona = new Waluta("Korona", listaKoron, 3.28, 3.263);

        List<String> listaEuro = new ArrayList<>();
        listaEuro.add(listaKrajow.get(55));
        listaEuro.add(listaKrajow.get(25));
        listaEuro.add(listaKrajow.get(155));
        listaZlotowek.add(listaKrajow.get(142));
        listaZlotowek.add(listaKrajow.get(56));
        Waluta euro = new Waluta("Euro", listaEuro, 3.88, 3.869);

        listaWalut.add(zloty);
        listaWalut.add(euro);
        listaWalut.add(korona);

        Spolka[] spolki = new Spolka[2];
        spolki[0] = new Spolka("Szubinianka", "20.09.2015", 1000, 8, 7, 13, 100, 7000,
                1000, 80000, 200000, 40, 30000);
        spolki[1] = new Spolka("SZUBINpol", "20.09.2016", 1500, 9, 7, 19, 200, 7000,
                1000, 80000, 200000, 40, 30000);

        for (Spolka i : spolki) {
            listaSpolek.add(i);
        }

        listaSZU20.add(spolki[0]);
        listaSZU20.add(spolki[1]);

        Inwestor[] inwestorzy = new Inwestor[3];
        inwestorzy[0] = new Inwestor("Adam", "Zielinski", "97191914455", 20000);
        inwestorzy[1] = new Inwestor("Chuck", "Norris", "60919121423", 100000);
        inwestorzy[2] = new Inwestor("Christiano", "Ronaldo", "85919191664", 900000);

        for (Inwestor i : inwestorzy) {
            listaInwestorow.add(i);
        }
        //inwestorzy[0].kupAkcje();
        //inwestorzy[1].kupAkcje();
        //inwestorzy[2].kupAkcje();
        // System.out.priintln(listaInwestorow.get(2).PESEL);

        listaFunduszow.add(new Fundusz("Eric", "Cartman", "Cartmanland"));
        listaFunduszow.add(new Fundusz("Stan", "Marsh", "Terrence"));
        listaFunduszow.add(new Fundusz("Kyle", "Broflovski", "Fillip"));

        Kurs[] kursy = new Kurs[1];
        kursy[0] = new Kurs(zloty, euro, 4.33, 4.20);

        Indeks SZU20 = new Indeks("SZU20", 1, listaSZU20);

        listaIndeksow.add(SZU20);
        for (int i = 0; i < 3; i++) {
            listaIndeksow.add(new Indeks());
        }

        Surowiec[] surowce = new Surowiec[3];
        surowce[0] = new Surowiec("ropa", "barylka", zloty, 100, 80, 110);
        surowce[1] = new Surowiec("zloto", "uncja", zloty, 600, 480, 710);
        surowce[2] = new Surowiec("cegla", "tona", zloty, 50, 40, 70);

        for (Surowiec s : surowce) {
            listaSurowcow.add(s);
        }

        RynekSurowcow rynekSurowcow = new RynekSurowcow("Rynek surowcow", listaSurowcow);
        RynekWalut rynekWalut = new RynekWalut("Rynek walut", listaWalut);
        Gielda g1 = new Gielda("Bazarek w Szubinie", zloty, "Szubin", "Polska", "Złotopolskich 30", listaIndeksow, 5.4);
        listaGield.add(g1);

        Inwestor adam = new Inwestor();
        adam.wyswietl();

        Fundusz jessie = new Fundusz();
        jessie.wyswietl();

        //Surowiec nowy = new Surowiec();
        String nazwa = UUID.randomUUID().toString();
        System.out.println(nazwa);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pulpit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pulpit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pulpit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pulpit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        // Waluta waluta = new Waluta();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //Thread threadPulpit = new Thread(new Pulpit());
                    //threadPulpit.start();
                    Pulpit pulpit = new Pulpit();
                    pulpit.setVisible(true);
                    /*while(true) {
                    Thread.sleep(1000);
                    pulpit.setVisible(true);
                    pulpit.repaint();

                    }*/
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //serializacja.zapisz();
       // serializable.save((Serializable) listaInwestorow);
     
       Serial serial = new Serial();
       serial.zapisz();
        System.out.println("XXXXXXXX");
       for(Inwestor i: listaInwestorow)
            System.out.println(i.getNazwisko());
        listaInwestorow.add(new Inwestor());
        System.out.println("YYYYYY");
       for(Inwestor i: listaInwestorow)
            System.out.println(i.getNazwisko());
       serial.wczytaj();
        System.out.println("ZZZZZZZ");
       for(Inwestor i: listaInwestorow)
            System.out.println(i.getNazwisko());
           
       
    }
}
