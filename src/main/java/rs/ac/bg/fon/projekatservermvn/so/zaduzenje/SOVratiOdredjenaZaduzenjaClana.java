
package rs.ac.bg.fon.projekatservermvn.so.zaduzenje;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zaduzenje;


/**
 * Predstavlja sistemsku operaciju koja vraca odredjena zaduzenja za jednog clana iz baze podataka sortirana u opadajucem redosledu prema datumu zaduzenja iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiOdredjenaZaduzenjaClana extends OpstiSO {
    /**
     * Lista svih zaduzenja clana koji ispunjavaju prosledjeni uslov kao ArrayList.
     */
    private ArrayList<Zaduzenje> lista = new ArrayList<>();

    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Zaduzenje.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Zaduzenje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase zaduzenje.");
        }
    }
    /**
     * Vraca sva zaduzenja iz baze podataka koja odgovaraju prosledjenom uslovu i dodaje ih u listu.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> zaduzenja = DBBroker.getInstanca().selectPoUslovu(odo);
        for (OpstiDomenskiObjekat z : zaduzenja) {
            lista.add((Zaduzenje) z);
        }

    }
    /**
     * Vraca listu odredjenih zaduzenja clana.
     * @return - lista odredjenih zaduzenja clana kao ArrayList.
     */
    public ArrayList<Zaduzenje> getLista() {
        return lista;
    }
}
