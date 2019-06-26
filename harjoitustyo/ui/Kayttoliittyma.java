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
    private static final String ERROR_MESSAGE = "Error!";

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
            jatka = komento(valinta);




            if (jatka)
                tulkki.tulostaPolku();
        }
        System.out.println("Shell terminated.");
    }

    public boolean komento(String valinta) throws IllegalArgumentException, NumberFormatException {
        String[] valintaPaloina = valinta.split(" ");
        int paramPituus = valintaPaloina.length;

        if (valintaPaloina.length >= 1) {

            String komento = valintaPaloina[0];
            String parametri = "";
            String parametri2 = "";

            if (paramPituus >= 2) {
                parametri = valintaPaloina[1];
            }

            if (paramPituus >= 3) {
                parametri2 = valintaPaloina[2];
            }

            switch (komento) {

                case "md":
                    if (parametri != null && !parametri.isEmpty() &&
                            !tulkki.tarkistaOnkoNimiOlemassa(parametri) && paramPituus == 2)
                        try {
                            tulkki.teeUusiKansio(parametri);
                        } catch (IllegalArgumentException e) {
                            System.out.println(ERROR_MESSAGE);
                        }
                    else
                        System.out.println(ERROR_MESSAGE);
                    break;
                case "mf":
                    if (parametri != null && !parametri.isEmpty() && paramPituus <= 3
                            && !tulkki.tarkistaOnkoNimiOlemassa(parametri)) {
                        try {
                            tulkki.teeUusiTiedosto(parametri, parametri2.isEmpty() ? 0 : Integer.valueOf(parametri2));
                        } catch (IllegalArgumentException e) {
                            System.out.println(ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println(ERROR_MESSAGE);

                    }
                    break;
                case "cd":
                    if (parametri == null || paramPituus > 2) {
                        System.out.println(ERROR_MESSAGE);
                    } else {
                        tulkki.vaihdaKansiota(parametri);
                    }
                    break;
                case "ls":
//                   Jos haetaan parametrilla tarkistetaan että Tieto on olemassa
                    if (!parametri.isEmpty())
                        if (tulkki.tarkistaOnkoNimiOlemassa(parametri) && paramPituus == 2)
                            tulkki.listaaSisalto(parametri);
                        else
                            System.out.println(ERROR_MESSAGE);
                    else
                        tulkki.listaaSisalto(parametri);
                    break;
                case "rm":
                    if (!parametri.isEmpty() && paramPituus == 2 && tulkki.tarkistaOnkoNimiOlemassa(parametri))
                        try {
                            tulkki.poistaSisaltoa(parametri);
                        } catch (Exception e) {
                            System.out.println(ERROR_MESSAGE);
                        }
                    else
                        System.out.println(ERROR_MESSAGE);
                    break;
                case "mv":
                    if (!parametri.isEmpty() && !parametri2.isEmpty()
                            && paramPituus == 3
                            && tulkki.tarkistaOnkoNimiOlemassa(parametri)
                            && !tulkki.tarkistaOnkoNimiOlemassa(parametri2)) {
                        try {

                            tulkki.muutaNimi(parametri, parametri2);
                        } catch (IllegalArgumentException e) {
                            System.out.println(ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println(ERROR_MESSAGE);
                    }
                    break;
                case "cp":
                    if (!parametri.isEmpty() && !parametri2.isEmpty()
                            && paramPituus == 3
                            && tulkki.tarkistaOnkoNimiOlemassa(parametri)
                    ) {
                        tulkki.kopioi(parametri, parametri2);
                    } else {
                        System.out.println(ERROR_MESSAGE);
                    }
                    break;
                case "find":
                    if (paramPituus == 1) {
                        tulkki.find();
                    } else {
                        System.out.println(ERROR_MESSAGE);
                    }
                    break;
                case "exit":
                    return false;
                case "--help":
                    tulkki.apuTeksti();
                    break;
                default:
                    System.out.println(ERROR_MESSAGE);
            }
        }

        return true;
    }

//    public static String sanitize(String s) {
//        return s.toLowerCase().trim();
//    }
}
