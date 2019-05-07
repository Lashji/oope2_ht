// Lassi Palojärvi
// 432220
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Sailova;
import harjoitustyo.iteraattorit.HakemistoIteraattori;
import harjoitustyo.omalista.OmaLista;

import java.util.Iterator;

import java.util.LinkedList;

// Hakemisto
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

    @Override
    public boolean lisaa(Tieto lisattava) {
        return sisalto.lisaa(lisattava);
    }

    @Override
    public boolean poista(Tieto poistettava) {
        return sisalto.poista(poistettava) > 0;
    }

    public String getNimiAsString() {
        return super.nimi().toString();
    }

    @Override
    public String toString() {
        return super.toString() + "/ " + sisalto.size();
    }
    public String getPolku(){
        return super.toString() + "/";
    }
    @Override
    public boolean equals(String hakusana) {
        boolean e = super.equals(hakusana);
        if (e) return e;
        return getNimiAsString().equals(hakusana);
    }

    @Override
    public Iterator<Tieto> iterator() {
        return new HakemistoIteraattori<>(sisalto);
    }
}
