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

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Main tiedosto
 * Luodaan tulkki ja käynnistetään käyttöliittymä
 * Sisältää myös testin
 */
public class Oope2HT {
    /**
     * Main
     *
     * @param args args
     */
    public static void main(String[] args) {

        Tulkki tulkki = new Tulkki();
        Kayttoliittyma ui = new Kayttoliittyma(tulkki);

        ui.start();
//        Testi();
    }

    /**
     * TESTI
     */
    public static void Testi() {
        // Luodaan hakemistot. Alihakemistoista asetetaan viitteet ylihakemistoon
// ja alihakemistot lisätään ylihakemistoon.
        Hakemisto ylihakemisto = new Hakemisto(new StringBuilder("pics"), null);
        Hakemisto alihakemisto1 = new Hakemisto(new StringBuilder("cats"), ylihakemisto);
        Hakemisto alihakemisto2 = new Hakemisto(new StringBuilder("dogs"), ylihakemisto);
        ylihakemisto.lisaa(alihakemisto1);
        ylihakemisto.lisaa(alihakemisto2);

// Lisätään tiedostoja alihakemistoihin.
        Tiedosto tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
        Tiedosto tiedosto2 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
        Tiedosto tiedosto3 = new Tiedosto(new StringBuilder("ceiling_cat.gif"), 3677);
        Tiedosto tiedosto4 = new Tiedosto(new StringBuilder("dangerous_kitten.jpg"), 13432);
        Tiedosto tiedosto5 = new Tiedosto(new StringBuilder("worlds_ugliest_dog.jpg"), 118088);
        alihakemisto1.lisaa(tiedosto1);
        alihakemisto1.lisaa(tiedosto2);
        alihakemisto1.lisaa(tiedosto3);
        alihakemisto1.lisaa(tiedosto4);
        alihakemisto2.lisaa(tiedosto5);

// Kuljetaan ylihakemiston ja sen alihakemistojen läpi iteraattorilla ja tallennetaan iteraattorin
// palauttamat viitteet taulukkoon.
        int lkm = ylihakemisto.sisalto().size() + alihakemisto1.sisalto().size() + alihakemisto2.sisalto().size();
        Tieto[] viitteet = new Tieto[lkm];
        Iterator<Tieto> iteraattori = ylihakemisto.iterator();
        int ind = 0;
        while (iteraattori.hasNext()) {
            viitteet[ind++] = iteraattori.next();
        }


    }
}
