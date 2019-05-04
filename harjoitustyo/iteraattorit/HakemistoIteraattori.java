package harjoitustyo.iteraattorit;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.tiedot.Tieto;
import java.util.Iterator;
import java.util.function.Consumer;

// Tänne tulee hakemistoiteraattori myöhemmin
public class HakemistoIteraattori<E> implements Iterator<E> {

    @Override
    public boolean hasNext() {
        
        
    }

    @Override
    public E next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        Iterator.super.remove(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEachRemaining(Consumer<? super E> cnsmr) {
        Iterator.super.forEachRemaining(cnsmr); //To change body of generated methods, choose Tools | Templates.
    }
}
