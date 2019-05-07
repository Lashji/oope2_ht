// Lassi Palojärvi
// 432220
package harjoitustyo.ui;

import harjoitustyo.apulaiset.In;
import harjoitustyo.tulkki.Tulkki;

public class Kayttoliittyma {
    private Tulkki tulkki;

    public Kayttoliittyma(Tulkki uusiTulkki) {
        this.tulkki = uusiTulkki;
    }

    public void start() {
        boolean jatka = true;

        while (jatka) {

            String valinta = In.readString();
            jatka = tulkki.komento(sanitize(valinta));

            if (jatka)
                tulkki.tulostaPolku();
        }
        System.out.println("Shell terminated.");
    }

    public static String sanitize(String s) {
        return s.toLowerCase().trim();
    }
}
