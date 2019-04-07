import harjoitustyo.tulkki.Tulkki;
import harjoitustyo.ui.Kayttoliittyma;
import harjoitustyo.apulaiset.In;

public class Oope2HT {
    public static void main(String[] args) {
        In in = new In();
        Tulkki tulkki = new Tulkki();
        Kayttoliittyma ui = new Kayttoliittyma(in, tulkki);

        ui.start();
    }
}
