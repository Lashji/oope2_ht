package harjoitustyo.iteraattorit;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tieto;

/**
 * Luokka iteroi Hakemiston lävitse
 * Se sisältää Listan jonka se täyttää konstruktorissa
 */
import java.util.Iterator;

// HakemistoIteraattori
public class HakemistoIteraattori<E> implements Iterator<E> {
    private Hakemisto h;
    private OmaLista<E> tiedot;

    private int index;

    public HakemistoIteraattori(Hakemisto h) {
        this.h = h;
        this.tiedot = new OmaLista<>();
        this.index = 0;
        init();
    }

    /**
     * aloittaa listan täyttämisen
     */
    private void init() {
        lisaa(h, tiedot);
    }

    @Override
    public boolean hasNext() {
        return index < tiedot.size();
    }

    @Override
    public E next() {
        return tiedot.get(index++);
    }

    /**
     * Täyttää listan rekursiivisesti
     *
     * @param h hakemisto
     * @param tiedot tallennettavat tiedot
     */
    private void lisaa(Hakemisto h, OmaLista<E> tiedot) {
        Iterator<E> itr = (Iterator<E>) h.sisalto().iterator();

        while (itr.hasNext()) {
            E t = itr.next();
            tiedot.add(t);
            if (t instanceof Hakemisto) {
                lisaa((Hakemisto) t, tiedot);
            }
        }

    }
}
