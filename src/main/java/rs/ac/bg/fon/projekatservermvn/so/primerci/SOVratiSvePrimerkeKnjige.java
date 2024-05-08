
package rs.ac.bg.fon.projekatservermvn.so.primerci;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;


/**
 *
 * @author Filip Mrdak
 * Predstavlja sistemsku operaciju koja vraca sve primerke odredjene knjige iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 */
public class SOVratiSvePrimerkeKnjige extends OpstiSO {
    /**
     * Lista svih primeraka odredjene knjige u bazi podataka kao ArrayList.
     */
    ArrayList<PrimerakKnjige> lista=new ArrayList<>();
    
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
     * Vraca sve primerke odredjene knjige iz baze podataka na osnovu prosledjenog objekta i dodaje ih u listu primeraka.
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
