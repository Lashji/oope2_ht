// Lassi Palojärvi
// 432220
package harjoitustyo;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.tiedot.Tiedosto;
import harjoitustyo.tulkki.Tulkki;
import harjoitustyo.ui.Kayttoliittyma;
import harjoitustyo.apulaiset.In;

public class Oope2HT {
    public static void main(String[] args) {
        In in = new In();
        Tulkki tulkki = new Tulkki();
        Kayttoliittyma ui = new Kayttoliittyma(in, tulkki);

//        ui.start();
        Testi();
    }

    public static void Testi() {
        StringBuilder sb = new StringBuilder();
        sb.append("tiedosto");

//        Tiedosto t1 = new Tiedosto(sb, 1);
//        Tiedosto t2 = new Tiedosto(sb, 2);
//        StringBuilder sb1 = new StringBuilder("au.gif");
//        StringBuilder sb2 = new StringBuilder("fe.gif");
//
//        Tiedosto au = new Tiedosto(sb1, 1);
//        Tiedosto fe = new Tiedosto(sb2, 1);
//
//        System.out.println("Equals " + t1.equals(t2));
//        System.out.println("Compare: "+ au.compareTo(fe));
        OmaLista<String> lista = new OmaLista<>();

        String v1 = "A";
        String v2 = "A";
        String v3 = "A";

        lista.add(v1);
        lista.add(v2);
        lista.add(v3);
        System.out.println("Lista: " + lista);
        lista.remove(v3);
        System.out.println("Lista. " + lista);

    }
}
