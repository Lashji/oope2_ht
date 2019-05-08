// Lassi Palojärvi
// 432220
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Sailova;
import harjoitustyo.iteraattorit.HakemistoIteraattori;
import harjoitustyo.omalista.OmaLista;

import java.util.Iterator;

import java.util.LinkedList;

/**
 * Hakemisto wrappaa sisälleen omalista luokan.
 * Hakemisto periytyy Tieto luokasta
 */
public class Hakemisto extends Tieto implements Sailova<Tieto>, Iterable<Tieto> {

    private OmaLista<Tieto> sisalto;
    private Hakemisto ylihakemisto;

    public Hakemisto(StringBuilder uusiNimi, Hakemisto uusiYlihakemisto) throws IllegalArgumentException {
        super(uusiNimi);
        sisalto = new OmaLista<>();
        ylihakemisto(uusiYlihakemisto);
    }

    public Hakemisto() {
        super();
        sisalto = new OmaLista<>();
        ylihakemisto = null;
    }

    public OmaLista<Tieto> sisalto() {
        return sisalto;
    }

    public void ylihakemisto(Hakemisto h) {
        ylihakemisto = h;
    }

    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }

    public void sisalto(OmaLista<Tieto> uusiSisalto) throws IllegalArgumentException {
        if (uusiSisalto != null) {
            sisalto = uusiSisalto;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Metodi hakee hakusanalla sen nimisiä tiedostoja ja palauttaa ne listana.
     *
     * @param hakusana nimi tai ilmaus, johon hakemiston tiedostojen ja alihakemistojen
     *                 nimi� verrataan.
     * @return
     */
    @Override
    public LinkedList<Tieto> hae(String hakusana) {
        LinkedList<Tieto> lista = new LinkedList<>();
        for (Tieto t : sisalto) {
            if (t.equals(hakusana)) {
                lista.add(t);
            }
        }
        return lista;
    }

    /**
     * Lisää Tieto tyyppisen muutujanOmaListaan
     *
     * @param lisattava viite lis�tt�v��n tietoon.
     * @return
     */
    @Override
    public boolean lisaa(Tieto lisattava) {
        return sisalto.lisaa(lisattava);
    }

    /**
     * Poistaa muuttujan listasta
     * @param poistettava viite poistettavaan tietoon.
     * @return
     */
    @Override
    public boolean poista(Tieto poistettava) {
        return sisalto.poista(poistettava) > 0;
    }

    /**
     * Listaa hakemiston sisällön
     */
    public void listaaSisalto() {
        System.out.println(toString());
        sisalto.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return super.toString() + (!nimi().toString().equals("/") ? "/ " : " ") + sisalto.size();
    }

    public String getPolku() {
        return super.toString();
    }

    /**
     * Vertaa eka Tieto luokan equalssia ja jos se palautuu falsena vertaa vielä nimeä
     * @param hakusana
     * @return
     */
    @Override
    public boolean equals(String hakusana) {
        boolean e = super.equals(hakusana);
        if (e) return e;
        return nimi().toString().equals(hakusana);
    }

    /**
     * palauttaa uuden HakemistoIteraattorin
     * @return new HakemistoIteraattori
     */
    @Override
    public Iterator<Tieto> iterator() {
        return new HakemistoIteraattori<>(this);
    }
}
