package harjoitustyo.omalista;

import harjoitustyo.apulaiset.Ooperoiva;

import java.util.LinkedList;
import java.util.ListIterator;

public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    public OmaLista() {
        super();
    }


    @Override
    public boolean lisaa(E uusi) {
        return add(uusi);
    }

    @Override
    public int poista(E poistettava) {

        ListIterator iterator = new ListIterator();




    }
}
