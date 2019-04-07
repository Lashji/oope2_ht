package harjoitustyo.omalista;

import harjoitustyo.apulaiset.Ooperoiva;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    public OmaLista() {
        super();
    }


    @Override
    public boolean lisaa(E uusi) {

//        Instance of palauttaa false jos arvo null. Joten null arvon tarkistaminen ei ole erikseen tarpeen.
        if (uusi instanceof Comparable) {

            if (add(uusi)) {

                Collections.sort(this, (e, t1) -> {
                    if (e instanceof Comparable && t1 instanceof Comparable) {

                        Comparable t = (Comparable) e;
                        Comparable t2 = (Comparable) t1;

                        return t.compareTo(t2);
                    }
                    return 0;
                });
                return true;
            }
        }
        return false;
    }

    @Override
    public int poista(E poistettava) {
        ListIterator<E> itr = this.listIterator();
        int maara = 0;

        while (itr.hasNext()) {
            if (itr.next().equals(poistettava)) {
                itr.remove();
                maara++;
            }
        }
        return maara;
    }
}
