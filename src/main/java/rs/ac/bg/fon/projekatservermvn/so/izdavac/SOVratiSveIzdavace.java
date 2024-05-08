package rs.ac.bg.fon.projekatservermvn.so.izdavac;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Izdavac;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 * Predstavlja sistemsku operaciju koja vraca sve izdavace iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiSveIzdavace extends OpstiSO {
    
    /**
     * Lista svih izdavaca iz baze podataka kao ArrayList.
     */
    ArrayList<Izdavac> lista = new ArrayList<>();
    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Izdavac.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Izdavac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izdavac!");
        }
    }
    /**
     * Vraca sve izdavace iz baze podataka i dodaje ih u listu.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> izdavaci = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat i : izdavaci) {
            lista.add((Izdavac) i);
        }
    }
    /**
     * Vraca listu svih izdavaca iz baze podataka.
     * @return - lista svih izdavaca kao ArrayList.
     */
    public ArrayList<Izdavac> getLista() {
        return lista;
    }

}
