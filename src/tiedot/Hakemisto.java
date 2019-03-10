package tiedot;

import utils.OmaLista;

public class Hakemisto extends Tieto {
    private OmaLista<Tieto> sisalto;
    private Hakemisto ylihakemisto;

    public Hakemisto(StringBuilder uusiNimi, Hakemisto uusiYlihakemisto) {
        super(uusiNimi);
        ylihakemisto = uusiYlihakemisto;
        sisalto = new OmaLista<>();
    }

    public Hakemisto(){
        super();
        sisalto = new OmaLista<>();
        ylihakemisto = null;
    }
    public String toString() {
        return super.toString() + "/" + sisalto.size();
    }

}
