package harjoitustyo.omalista;

import harjoitustyo.apulaiset.Ooperoiva;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    public OmaLista() {
        super();
    }


    @Override
    public boolean lisaa(E uusi) {
<<<<<<< HEAD
        return add(uusi);
=======

//        Instance of palauttaa false jos arvo null. Joten null arvon tarkistaminen ei ole erikseen tarpeen.
        if (uusi instanceof Comparable) {

            add(uusi);

            Collections.sort(this, new Comparator<E>() {
                @Override
                public int compare(E e, E t1) {

                    if (e instanceof String && t1 instanceof String) {

                        String t = (String) e;
                        String t2 = (String) t1;


                        return t.compareTo(t2);


                    }
                    return 0;
                }§
            });
            return true;
        }

        return false;
>>>>>>> 4e5e515f6afbc372774f3d40b848e9066b7a562b
    }

    @Override
    public int poista(E poistettava) {
<<<<<<< HEAD

        ListIterator iterator = new ListIterator();




=======
        int maara = 0;

        for (E e : this) {
            if (e.equals(poistettava)) {
                remove(e);
                maara++;
            }
        }

        return maara;
>>>>>>> 4e5e515f6afbc372774f3d40b848e9066b7a562b
    }
}
