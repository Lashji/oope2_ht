package harjoitustyo.omalista;

import harjoitustyo.apulaiset.Ooperoiva;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * OmaLista on LinkedLististä periytyvä oma tietorakenne jolla on
 * lisaa, poista ja järjestä metodit.
 * lista järjestetään aina kun sinne lisätään asioita
 * @param <E>
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    public OmaLista() {
        super();
    }

    /**
     *
     * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
     * toteuttaneen Comparable-rajapinnan.
     * @return
     */
    @Override
    public boolean lisaa(E uusi) {
//        Instance of palauttaa false jos arvo null. Joten null arvon tarkistaminen ei ole erikseen tarpeen.
        if (uusi instanceof Comparable) {
            boolean added = add(uusi);

            if (added) {
                jarjesta();
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param poistettava viite tietoalkioon.
     * @return
     */
    @Override
    public int poista(E poistettava) {
        ListIterator<E> itr = this.listIterator();
        int maara = 0;

        while (itr.hasNext()) {

            E next = itr.next();
            if (next == poistettava) {
                itr.remove();
                maara++;
            }
        }
        return maara;
    }

    /**
     * Metodi järjestää OmaListan
     */
    public void jarjesta() {
        Collections.sort(this, (e, t1) -> {
            if (e instanceof Comparable && t1 instanceof Comparable) {

                Comparable t = (Comparable) e;
                Comparable t2 = (Comparable) t1;

                return t.compareTo(t2);
            }
            return 0;
        });
    }
}
