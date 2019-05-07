// Lassi Palojärvi
// 432220
package harjoitustyo;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tiedosto;
import harjoitustyo.tiedot.Tieto;
import harjoitustyo.tulkki.Tulkki;
import harjoitustyo.ui.Kayttoliittyma;
import harjoitustyo.apulaiset.In;

import java.util.LinkedList;

public class Oope2HT {
    public static void main(String[] args) {

        Tulkki tulkki = new Tulkki();
        Kayttoliittyma ui = new Kayttoliittyma(tulkki);

        ui.start();
//        Testi();
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
//        OmaLista<String> lista = new OmaLista<>();
//
//        String v1 = new String("A");
//        String v2 = new String("A");
//        String v3 = new String("A");
//
//        lista.add(v1);
//        lista.add(v2);
//        lista.add(v3);
//        System.out.println("Lista: " + lista);
//        System.out.println("Paluuarvo: " + lista.poista(v3));
//        System.out.println("Lista. " + lista);
        Hakemisto hakemisto = new Hakemisto(new StringBuilder("cats"), null);
        Tiedosto tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
        Tiedosto tiedosto2 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
        Tiedosto tiedosto3 = new Tiedosto(new StringBuilder("ceiling_cat.gif"), 3677);
        Tiedosto tiedosto4 = new Tiedosto(new StringBuilder("dangerous_kitten.jpg"), 13432);
        hakemisto.lisaa(tiedosto1);
        hakemisto.lisaa(tiedosto2);
        hakemisto.lisaa(tiedosto3);
        hakemisto.lisaa(tiedosto4);
        LinkedList<Tieto> osumat1 = hakemisto.hae("**"); // [ ]
        LinkedList<Tieto> osumat2 = hakemisto.hae("*j*peg"); // [ ]
        LinkedList<Tieto> osumat3 = hakemisto.hae("***"); // [ ]
        LinkedList<Tieto> osumat4 = hakemisto.hae("i_really_really_really_like_this_picture.gif"); // [ ]
        LinkedList<Tieto> osumat5 = hakemisto.hae("g**"); // [ ]

        System.out.println(osumat1);
        System.out.println(osumat2);
        System.out.println(osumat3);
        System.out.println(osumat4);
        System.out.println(osumat5);

    }
}
