package tiedot;

public class Tiedosto extends Tieto{
    private int koko;

    public Tiedosto(StringBuilder nimi, int uusiKoko){
        super(nimi);
        koko(uusiKoko);

    }

    public void koko(int uusiKoko){
        koko = uusiKoko;
    }

    public int koko(){
        return koko;
    }
}
