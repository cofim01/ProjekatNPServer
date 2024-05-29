package rs.ac.bg.fon.projekatservermvn.so.zanr;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zanr;


/**
 * Predstavlja sistemsku operaciju koja vraca sve zanrove iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiSveZanrove extends OpstiSO {
    /**
     * Lista svih zanrova iz baze podataka kao ArrayList.
     */
    ArrayList<Zanr> lista = new ArrayList<>();

    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Zanr.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Zanr.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Zanr)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zanr");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije
     * Vraca sve zanrove iz baze podataka na osnovu prosledjenog objekta i dodaje ih u listu zanrova.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Zanr.
     * @throws Exception - ukoliko dodje do greske prilikom dohvatanja podataka iz baze podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> zanrovi = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat z : zanrovi) {
            lista.add((Zanr) z);
        }
    }
    /**.
     * Vraca listu svih zanrova.
     * @return lista svih zanrova kao ArrayList.
     */
    public ArrayList<Zanr> getLista() {
        return lista;
    }

}
