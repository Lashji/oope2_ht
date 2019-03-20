package ui;

import harjoitustyo.apulaiset.In;
import logic.Tulkki;

public class Kayttoliittyma {
    private In in;
    private Tulkki tulkki;

    public Kayttoliittyma(In uusiIn, Tulkki uusiTulkki){
        this.in = uusiIn;
        this.tulkki = uusiTulkki;
    }


    public void start(){
        boolean jatka = true;


        while (jatka){

            String valinta = In.readString();

            jatka = tulkki.doCommand(sanitize(valinta));

        }



    }

    public static String sanitize(String s){
        return s.toLowerCase().trim();
    }
}
