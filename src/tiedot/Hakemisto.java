package tiedot;

import utils.OmaLista;

public class Hakemisto extends Tieto{
    private OmaLista<Tieto> sisalto;
    private Hakemisto ylihakemisto;

    public Hakemisto(StringBuilder uusiNimi, Hakemisto uusiYlihakemisto) {
        super(uusiNimi);
        ylihakemisto = uusiYlihakemisto;
    }

}
