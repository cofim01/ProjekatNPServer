
package rs.ac.bg.fon.projekatservermvn.so.primerci;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiSvePrimerkeKnjige extends OpstiSO {
    
    ArrayList<PrimerakKnjige> lista=new ArrayList<>();
    
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof PrimerakKnjige)) {
            throw new Exception("Proesledjeni objekat nije instaca klase PrimerakKnjige!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> primerciKnjige=DBBroker.getInstanca().selectPoUslovu(odo);
        for(OpstiDomenskiObjekat pk: primerciKnjige){
            lista.add((PrimerakKnjige)pk);
        }
        
        
    }

    public ArrayList<PrimerakKnjige> getLista() {
        return lista;
    }
    
    
    
}
