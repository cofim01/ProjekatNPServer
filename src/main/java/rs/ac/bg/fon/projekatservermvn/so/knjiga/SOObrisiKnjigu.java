
package rs.ac.bg.fon.projekatservermvn.so.knjiga;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author Filip Mrdak
 * Predstavlja sistemsku operaciju koja brise odredjenu knjigu iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 */
public class SOObrisiKnjigu extends OpstiSO{
    
    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Knjiga.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof  Knjiga)){
            throw new Exception("Prosledjeni objekat nije instaca knjige!");
        }
    }
    /**
     * Brise odredjenu knjigu iz baze podataka na osnovu primernog kljuca prosledjenog objekta.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstanca().delete(odo);
    }
    
}
