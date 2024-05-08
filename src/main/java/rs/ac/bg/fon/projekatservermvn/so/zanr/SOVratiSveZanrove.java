package rs.ac.bg.fon.projekatservermvn.so.zanr;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zanr;


/**
 *
 * @author Filip Mrdak
 * Predstavlja sistemsku operaciju koja vraca sve zanrove iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 */
public class SOVratiSveZanrove extends OpstiSO {
    /**
     * Lista svih zanrova iz baze podataka kao ArrayList.
     */
    ArrayList<Zanr> lista = new ArrayList<>();

    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Zanr.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Zanr)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zanr");
        }
    }
    /**
     * Vraca sve zanrove iz baze podataka na osnovu prosledjenog objekta i dodaje ih u listu zanrova.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> zanrovi = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat z : zanrovi) {
            lista.add((Zanr) z);
        }
    }
    /**
     * Vraca listu svih zanrova.
     * @return lista svih zanrova kao ArrayList.
     */
    public ArrayList<Zanr> getLista() {
        return lista;
    }

}
