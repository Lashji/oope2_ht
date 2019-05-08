// Lassi Palojärvi
// 432220
package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Tietoinen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.Objects;

public abstract class Tieto implements Comparable<Tieto>, Tietoinen, Serializable {

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

    public StringBuilder nimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return nimi.toString();
    }

    private boolean tarkistaNimenOikeellisuus(StringBuilder nimi) {

        if (nimi == null) {
            return false;
        }

        String pattern = "^$|^(\\.?[\\w/]+(\\.[\\w]+)?)";

        return nimi.toString().matches(pattern);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Tieto)) {
            return false;
        }

        Tieto tieto = (Tieto) o;
        return nimi().toString().equals(tieto.nimi().toString());

    }

    public boolean equals(String hakusana) {
        if (hakusana == null) {
            return false;
        } else if (this.nimi().toString().equals(hakusana)) {
            return true;
        } else if (hakusana.equals("*")) {
            return true;
        }
//        if (hakusana.contains("*")) {

            String tmpString = hakusana.replaceAll("\\*", "");
            switch (moodi(hakusana)) {
                case 1:
                    return nimi().toString().endsWith(tmpString);
                case 2:
                    return nimi().toString().startsWith(tmpString);
                case 3:
                    return nimi().toString().contains(tmpString);
                default:
                    return false;
            }
//        }
//        else {
//            return nimi.toString().equals(hakusana);
//        }

    }

    //
    public int moodi(String hakusana) {
        int maara = jokerienMaara(hakusana);

        if (maara == 2) {
            return 3;
        }

        if (maara == 1) {
            if (hakusana.startsWith("*")) {
                return 1;
            } else {
                return 2;
            }
        }

        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nimi);
    }

    @Override
    public int compareTo(Tieto t) {
        int i = this.nimi.toString().compareTo(t.nimi.toString());
//        Stringin compareTo palautti -5 jostain syystä joten jotta testit menisivät läpi Integer.compare alapuolella.
        return Integer.compare(i, 0);
    }

    private int jokerienMaara(String sana) {
        int maara = 0;
        char[] ca = sana.toCharArray();

        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == '*') {
                maara++;
                if (i + 1 < ca.length) {
                    if (ca[i + 1] == '*' || (maara == 2 && i != ca.length - 1)) {
                        return -1;
                    }
                }

            }
        }

        if (maara > 2) {
            return -1;
        }

        return maara;
    }

    public Tieto copy() {
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(this);
            oos.flush();
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object kopio = ois.readObject();
            return (Tieto) kopio;

        } catch (InvalidClassException | NotSerializableException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
            return null;
        }
    }


}
