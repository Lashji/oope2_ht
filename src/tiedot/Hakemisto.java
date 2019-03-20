package tiedot;

import harjoitustyo.apulaiset.Sailova;
import harjoitustyo.omalista.OmaLista;

import java.util.LinkedList;

public class Hakemisto extends Tieto implements Sailova<Tieto> {
    private OmaLista<Tieto> sisalto;
    private Hakemisto ylihakemisto;

    public Hakemisto(StringBuilder uusiNimi, Hakemisto uusiYlihakemisto) throws  IllegalArgumentException{
        super(uusiNimi);
        ylihakemisto = uusiYlihakemisto;
        sisalto = new OmaLista<>();
    }

    public Hakemisto(){
        super();
        sisalto = new OmaLista<>();
        ylihakemisto = null;
    }
    public String toString() {
        return super.toString() + "/" + sisalto.size();
    }

    public void ylihakemisto(Hakemisto h) throws IllegalArgumentException{
        if (h != null)
        {
            ylihakemisto = h;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public Hakemisto ylihakemisto(){
        return ylihakemisto;
    }

    public void sisalto(OmaLista<Tieto> uusiSisalto) throws IllegalArgumentException{
        if (uusiSisalto != null) {

            sisalto = uusiSisalto;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public LinkedList<Tieto> hae(String hakusana) {
        return null;
    }

    @Override
    public boolean lisaa(Tieto lisattava) {
        return false;
    }

    @Override
    public boolean poista(Tieto poistettava) {
        return false;
    }

    @Override
    public boolean equals(String hakusana) {
        return false;
    }
}
