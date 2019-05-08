// Lassi Palojärvi
// 432220
package harjoitustyo.tulkki;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tiedosto;
import harjoitustyo.tiedot.Tieto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Simuloi Terminal Emulaattoria
 * Tulkki hallinnoi kaikkia toimintoja
 */
public class Tulkki {

    private static final String ERROR_MESSAGE = "Error!";
    private Hakemisto juuri;
    private Hakemisto sijainti;
    private List<String> path;

    public Tulkki() {
        juuri = new Hakemisto();
        juuri.nimi(new StringBuilder("/"));
        sijainti(juuri);
        path = new ArrayList<>();
        path.add(juuri.nimi().toString());
        aloitusTeksti();
    }

    /**
     * Ottaa vastaan komennon käyttöliittymältä ja jakaa sen eteenpäin
     * @param valinta
     * @return
     * @throws IllegalArgumentException
     * @throws NumberFormatException
     */
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
                            !tarkistaOnkoNimiOlemassa(parametri) && paramPituus == 2)
                        try {
                            teeUusiKansio(parametri);
                        } catch (IllegalArgumentException e) {
                            System.out.println(ERROR_MESSAGE);
                        }
                    else
                        System.out.println(ERROR_MESSAGE);
                    break;
                case "mf":
                    if (parametri != null && !parametri.isEmpty() && paramPituus <= 3
                            && !tarkistaOnkoNimiOlemassa(parametri)) {
                        try {
                            teeUusiTiedosto(parametri, parametri2.isEmpty() ? 0 : Integer.valueOf(parametri2));
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
                        vaihdaKansiota(parametri);
                    }
                    break;
                case "ls":
//                   Jos haetaan parametrilla tarkistetaan että Tieto on olemassa
                    if (!parametri.isEmpty())
                        if (tarkistaOnkoNimiOlemassa(parametri) && paramPituus == 2)
                            listaaSisalto(parametri);
                        else
                            System.out.println(ERROR_MESSAGE);
                    else
                        listaaSisalto(parametri);
                    break;
                case "rm":
                    if (!parametri.isEmpty() && paramPituus == 2 && tarkistaOnkoNimiOlemassa(parametri))
                        try {
                            poistaSisaltoa(parametri);
                        } catch (Exception e) {
                            System.out.println(ERROR_MESSAGE);
                        }
                    else
                        System.out.println(ERROR_MESSAGE);
                    break;
                case "mv":
                    if (!parametri.isEmpty() && !parametri2.isEmpty()
                            && paramPituus == 3
                            && tarkistaOnkoNimiOlemassa(parametri)
                            && !tarkistaOnkoNimiOlemassa(parametri2)) {
                        try {

                            muutaNimi(parametri, parametri2);
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
                            && tarkistaOnkoNimiOlemassa(parametri)
                    ) {
                        kopioi(parametri, parametri2);
                    } else {
                        System.out.println(ERROR_MESSAGE);
                    }
                    break;
                case "find":
                    if (paramPituus == 1) {
                        find();
                    } else {
                        System.out.println(ERROR_MESSAGE);
                    }
                    break;
                case "exit":
                    return false;
                case "--help":
                    apuTeksti();
                    break;
                default:
                    System.out.println(ERROR_MESSAGE);
            }
        }

        return true;
    }

    /**
     * Tarkistaa esiintyykö nimi jo halutussa hakemistossa
     * @param parametri
     * @return
     */
    private boolean tarkistaOnkoNimiOlemassa(String parametri) {

        if (parametri.contains("*"))
            return true;

        for (Tieto t : sijainti.sisalto()) {
            if (t.nimi().toString().equals(parametri)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Luo uuden kansion simulaatioon
     * @param valinta
     * @throws IllegalArgumentException
     */
    private void teeUusiKansio(String valinta) throws IllegalArgumentException {

        StringBuilder sb = new StringBuilder();
        sb.append(valinta);

        if (!sijainti.lisaa(new Hakemisto(sb, this.sijainti))) {
            System.out.println("Tiedoston lisääminen epäonnistui");
        }
    }

    /**
     * Luo uuden tiedoston simulaatioon.
     * @param valinta
     * @param koko
     * @throws IllegalArgumentException
     */
    private void teeUusiTiedosto(String valinta, int koko) throws IllegalArgumentException {
        if (valinta == null || valinta.length() == 0) {
            sijainti.lisaa(new Tiedosto());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(valinta);
            sijainti.lisaa(new Tiedosto(sb, koko));
        }
    }

    /**
     * Vaihtaa kansiota
     * @param valinta
     */
    private void vaihdaKansiota(String valinta) {

        if (valinta == null || valinta.isEmpty()) {
            sijainti(juuri);
            path.clear();
            path.add(juuri.nimi().toString());
            return;
        }

        if (valinta.equals("..")) {
            if (!sijainti.equals(juuri)) {
                path.remove(path.size() - 1);
                sijainti(sijainti.ylihakemisto());
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        } else {
            LinkedList<Tieto> lista = sijainti.hae(valinta);
            if (!lista.isEmpty()) {
                if (lista.getFirst() instanceof Hakemisto) {
                    sijainti((Hakemisto) lista.getFirst());
                    path.add(sijainti.nimi().toString());
                } else {
                    System.out.println(ERROR_MESSAGE);
                }
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        }

    }

    /**
     * Listaa kansion sisällön
     * @param parametri
     */
    private void listaaSisalto(String parametri) {

        if (parametri.isEmpty()) {
            this.sijainti.sisalto().forEach(System.out::println);
            return;
        }

        LinkedList<Tieto> haku = sijainti.hae(parametri);
        if (haku != null && haku.size() > 0)
            haku.forEach(System.out::println);
        else if (parametri.equals("*.txt") || parametri.equals("*.gif")) {
            System.out.println(ERROR_MESSAGE);
        }

    }

    /**
     * Poistaa sisältöä hakemistosta
     * @param valinta
     * @throws IllegalArgumentException
     */
    private void poistaSisaltoa(String valinta) throws IllegalArgumentException {
        LinkedList<Tieto> tmp = sijainti.hae(valinta);
        if (tmp.isEmpty()) throw new IllegalArgumentException();

        for (Tieto t : tmp) {
            sijainti.poista(t);
        }
    }

    /**
     * Muuttaa Tiedon nimen
     * @param vanhaNimi
     * @param uusiNimi
     * @throws IllegalArgumentException
     */
    private void muutaNimi(String vanhaNimi, String uusiNimi) throws IllegalArgumentException {
        List<Tieto> vanhaTieto = sijainti.hae(vanhaNimi);

        if (!vanhaTieto.isEmpty()) {
            List<Tieto> lista = sijainti.hae(uusiNimi);
            if (lista.isEmpty()) {
                vanhaTieto.get(0).nimi(new StringBuilder(uusiNimi));
            }
            sijainti.sisalto().jarjesta();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Listaa hakemiston tiedot rekursiivisesti käyttäen hyväksi HakemistoIteraattoria
     */
    private void find() {
        Iterator<Tieto> itr = sijainti.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    /**
     * Syväkopioi Hakusanalla annetun tiedon joko toiseen kansioon tai toiselle nimelle.
     * @param vanhaNimi
     * @param uusiNimi
     */
    private void kopioi(String vanhaNimi, String uusiNimi) {

        if (vanhaNimi.startsWith("*") && !uusiNimi.isEmpty()) {
            kopioiLista(vanhaNimi, uusiNimi);
            return;
        }

        if (vanhaNimi.startsWith("*") && uusiNimi.isEmpty()) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        LinkedList<Tieto> uusi = sijainti.hae(uusiNimi);


        List<Tieto> vanhaTietoListana = sijainti.hae(vanhaNimi);
        Tieto vanhaTieto = !vanhaTietoListana.isEmpty() ? vanhaTietoListana.get(0) : null;
        if (!sijainti.hae(vanhaNimi).isEmpty() && vanhaTieto != null) {
            Tieto kopio = vanhaTieto.copy();
            if (kopio instanceof Hakemisto) {
                System.out.println(ERROR_MESSAGE);
                return;
            }
            if (uusi.size() > 0) {

                if (uusi.getFirst() instanceof Hakemisto) {
                    Hakemisto uusih = (Hakemisto) uusi.getFirst();
                    kopio.nimi(new StringBuilder(vanhaNimi));

                    if (uusih.hae(vanhaNimi).size() == 0 || vanhaTieto instanceof Hakemisto)
                        uusih.sisalto().lisaa(kopio);
                    else {
                        System.out.println(ERROR_MESSAGE);
                    }
                } else {
                    System.out.println(ERROR_MESSAGE);
                }
            } else {
                kopio.nimi(new StringBuilder(uusiNimi));
                sijainti.lisaa(kopio);
            }
        }
    }

    /**
     * Hirveä viritys joka tuli tehtyä pikaisesti työtä palautellessa kello 2:00 yöllä
     * Kopioi listan tai jotain sinnepäin. Haluan nukkumaan.
     * @param vanhaNimi
     * @param uusiNimi
     */
    private void kopioiLista(String vanhaNimi, String uusiNimi) {

        LinkedList<Tieto> haut = sijainti.hae(vanhaNimi);
        Tieto h = sijainti.ylihakemisto();
        if (uusiNimi.equals("..")) {
            h = (Hakemisto) sijainti.ylihakemisto();
            if (h.equals(juuri) && h == null) {
                System.out.println(ERROR_MESSAGE);
            }
        } else {
            LinkedList<Tieto> haku = sijainti.hae(uusiNimi);
            if (haku.size() == 0) {
                System.out.println(ERROR_MESSAGE);
                return;
            } else {
                h = haku.getFirst();
            }
        }

        if (h instanceof Hakemisto) {
            for (Tieto t : haut) {
                for (Tieto tt : ((Hakemisto) h).sisalto()) {
                    if (t.equals(tt)) {
                        System.out.println(ERROR_MESSAGE);
                        return;
                    }
                }
            }
            for (Tieto t : haut) {
                Tieto k = t.copy();
                ((Hakemisto) h).lisaa(k);
            }
        } else {
            System.out.println(ERROR_MESSAGE);
        }
    }

    private void sijainti(Hakemisto h) throws IllegalArgumentException {
        if (h != null) {
            sijainti = h;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Hakemisto sijainti() {
        return sijainti;
    }

    /*
     * Print functions
     */

    /**
     * Tulostaa aloitustekstin
     */
    public void aloitusTeksti() {
        System.out.print("Welcome to SOS.\n");
        tulostaPolku();
    }

    /**
     * Ohjeteksti komennoille
     */
    public void apuTeksti() {
        System.out.println("Commands you can do: " + "\nmd - creates new directory" + "\nmf - creates new file"
                + "\ncd - changes directory" + "\nls - lists directory" + "\nrm - removes file" + "\nmv - moves file"
                + "\ncp - copies file" + "\nfind - searchs for a file" + "\nexit - exit program");
    }

    /**
     * Tulostaa polun missä mennään
     */
    public void tulostaPolku() {
        StringBuilder sb = new StringBuilder();

        for (String s : path) {
            sb.append(s);
            if (!s.equals("/"))
                sb.append("/");
        }

        sb.append(">");
        System.out.println(sb.toString());
    }

    /**
     * Tulostaa Errorin, mutta tätä unohdin vissiin käyttää.
     */
    public void tulostaError() {
        System.out.println(ERROR_MESSAGE);
    }
}
