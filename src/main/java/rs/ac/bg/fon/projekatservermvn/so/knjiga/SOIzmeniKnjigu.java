
package rs.ac.bg.fon.projekatservermvn.so.knjiga;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 * Predstavlja sistemsku operaciju koja vrsi izmenu nad odredjenom knjigom u bazi podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOIzmeniKnjigu extends OpstiSO{
    
    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Knjiga.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Knjiga.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof  Knjiga)){
            throw new Exception("Prosledjeni objekat nije instanca klase knjiga");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Azurira odredjene podatke vec postojece knjige u bazi podataka na osnovu prosledjenog objekta.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Knjiga.
     * @throws Exception - ukoliko dodje do greske prilikom azuriranja podataka u bazi podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstanca().update(odo);
    }
    
}
