// Lassi Palojärvi
// 432220
package harjoitustyo.tulkki;

import harjoitustyo.iteraattorit.HakemistoIteraattori;
import harjoitustyo.omalista.OmaLista;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tiedosto;
import harjoitustyo.tiedot.Tieto;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tulkki {

    private static final String ERROR_MESSAGE = "ERROR!";

    private Hakemisto juuri;
    private Hakemisto sijainti;

    public Tulkki() {
        juuri = new Hakemisto();
        sijainti(juuri);
        helloText();
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
                    teeUusiKansio(parametri);
                    break;
                case "mf":
                    if (parametri != null) {
                        teeUusiTiedosto(parametri, parametri2.isEmpty() ? 0 : Integer.valueOf(parametri2));
                    }
                    break;
                case "cd":
                    vaihdaKansiota(parametri);
                    break;
                case "ls":
                    listaaSisalto();
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
                    helpText();
                    break;
                default:
                    System.out.println(ERROR_MESSAGE);
            }

        }

        return true;
    }

    private void teeUusiKansio(String valinta) {

        StringBuilder sb = new StringBuilder();

        sb.append(valinta);

        if (!sijainti.lisaa(new Hakemisto(sb, juuri))) {
            System.out.println("Tiedoston lisääminen epäonnistui");
        }
    }

    private void teeUusiTiedosto(String valinta, int koko) {
        if (valinta == null || valinta.length() == 0) {
            sijainti.lisaa(new Tiedosto());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(valinta);
            sijainti.lisaa(new Tiedosto(sb, koko));
        }
    }

    private void vaihdaKansiota(String valinta) {
        if (valinta.equals("..")) {
            if (!sijainti.equals(juuri)) {
                sijainti(sijainti.ylihakemisto());
            }
        } else {
            LinkedList<Tieto> lista = sijainti.hae(valinta);
            if (!lista.isEmpty()) {
                sijainti((Hakemisto) lista.getFirst());
            }
        }
    }

    private void listaaSisalto() {
        System.out.println("tietojen maara: " + sijainti.sisalto().size());
        this.sijainti.sisalto().forEach(System.out::println);
    }

    private void poistaSisaltoa(String valinta) {
        Tieto tmp = sijainti.hae(valinta).get(0);
        sijainti.poista(tmp);
    }

    private void muutaNimi(String vanhaNimi, String uusiNimi) {
        List<Tieto> vanhaTieto = sijainti.hae(vanhaNimi);

        if (!vanhaTieto.isEmpty()) {
            List<Tieto> lista = sijainti.hae(uusiNimi);
            if (lista.isEmpty()) {
                vanhaTieto.get(0).nimi(new StringBuilder(uusiNimi));
            }
        }
    }

    private void find() {
        OmaLista<Tieto> tiedot = new OmaLista<Tieto>();
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
    public void helloText() {
        System.out.println("Welcome to SOS." + "\n/>");
    }

    public void helpText() {
        System.out.println("Commands you can do: " + "\nmd - creates new directory" + "\nmf - creates new file"
                + "\ncd - changes directory" + "\nls - lists directory" + "\nrm - removes file" + "\nmv - moves file"
                + "\ncp - copies file" + "\nfind - searchs for a file" + "\nexit - exit program");
    }
}
