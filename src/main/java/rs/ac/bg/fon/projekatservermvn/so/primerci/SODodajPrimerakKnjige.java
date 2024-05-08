
package rs.ac.bg.fon.projekatservermvn.so.primerci;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;


/**
 * Predstavlja sistemsku operaciju koja dodaje novi primerak za odredjenu knjigu u bazu podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SODodajPrimerakKnjige extends OpstiSO{
    
    /**
     * Primarni kljuc novog primerka knjige koji je dodat u bazu podataka kao int.
     */
    private int pk;
    
    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase PrimerakKnjige.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof PrimerakKnjige)) {
            throw new Exception("Proesledjeni objekat nije instaca klase PrimerakKnjige!");
        }
        
    }
    /**
     * Dodaje novi primerak knjige u bazu podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        pk=DBBroker.getInstanca().insert(odo);
    }
    /**
     * Vraca primarni kljuc novog primerka knjige koji je dodata u bazu.
     * @return - primarni kljuc novog primerka kao int.
     */
    public int getPk() {
        return pk;
    }
    
    
    
}
