package tiedot;

public class Tiedosto extends Tieto {
    private int koko;

    public Tiedosto(StringBuilder nimi, int uusiKoko) {
        super(nimi);
        koko(uusiKoko);

    }

    public Tiedosto(){
        koko(0);
    }

    public void koko(int uusiKoko) {
        if (koko >= 0) {
            koko = uusiKoko;
        }
    }

    public int koko() {
        return koko;
    }

    @Override
    public String toString() {
        return super.toString() + " " + koko;
    }
}
