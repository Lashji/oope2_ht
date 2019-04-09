package harjoitustyo.iteraattorit;


import java.util.Iterator;

public class HakemistoIteraattori<E> implements Iterable<E> {


    @Override
    public Iterator<E> iterator() {
        HakemistoIteraattori<E> hi = new HakemistoIteraattori<>();

        return (Iterator<E>) hi;

    }
}
