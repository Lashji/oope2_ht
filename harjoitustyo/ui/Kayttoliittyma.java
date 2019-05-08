// Lassi Palojärvi
// 432220
package harjoitustyo.ui;

import harjoitustyo.apulaiset.In;
import harjoitustyo.tulkki.Tulkki;

/**
 * Käyttöliittymä luokka
 * Hallinnoi kaikkea käyttöliittymään liittyvää.
 * Ottaa vastaan komennot ja välittää ne tulkille.
 */
public class Kayttoliittyma {
    private Tulkki tulkki;

    public Kayttoliittyma(Tulkki uusiTulkki) {
        this.tulkki = uusiTulkki;
    }

    /**
     * Main loop
     */
    public void start() {
        boolean jatka = true;

        while (jatka) {

            String valinta = In.readString();
            jatka = tulkki.komento(valinta);

            if (jatka)
                tulkki.tulostaPolku();
        }
        System.out.println("Shell terminated.");
    }

//    public static String sanitize(String s) {
//        return s.toLowerCase().trim();
//    }
}
