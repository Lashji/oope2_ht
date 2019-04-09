package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Syvakopioituva;

public class Tiedosto extends Tieto implements Syvakopioituva<Tieto> {
    private int koko;

    public Tiedosto(StringBuilder nimi, int uusiKoko) throws IllegalArgumentException {
        super(nimi);
        koko(uusiKoko);

    }

    public Tiedosto(){
        koko(0);
    }

    public void koko(int uusiKoko) throws IllegalArgumentException{
        if (koko >= 0) {
            koko = uusiKoko;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int koko() {
        return koko;
    }

    @Override
    public String toString() {
        return super.toString() + " " + koko;
    }

    @Override
    public Tieto kopioi() {
        return null;
    }
}