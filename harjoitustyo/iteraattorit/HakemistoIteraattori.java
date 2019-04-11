package harjoitustyo.iteraattorit;

import java.util.Iterator;

// Tänne tulee hakemistoiteraattori myöhemmin
public class HakemistoIteraattori<E> implements Iterable<E> {

    @Override
    public Iterator<E> iterator() {
        HakemistoIteraattori<E> hi = new HakemistoIteraattori<>();

        return (Iterator<E>) hi;

    }
}
