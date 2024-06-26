package rs.ac.bg.fon.projekatservermvn.so.zaduzenje;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zaduzenje;


/**
 * Predstavlja sistemsku operaciju koja vraca sva zaduzenja za jednog clana iz baze podataka sortirana u opadajucem redosledu prema datumu zaduzenja. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiSvaZaduzenjaClana extends OpstiSO {
    /**
     * Lista svih zaduzenja clana kao ArrayList.
     */
    private ArrayList<Zaduzenje> lista = new ArrayList<>();

    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Zaduzenje.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Zaduzenje.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Zaduzenje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase zaduzenje.");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Vraca sva zaduzenja clana iz baze podataka i dodaje ih u listu zaduzenja.
     * 
     *  @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Zaduzenje koja sadrzi podatke o clanu.
     *  @throws Exception - ukoliko dodje do greske prilikom dohvatanja podataka iz baze podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> zaduzenja = DBBroker.getInstanca().selectPoUslovu(odo);
        for (OpstiDomenskiObjekat z : zaduzenja) {
            lista.add((Zaduzenje) z);
        }

    }
    /**
     * Vraca listu svi zaduzenja zaduzenja clana.
     * @return - lista svihzaduzenja clana kao ArrayList.
     */
    public ArrayList<Zaduzenje> getLista() {
        return lista;
    }

}
