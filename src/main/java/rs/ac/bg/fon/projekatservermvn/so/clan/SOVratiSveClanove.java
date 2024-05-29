package rs.ac.bg.fon.projekatservermvn.so.clan;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 * Predstavlja sistemsku operaciju koja vraca sve clanove biblioteke iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiSveClanove extends OpstiSO {
    /**
     * Lista svih clanova iz baze podataka kao ArrayList.
     */
    ArrayList<Clan> lista = new ArrayList<>();

    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Clan.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Clan.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Vraca sve clanove biblioteke iz baze podataka i dodaje ih u listu.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da sadrzi uslove pretrage.
     * @throws Exception - ukoliko dodje do greske prilikom izvrsenja pretrage.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> clanovi = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat c : clanovi) {
            lista.add((Clan) c);
        }
    }
    /**
     * Vraca listu svih clanova biblioteke.
     * @return - lista svih clanova kao ArrayList.
     */
    public ArrayList<Clan> getLista() {
        return lista;
    }

}
