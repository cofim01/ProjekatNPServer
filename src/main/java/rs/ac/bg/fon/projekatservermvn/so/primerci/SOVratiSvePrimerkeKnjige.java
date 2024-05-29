
package rs.ac.bg.fon.projekatservermvn.so.primerci;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;


/**
 * Predstavlja sistemsku operaciju koja vraca sve primerke odredjene knjige iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiSvePrimerkeKnjige extends OpstiSO {
    /**
     * Lista svih primeraka odredjene knjige u bazi podataka kao ArrayList.
     */
    ArrayList<PrimerakKnjige> lista=new ArrayList<>();
    
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
     * Vraca sve primerke odredjene knjige iz baze podataka na osnovu prosledjenog objekta i dodaje ih u listu primeraka.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase PrimerakKnjige.
     * @throws Exception - ukoliko dodje do greske prilikom dohvatanja podataka iz baze podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> primerciKnjige=DBBroker.getInstanca().selectPoUslovu(odo);
        for(OpstiDomenskiObjekat pk: primerciKnjige){
            lista.add((PrimerakKnjige)pk);
        }
        
        
    }
    /**
     * Vraca listu svih primerka odredjene knjige iz baze podataka.
     * @return - lista svih primeraka odredjene knjige kao ArrayList.
     */
    public ArrayList<PrimerakKnjige> getLista() {
        return lista;
    }
    
    
    
}
