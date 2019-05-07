package harjoitustyo.iteraattorit;

import harjoitustyo.omalista.OmaLista;
import java.util.Iterator;
// HakemistoIteraattori
public class HakemistoIteraattori<E> implements Iterator<E> {
    private OmaLista<E> sisalto;

    private int index;

    public HakemistoIteraattori(OmaLista<E> sisalto) {
        this.sisalto = sisalto;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < sisalto.size();
    }

    @Override
    public E next() {
        return sisalto.get(index++);
    }
}
