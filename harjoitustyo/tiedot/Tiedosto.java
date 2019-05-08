// Lassi Palojärvi
// 432220

package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Syvakopioituva;

/**
 * Simuloi tiedostoa
 * periytyy Tieto luokasta
 */
public class Tiedosto extends Tieto implements Syvakopioituva<Tieto> {
    private int koko;

    public Tiedosto(StringBuilder nimi, int uusiKoko) throws IllegalArgumentException {
        super(nimi);
        koko(uusiKoko);

    }

    public Tiedosto() {
        koko(0);
    }

    /**
     *
     * @param uusiKoko
     * @throws IllegalArgumentException
     */
    public void koko(int uusiKoko) throws IllegalArgumentException {
        if (uusiKoko >= 0) {
            koko = uusiKoko;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @return
     */
    public int koko() {
        return koko;
    }

    /**
     *
     * @param toinen
     * @return
     */
    @Override
    public boolean equals(Object toinen){
        return super.equals(toinen);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " " + koko;
    }

    /**
     * Syväkopio tiedoston
     * @return syväkopioitu Tieto
     */
    @Override
    public Tiedosto kopioi() {
        return (Tiedosto) super.copy();
    }
}
