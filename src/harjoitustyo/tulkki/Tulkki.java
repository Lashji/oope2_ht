package harjoitustyo.tulkki;

import harjoitustyo.apulaiset.In;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tiedosto;
import harjoitustyo.tiedot.Tieto;

import java.util.LinkedList;

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
                    System.out.println("md");
                    teeUusiKansio(parametri);
                    break;
                case "mf":
                    System.out.println("mf");
                    teeUusiTiedosto(parametri, Integer.valueOf(parametri2));
                    break;
                case "cd":
                    System.out.println("cd");
                    vaihdaKansiota(parametri);
                    break;
                case "ls":
                    System.out.println("ls");
                    listaaSisalto(parametri);
                    break;
                case "rm":
                    System.out.println("rm");
                    poistaSisaltoa(parametri);
                    break;
                case "mv":
                    System.out.println("mv");
                    liikutaSisaltoa(parametri);
                    break;
                case "cp":
                    System.out.println("cp");
                    kopioi(parametri);
                    break;
                case "find":
                    System.out.println("find");
                    etsi(parametri);
                    break;
                case "exit":
                    System.out.println("exit");
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

        if (!juuri.lisaa(new Hakemisto(sb, juuri))) {
            System.out.println("Tiedoston lisääminen epäonnistui");
        }
    }

    private void teeUusiTiedosto(String valinta, int koko) {
        if (valinta == null || valinta.length() == 0) {
            juuri.lisaa(new Tiedosto());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(valinta);
            juuri.lisaa(new Tiedosto(sb, koko));
        }
    }

    private void vaihdaKansiota(String valinta) {

        if (valinta.equals("..")) {
            if (!sijainti.equals(juuri)) {
                sijainti(sijainti.ylihakemisto());
            }
        } else {
            LinkedList<Tieto> lista = sijainti.hae(valinta);
            if (!lista.isEmpty()){
                sijainti((Hakemisto) lista.getFirst());
            }
        }

    }

    private void listaaSisalto(String valinta) {



    }

    private void poistaSisaltoa(String valinta) {
        Tieto tmp = sijainti.hae(valinta).get(0);
        sijainti.poista(tmp);
    }

    private void liikutaSisaltoa(String valinta) {
    }


    private void etsi(String valinta) {
    }

    private void kopioi(String valinta) {
    }

    private void sijainti(Hakemisto h) throws IllegalArgumentException {
        if (h != null)
            sijainti = h;
        else
            throw new IllegalArgumentException();
    }

    public Hakemisto sijainti() {
        return sijainti;
    }


    /*
     * Print functions
     */

    public void helloText() {
        System.out.println("Welcome to SOS." +
                "\n/>");
    }

    public void helpText() {
        System.out.println("Commands you can do: " +
                "\nmd - creates new directory" +
                "\nmf - creates new file" +
                "\ncd - changes directory" +
                "\nls - lists directory" +
                "\nrm - removes file" +
                "\nmv - moves file" +
                "\ncp - copies file" +
                "\nfind - searchs for a file" +
                "\nexit - exit program");
    }
}
