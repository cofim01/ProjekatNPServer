package rs.ac.bg.fon.projekatservermvn.so;


import java.sql.SQLException;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

/**
 * Apstraktna klasa koja predstavlja osnovu za sve sistemske operacije.
 * Svaka sistemska operacija se sastoji od validacije i izvrsenja odredjenih akcija nad objektima domenskog modela.
 * @author Filip Mrdak
 * 
 */
public abstract class OpstiSO {
    
    /**
     * Apstraktna metoda za proveru uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko validacija nije uspesna.
     */
    protected abstract void validate(OpstiDomenskiObjekat odo) throws Exception;

    /**
     * Apstraktna metoda koja predstavlja izvrsenje sistemske operacije nad prosledjenim objektom domenskog modela.
     * @param odo - prosledjeni objekat domenskog modela.
     * @throws Exception - ukoliko je doslo do greske prilikom izvrsenja sistemske operacije. 
     */
    protected abstract void execute(OpstiDomenskiObjekat odo) throws Exception;

    /**
     * Metoda koja predstavlja sablon za sve sistemske operacije.
     * Satoji se od validacije prosledjenog objekata,izvrsenja sistemske operacije nad objektom kao i potvrde promena nad bazom podataka.
     * Ukoliko dodje do greske prilikom izvrsenja operacije, promene se ponistavaju.
     * @param odo - prosledjeni objekat domenskog modela.
     * @throws Exception - ukoliko dodje do greske prilikom izvrsenja sistemske operacije.
     */
    public void executeTemplate(OpstiDomenskiObjekat odo) throws Exception {
        try {
            validate(odo);
            execute(odo);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }

    }
    /**
     * Potvrdjuje izmene u bazi podataka.
     * @throws SQLException - ukoliko dodje do greske prilikom potvrde izmena u bazi.
     */
    private void commit() throws SQLException {
        DBBroker.getInstanca().getConection().commit();

    }
    
    /**
     * Ponistava izmene u bazi podataka.
     * @throws SQLException - ukoliko dodje do greske prilikom ponistavanja izmena u bazi.
     */
    private void rollback() throws SQLException {
        DBBroker.getInstanca().getConection().rollback();
    }

}
