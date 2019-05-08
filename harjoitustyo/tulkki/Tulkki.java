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
                    poistaSisaltoa(parametri);
                    break;
                case "mv":
                    if (!parametri.isEmpty() && !parametri2.isEmpty()) {
                        muutaNimi(parametri, parametri2);
                    }
                    break;
                case "cp":
                    if (!parametri.isEmpty() && !parametri2.isEmpty()) {
                        kopioi(parametri, parametri2);
                    }
                    break;
                case "find":
                    find();
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

    private void teeUusiKansio(String valinta) throws IllegalArgumentException {

        StringBuilder sb = new StringBuilder();
        sb.append(valinta);

        if (!sijainti.lisaa(new Hakemisto(sb, this.sijainti))) {
            System.out.println("Tiedoston lisääminen epäonnistui");
        }
    }

    private void teeUusiTiedosto(String valinta, int koko) throws IllegalArgumentException {
        if (valinta == null || valinta.length() == 0) {
            sijainti.lisaa(new Tiedosto());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(valinta);
            sijainti.lisaa(new Tiedosto(sb, koko));
        }
    }

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


    private void listaaSisalto(String parametri) {

        if (parametri.isEmpty()) {
            this.sijainti.sisalto().forEach(System.out::println);
            return;
        }

        LinkedList<Tieto> haku = sijainti.hae(parametri);
        if (haku != null && haku.size() > 0) haku.forEach(System.out::println);
    }

    private void poistaSisaltoa(String valinta) {
        LinkedList<Tieto> tmp = sijainti.hae(valinta);

        for (Tieto t : tmp) {
            sijainti.poista(t);
        }
    }

    private void muutaNimi(String vanhaNimi, String uusiNimi) {
        List<Tieto> vanhaTieto = sijainti.hae(vanhaNimi);

        if (!vanhaTieto.isEmpty()) {
            List<Tieto> lista = sijainti.hae(uusiNimi);
            if (lista.isEmpty()) {
                vanhaTieto.get(0).nimi(new StringBuilder(uusiNimi));
            }

            sijainti.sisalto().jarjesta();

        }
    }

    private void find() {
        OmaLista<Tieto> tiedot = new OmaLista<>();
        lisaa(sijainti, tiedot);
        tiedot.forEach(System.out::println);
    }

    private void lisaa(Hakemisto hakemisto, OmaLista<Tieto> tiedot) {
        Iterator<Tieto> itr = hakemisto.iterator();

        while (itr.hasNext()) {
            Tieto t = itr.next();
            tiedot.add(t);
            if (t instanceof Hakemisto) {
                lisaa((Hakemisto) t, tiedot);
            }
        }

    }

    private void kopioi(String vanhaTiedosto, String uusiNimi) {
        List<Tieto> vanhaTietoListana = sijainti.hae(vanhaTiedosto);
        Tieto vanhaTieto = !vanhaTietoListana.isEmpty() ? vanhaTietoListana.get(0) : null;

        if (!sijainti.hae(vanhaTiedosto).isEmpty() && vanhaTieto != null) {
            Tieto kopio = vanhaTieto.copy();
            kopio.nimi(new StringBuilder(uusiNimi));
            sijainti.lisaa(kopio);
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
    public void aloitusTeksti() {
        System.out.print("Welcome to SOS.\n");
        tulostaPolku();
    }

    public void apuTeksti() {
        System.out.println("Commands you can do: " + "\nmd - creates new directory" + "\nmf - creates new file"
                + "\ncd - changes directory" + "\nls - lists directory" + "\nrm - removes file" + "\nmv - moves file"
                + "\ncp - copies file" + "\nfind - searchs for a file" + "\nexit - exit program");
    }

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

    public void tulostaError() {
        System.out.println(ERROR_MESSAGE);
    }
}
