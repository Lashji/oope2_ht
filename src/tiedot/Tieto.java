package tiedot;

import java.util.Objects;

public abstract class Tieto {

    private StringBuilder nimi;

    public Tieto(StringBuilder uusiNimi){
        nimi(uusiNimi);
    }

    public Tieto(){
        nimi(new StringBuilder(""));
    }

    public void nimi(StringBuilder uusiNimi) {
        nimi = uusiNimi;
    }

    public String nimi(){
        return nimi.toString();
    }

    @Override
    public String toString(){
        return nimi.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tieto)) return false;
        Tieto tieto = (Tieto) o;
        return nimi.toString().equals(tieto.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nimi);
    }
}
