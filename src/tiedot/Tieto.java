package tiedot;

import harjoitustyo.apulaiset.Tietoinen;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Tieto implements Comparable<Tieto>, Tietoinen {

    private StringBuilder nimi;

    public Tieto(StringBuilder uusiNimi) throws IllegalArgumentException {
        nimi(uusiNimi);
    }

    public Tieto() {
        nimi(new StringBuilder(""));
    }

    public void nimi(StringBuilder uusiNimi) throws IllegalArgumentException {

        if (tarkistaNimenOikeellisuus(uusiNimi)) {

            nimi = uusiNimi;

        } else {
            throw new IllegalArgumentException();
        }

    }

    public String nimi() {
        return nimi.toString();
    }

    @Override
    public String toString() {
        return nimi.toString();
    }

    public boolean tarkistaNimenOikeellisuus(StringBuilder nimi){


        if (nimi == null || nimi.length() < 1){
            return false;
        }

        String pattern = "^[A-Za-z0-9_.]+$\n";

//        Pattern p = Pattern.compile(pattern);
//        Matcher m = p.matcher(nimi);

        if (!nimi.toString().matches(pattern)){
            return false;
        }

        return true;
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

    @Override
    public int compareTo(Tieto t){
        return this.nimi.toString().compareTo(t.toString());
    }
}
