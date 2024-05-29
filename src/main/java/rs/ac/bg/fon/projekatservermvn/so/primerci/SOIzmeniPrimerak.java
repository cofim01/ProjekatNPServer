
package rs.ac.bg.fon.projekatservermvn.so.primerci;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;


/**
 * Predstavlja sistemsku operaciju koja menja podatke za odredjeni primerak knjige u bazi podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOIzmeniPrimerak extends OpstiSO{
    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase PrimerakKnjige.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase PrimerakKnjige.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof PrimerakKnjige)) {
            throw new Exception("Proesledjeni objekat nije instaca klase PrimerakKnjige!");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Vrsi izmene nad postojecim primerkom knjige u bazi podataka na osnovu prosledjenog objekta.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase PrimerakKnjige.
     * @throws Exception - ukoliko dodje do greske prilikom izmene u bazi podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstanca().update(odo);
    }
    
}
