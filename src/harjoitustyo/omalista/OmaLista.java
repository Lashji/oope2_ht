package harjoitustyo.omalista;

import harjoitustyo.apulaiset.Ooperoiva;

import java.util.LinkedList;

public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    public OmaLista() {
        super();
    }


    @Override
    public boolean lisaa(E uusi) {
        return false;
    }

    @Override
    public int poista(E poistettava) {
        return 0;
    }
}
