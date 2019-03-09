package tiedot;

public abstract class Tieto {

    private StringBuilder nimi;

    public Tieto(StringBuilder uusiNimi){
        nimi(uusiNimi);
    }

    public void nimi(StringBuilder uusiNimi) {
        nimi = uusiNimi;
    }

    public String nimi(){
        return nimi.toString();
    }
}
